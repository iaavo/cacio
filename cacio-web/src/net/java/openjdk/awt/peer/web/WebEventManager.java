package net.java.openjdk.awt.peer.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class WebEventManager {

  WebMouseStateTracker mouseTracker;

  WebKeyboardStateTracker keyboardTracker;

  WebSessionState state;

  HashMap<Integer, String[]> eventIDMap = new HashMap<Integer, String[]>();

  int highestDispatchedEventID = 0;

  public WebEventManager(WebSessionState state) {
    this.state = state;

    this.mouseTracker = new WebMouseStateTracker(state.getScreen());
    this.keyboardTracker = new WebKeyboardStateTracker(state.getScreen());
  }

  public void parseEventData(int eventID, String paramStr) {
    // System.out.println(eventID + ": " + paramStr);
    this.state.lockSession();
    try {
      final String[] params = paramStr.split("_");

      if (eventID == -1 || (eventID == this.highestDispatchedEventID + 1 && this.eventIDMap.size() == 0)) {
        dispatchEvent(params);
        this.highestDispatchedEventID = eventID;
      } else {
        this.eventIDMap.put(eventID, params);

        final ArrayList<Integer> sortedEventIDList = new ArrayList<Integer>(this.eventIDMap.keySet());
        Collections.sort(sortedEventIDList);
        final int minID = sortedEventIDList.get(0);
        final int maxId = sortedEventIDList.get(sortedEventIDList.size() - 1);

        if (minID == this.highestDispatchedEventID + 1 && (maxId - minID) == (sortedEventIDList.size() - 1)) {
          for (final int curEvId : sortedEventIDList) {
            dispatchEvent(this.eventIDMap.get(curEvId));
          }
          this.highestDispatchedEventID = maxId;
          this.eventIDMap.clear();
        }
      }
    } finally {
      this.state.unlockSession();
    }
  }

  protected void dispatchEvent(String[] params) {
    final LinkedList<String> eventDataList = new LinkedList<String>();
    eventDataList.addAll(Arrays.asList(params));

    while (eventDataList.size() > 0) {
      final String command = eventDataList.removeFirst();
      if (command.length() > 0) {

        if (command.equals("M")) {
          processMouseEvent(eventDataList);
        } else if (command.equals("MM")) {
          processMouseMotionEvent(eventDataList);
        } else if (command.equals("MW")) {
          processMouseWheelEvent(eventDataList);
        } else if (command.equals("K")) {
          processKeyEvent(eventDataList);
        } else if (command.equals("S")) {
          processResizeEvent(eventDataList);
        }
      }
    }
  }

  protected void processResizeEvent(LinkedList<String> params) {
    final int w = Integer.parseInt(params.removeFirst());
    final int h = Integer.parseInt(params.removeFirst());

    this.state.getGraphicsConfiguration().getScreen().resizeScreen(w, h);
  }

  protected void processKeyEvent(LinkedList<String> params) {
    final boolean down = Integer.parseInt(params.removeFirst()) > 0;
    int keyCode = Integer.parseInt(params.removeFirst());
    final int keySym = KeyCodeMapping.toJava(keyCode,
                                             this.state.getKeyboard(),
                                             this.state.getBrowser(),
                                             this.state.getOS());
    final char charVal = 0;
    final boolean ctrl = Boolean.parseBoolean(params.removeFirst());
    final boolean shift = Boolean.parseBoolean(params.removeFirst());
    final boolean alt = Boolean.parseBoolean(params.removeFirst());
    this.keyboardTracker.trackKeyEvent(down, keySym, charVal, this.state.getKeyboard(), ctrl, shift, alt);
  }

  protected void processMouseMotionEvent(LinkedList<String> params) {
    final int x = Integer.parseInt(params.removeFirst());
    final int y = Integer.parseInt(params.removeFirst());

    this.mouseTracker.trackMouseMotionEvent(x, y);
  }

  protected void processMouseWheelEvent(LinkedList<String> params) {
    final boolean up = Integer.parseInt(params.removeFirst()) > 0;
    final int x = Integer.parseInt(params.removeFirst());
    final int y = Integer.parseInt(params.removeFirst());

    this.mouseTracker.trackMouseWheelEvent(up, x, y);
  }

  protected void processMouseEvent(LinkedList<String> params) {
    final int x = Integer.parseInt(params.removeFirst());
    final int y = Integer.parseInt(params.removeFirst());
    final boolean down = Integer.parseInt(params.removeFirst()) > 0;
    final int buttonMask = Integer.parseInt(params.removeFirst());

    this.mouseTracker.trackMouseEvent(down, buttonMask, x, y);
  }
}
