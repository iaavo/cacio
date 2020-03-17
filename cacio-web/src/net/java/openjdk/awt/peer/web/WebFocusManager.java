package net.java.openjdk.awt.peer.web;

import net.java.openjdk.cacio.servlet.WebSessionManager;
import sun.awt.peer.cacio.managed.FocusManager;

public class WebFocusManager extends FocusManager {

  @Override
  protected FocusManager getContextInstance() {
    final WebSessionState state = WebSessionManager.getInstance().getCurrentState();

    WebFocusManager mgr;
    if ((mgr = state.getFocusManager()) == null) {
      mgr = new WebFocusManager();
      state.setFocusManager(mgr);
    }

    return mgr;
  }

}
