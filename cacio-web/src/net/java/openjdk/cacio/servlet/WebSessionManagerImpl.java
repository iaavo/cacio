package net.java.openjdk.cacio.servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.java.openjdk.awt.peer.web.WebSessionState;

public class WebSessionManagerImpl extends WebSessionManager {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  public synchronized WebSessionState register(HttpSession session) {
    HashMap<Integer, WebSessionState> subSessionMap = (HashMap<Integer, WebSessionState>)session
      .getAttribute(SESSION_KEY);

    if (subSessionMap == null) {
      subSessionMap = new HashMap<Integer, WebSessionState>();
      session.setAttribute(SESSION_KEY, subSessionMap);
    }

    int subSessionID = subSessionMap.size();
    WebSessionState sessionState = new WebSessionState(subSessionID, subSessionMap);
    subSessionMap.put(subSessionID, sessionState);

    return sessionState;
  }

  public synchronized WebSessionState getCurrentState(HttpSession session, int subSessionID) {
    HashMap<Integer, WebSessionState> subSessionMap = (HashMap<Integer, WebSessionState>)session
      .getAttribute(SESSION_KEY);
    if (subSessionMap != null) {
      WebSessionState state = subSessionMap.get(subSessionID);
      registerSessionAtCurrentThread(state);
      return state;
    }

    return null;
  }

  public synchronized void disposeSession(HttpSession session) {
    HashMap<Integer, WebSessionState> subSessionMap = (HashMap<Integer, WebSessionState>)session
      .getAttribute(SESSION_KEY);
    if (subSessionMap != null) {
      List<Integer> subSessionKeyList = new ArrayList<Integer>(subSessionMap.keySet());

      for (Integer subSessionId : subSessionKeyList) {
        WebSessionState state = subSessionMap.get(subSessionId);

        state.lockSession();
        try {
          state.dispose();
        } catch (Exception ex) {
          logger.log(Level.WARNING, "Exception occured when disposing session", ex);
        } finally {
          state.unlockSession();
        }
      }
      subSessionMap.clear();
    }
  }

  public WebSessionState getSessionState(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    String subSessionID = request.getParameter("subsessionid");

    if (session == null || subSessionID == null) {
      logger.log(Level.WARNING,
                 "No Session registered for the specified session-id/subsession-number. Ignoring request");
      return null;
    }

    return getCurrentState(session, Integer.parseInt(subSessionID));
  }
}
