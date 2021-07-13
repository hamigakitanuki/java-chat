package chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// WebSocketのホストアドレス設定
//初めにWebSocketの接続をすると、プロトコールのhandshake段階で接続ヘッダをチェックして「@onOpen」クラスが呼ばれる。
@ServerEndpoint(value = "/broadsocket", configurator = HttpSessionConfigurator.class)
public class BroadSocket {
  // 接続したクライアントセッションを管理するリスト
  private static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
  // メッセージからユーザ名を取得するような正規表現パターン
  private static Pattern pattern = Pattern.compile("^\\{\\{.*?\\}\\}");

  private static Map<Session, EndpointConfig> configs = Collections.synchronizedMap(new HashMap<>());

  // WebSocket serverにブラウザ(client)が接続すれば呼ばれる関数。
  @OnOpen
  public void handleOpen(Session userSession, EndpointConfig config) {
    // クライアントが接続すればクライアントセッションリストにWebSocketセッションを格納する。
    sessionUsers.add(userSession);

    System.out.println(userSession);
    if (!configs.containsKey(userSession)) {
      // WebSocket sessionは各関数(@onMessage,@onError,@onClose)が呼ばれる時にパラメータから受け取られるので、
      //  キーとして使用できる。
      configs.put(userSession, config);
    }
    this.bloadcastMessage(userSession, "ヒトが入場しました");
    // コンソールにメッセージを出力する。
    System.out.println("client is now connected...");
  }
  // WebSocket serverにブラウザ(client)がメッセージを転送すれば呼ばれる関数。
  @OnMessage
  public void handleMessage(String message, Session userSession) throws IOException {
    // コンソールに受け取ったメッセージを出力する。
    System.out.println(message);
    // 初期ユーザ名
    String name = "anonymous";
    // メッセージから送信したユーザ名を取得する。
    Matcher matcher = pattern.matcher(message);
    // メッセージ例 : {{ユーザ名}} メッセージ
    if (matcher.find()) {
      name = matcher.group();
    }
    // closureのため、変数を常数に設定
    final String msg = message.replaceAll(pattern.pattern(), "");
    final String username = name.replaceFirst("^\\{\\{", "").replaceFirst("\\}\\}$", "");
    
    this.bloadcastMessage(userSession, username + " => " + msg);
  }
  // WebSocket serverにブラウザ(client)が切断すれば呼ばれる関数。
  @OnClose
  public void handleClose(Session userSession) {
	
	this.bloadcastMessage(userSession, "ヒトが退出しました");
    // セッションリストから切断されたセッションを抜く。
    sessionUsers.remove(userSession);
    // コンソールにメッセージを出力する。
    System.out.println("client is now disconnected...");
  }
  
  public void bloadcastMessage(Session userSession, String msg) {
    String chatRoomId;
    // configマップからWebSocket sessionキーでconfigオブジェクトを取得する。
    if (configs.containsKey(userSession)) {
      EndpointConfig config = configs.get(userSession);
      // HttpSessionConfiguratorから設定したsessionを取得する。
      HttpSession session = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);

      chatRoomId = (String) session.getAttribute("chatRoomId");
    } else {
      return;
    }
    
    System.out.println(chatRoomId);

    // クライアントセッションリストからセッションを繰り返して取得する。
    sessionUsers.forEach(sessionUser -> {
      // 取得したセッションとメッセージを送ったセッションは同じなら返事しない。
      if (sessionUser == userSession) {
        return;
      }
      try {
        if (configs.containsKey(sessionUser)) {
          EndpointConfig config = configs.get(sessionUser);
          // HttpSessionConfiguratorから設定したsessionを取得する。
          HttpSession httpSeesion = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);

          String userChatRoomId = (String) httpSeesion.getAttribute("chatRoomId");
          if (userChatRoomId.equals(chatRoomId)) {
            // 同じチャットルームIDのユーザーにメッセージ送信
            sessionUser.getBasicRemote().sendText(msg);
          }
        }

      } catch (IOException e) {
        // エラーが発生するとコンソールに出力する。
        e.printStackTrace();
      }
    });
  }
}
