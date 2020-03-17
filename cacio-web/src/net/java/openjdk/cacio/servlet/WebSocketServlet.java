package net.java.openjdk.cacio.servlet;

import javax.servlet.http.HttpServletRequest;

import net.java.openjdk.awt.peer.web.WebSessionState;

import org.eclipse.jetty.websocket.WebSocket;

public class WebSocketServlet extends org.eclipse.jetty.websocket.WebSocketServlet {

  @Override
  public WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
    WebSessionState state = WebSessionManager.getInstance().getSessionState(request);
    return new WebSocketStreamThread(request.getSession(), state);
  }
}
