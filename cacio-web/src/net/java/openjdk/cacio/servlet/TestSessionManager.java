package net.java.openjdk.cacio.servlet;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.java.openjdk.awt.peer.web.WebSessionState;

public class TestSessionManager extends WebSessionManager {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  HashMap<Integer, WebSessionState> idSessionMap = new HashMap<Integer, WebSessionState>();

  public synchronized WebSessionState register(HttpSession session) {
    int subSessionID = idSessionMap.size();
    WebSessionState sessionState = new WebSessionState(subSessionID, idSessionMap);
    idSessionMap.put(subSessionID, sessionState);

    return sessionState;
  }

  public synchronized WebSessionState getCurrentState(HttpSession session, int subSessionID) {
    WebSessionState state = idSessionMap.get(subSessionID);
    registerSessionAtCurrentThread(state);

    return state;
  }

  public synchronized void disposeSession(HttpSession session) {}

  public synchronized void disposeSession(int subSessionID) {
    WebSessionState state = idSessionMap.remove(subSessionID);
    state.dispose();
  }

  public WebSessionState getSessionState(HttpServletRequest request) {
    String subSessionID = request.getParameter("subsessionid");
    return getCurrentState(null, Integer.parseInt(subSessionID));
  }
}
