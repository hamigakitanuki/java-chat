package chat;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// handshake設定を指定する。
// 初めにWebSocketの接続をすると、プロトコールのhandshake段階で接続ヘッダをチェックして「@onOpen」クラスが呼ばれる。
@ServerEndpoint(value = "/websocket", configurator = HttpSessionConfigurator.class)
public class WebSocket {
  // WebSocket sessionをキーとしてconfigオブジェクト(Web SessionとContextがあるオブジェクト)をマップに管理する。
  private Map<Session, EndpointConfig> configs = Collections.synchronizedMap(new HashMap<>());
  // handshakeが終われば「@OnOpen」が呼ばれる。EndpointConfgのインスタンスはhandshakeクラスから設定されたconfigデータ。
  @OnOpen
  public void handleOpen(Session userSession, EndpointConfig config) {
    // コンソールに接続ログを表示する。
    System.out.println("client is now connected...");
    // EndpointConfigのデータを管理マップに格納する。
    if (!configs.containsKey(userSession)) {
      // WebSocket sessionは各関数(@onMessage,@onError,@onClose)が呼ばれる時にパラメータから受け取られるので、
      //  キーとして使用できる。
      configs.put(userSession, config);
    }
  }
  // WebSocket serverにブラウザ(client)がメッセージを転送すれば呼ばれる関数。
  @OnMessage
  public String handleMessage(String message, Session userSession) {
    // configマップからWebSocket sessionキーでconfigオブジェクトを取得する。
    if (configs.containsKey(userSession)) {
      EndpointConfig config = configs.get(userSession);
      // HttpSessionConfiguratorから設定したsessionを取得する。
      HttpSession session = (HttpSession) config.getUserProperties().get(HttpSessionConfigurator.Session);
      // Web SessionからTestSessionキーでデータを取得する。(テスト用)
      return "Session - " + (String) session.getAttribute("TestSession");
    }
    return "error";
  }
  // WebSocket serverにブラウザ(client)が切断すれば呼ばれる関数。
  @OnClose
  public void handleClose(Session userSession) {
    // コンソールにメッセージを出力する。
    System.out.println("client is now disconnected...");
    // 接続が切断すればconfigマップからWebSocket sessionキーでデータを抜く。
    if (configs.containsKey(userSession)) {
      configs.remove(userSession);
    }
  }
  // WebSocket serverにブラウザ(client)がエラーが発生すれば呼ばれる関数。
  @OnError
  public void handleError(Throwable t) {
    // コンソールにエラーメッセージを表示する。
    t.printStackTrace();
  }
}