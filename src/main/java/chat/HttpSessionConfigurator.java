package chat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class HttpSessionConfigurator extends Configurator {
  // configのプロパティにデータを格納するキー (セッション)
  public static final String Session = "Session";
  // configのプロパティにデータを格納するキー (コンテキスト)
  public static final String Context = "Context";

  // EndPointConfigに HttpSessionと HttpContextを格納する。
  // RequestとResponseはHttpプロトコールで要請、応答する時に使うオブジェクトなので要らない。
  @Override
  public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
    // HttpRequestからセッションを受け取る。
    HttpSession session = (HttpSession) request.getHttpSession();
    // HttpSessionからはコンテキストを受け取る。
    ServletContext context = session.getServletContext();
    // configのプロパティに格納する。
    config.getUserProperties().put(HttpSessionConfigurator.Session, session);
    config.getUserProperties().put(HttpSessionConfigurator.Context, context);
  }
}
