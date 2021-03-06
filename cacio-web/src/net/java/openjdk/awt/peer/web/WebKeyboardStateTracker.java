/*
 * Copyright (c) 2011, Clemens Eisserer, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package net.java.openjdk.awt.peer.web;

import static java.awt.event.KeyEvent.VK_0;
import static java.awt.event.KeyEvent.VK_1;
import static java.awt.event.KeyEvent.VK_2;
import static java.awt.event.KeyEvent.VK_3;
import static java.awt.event.KeyEvent.VK_4;
import static java.awt.event.KeyEvent.VK_5;
import static java.awt.event.KeyEvent.VK_6;
import static java.awt.event.KeyEvent.VK_7;
import static java.awt.event.KeyEvent.VK_8;
import static java.awt.event.KeyEvent.VK_9;
import static java.awt.event.KeyEvent.VK_BACK_SPACE;
import static java.awt.event.KeyEvent.VK_COLON;
import static java.awt.event.KeyEvent.VK_COMMA;
import static java.awt.event.KeyEvent.VK_CONTROL;
import static java.awt.event.KeyEvent.VK_DELETE;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_END;
import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_EQUALS;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static java.awt.event.KeyEvent.VK_F1;
import static java.awt.event.KeyEvent.VK_F10;
import static java.awt.event.KeyEvent.VK_F11;
import static java.awt.event.KeyEvent.VK_F12;
import static java.awt.event.KeyEvent.VK_F2;
import static java.awt.event.KeyEvent.VK_F3;
import static java.awt.event.KeyEvent.VK_F4;
import static java.awt.event.KeyEvent.VK_F5;
import static java.awt.event.KeyEvent.VK_F6;
import static java.awt.event.KeyEvent.VK_F7;
import static java.awt.event.KeyEvent.VK_F8;
import static java.awt.event.KeyEvent.VK_F9;
import static java.awt.event.KeyEvent.VK_HOME;
import static java.awt.event.KeyEvent.VK_INSERT;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SEMICOLON;
import static java.awt.event.KeyEvent.VK_SEPARATOR;
import static java.awt.event.KeyEvent.VK_SHIFT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_TAB;
import static java.awt.event.KeyEvent.VK_UP;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import sun.awt.peer.cacio.managed.EventData;

/**
 * @author Clemens Eisserer <linuxhippy@gmail.com>
 */
public class WebKeyboardStateTracker {
  final WebScreen screen;

  EventData lastEvent = null;

  public WebKeyboardStateTracker(WebScreen screen) {
    this.screen = screen;
  }

  public void trackKeyEvent(boolean down,
                            int keySym,
                            char keyChar,
                            String keyboard,
                            boolean ctrl,
                            boolean shift,
                            boolean alt) {
    final EventData data = new EventData();
    data.setTime(System.currentTimeMillis());
    data.setSource(this.screen);

    // System.out.println("Keyboard: "+getCharForKeyCode(keySym, shift));

    if (down) {
      data.setId(KeyEvent.KEY_PRESSED);
      data.setKeyCode(keySym);
      generateTypedEvent(data, keySym, keyChar, keyboard, ctrl, shift, alt);
    } else {
      data.setId(KeyEvent.KEY_RELEASED);
      data.setKeyCode(keySym);
    }

    this.lastEvent = data;
    this.screen.dispatchEvent(data);
  }

  protected void generateTypedEvent(EventData pressedEvent,
                                    int keySym,
                                    char keyChar,
                                    String keyboard,
                                    boolean ctrl,
                                    boolean shift,
                                    boolean alt) {
    int modifiers = 0;
    if (ctrl) modifiers |= InputEvent.CTRL_MASK;
    if (shift) modifiers |= InputEvent.SHIFT_MASK;
    if (alt) modifiers |= InputEvent.ALT_MASK;
    final char selChar = KeyStrokeMappingFactory.getInstance().getKeyStrokeMapping(keyboard)
      .getKeyChar(keySym, modifiers);
    if (selChar != KeyEvent.CHAR_UNDEFINED) {
      final EventData typedEvent = new EventData();
      typedEvent.setId(KeyEvent.KEY_TYPED);
      typedEvent.setTime(System.currentTimeMillis());
      typedEvent.setSource(this.screen);
      typedEvent.setKeyChar(selChar);
      // typedEvent.setKeyChar(s);

      this.screen.dispatchEvent(typedEvent);
    }
  }

  protected char getCharForKeyCode(int keySym, boolean shift) {

    // System.out.println("KeySym: "+keySym);

    switch (keySym) {

      case 32:
        return ' ';
      case 33:
        return '!';
      case 59:
        return ';';
      case 61:
        return '=';
      case 38:
        return '&';
      case 40:
        return '(';
      case 41:
        return ')';
      case 44:
        return ',';
      case 45:
        return '-';
      case 46:
        return '.';

      case 48:
        return '0';
      case 49:
        return '1';
      case 50:
        return '2';
      case 51:
        return '3';
      case 52:
        return '4';
      case 53:
        return '5';
      case 54:
        return '6';
      case 55:
        return '7';
      case 56:
        return '8';
      case 57:
        return '9';
      case 58:
        return ':';
      case 63:
        return '?';
      case 92:
        return '???';
      case 124:
        return '???';
      case 214:
        return '???';
      case 196:
        return '???';
    }

    if ((keySym >= 97) && (keySym <= 122) || ((keySym >= 65) && (keySym <= 90))) {
      return (char)keySym;
    }

    return 0;
  }

  protected int getJavaKeycodeForKeySym(int keySym, boolean shift) {

    switch (keySym) {
      case 0xFF08:
        return VK_BACK_SPACE;
      case 0xFF09:
        return VK_TAB;
      case 0xFF0D:
        return VK_ENTER;
      case 0xFF1B:
        return VK_ESCAPE;
      case 0xFF63:
        return VK_INSERT;
      case 0xFFFF:
        return VK_DELETE;
      case 0xFF50:
        return VK_HOME;
      case 0xFF57:
        return VK_END;
      case 0xFF51:
        return VK_LEFT;
      case 0xFF52:
        return VK_UP;
      case 0xFF53:
        return VK_RIGHT;
      case 0xFF54:
        return VK_DOWN;

      case 0xFFBE:
        return VK_F1;
      case 0xFFBF:
        return VK_F2;
      case 0xFFC0:
        return VK_F3;
      case 0xFFC1:
        return VK_F4;
      case 0xFFC2:
        return VK_F5;
      case 0xFFC3:
        return VK_F6;
      case 0xFFC4:
        return VK_F7;
      case 0xFFC5:
        return VK_F8;
      case 0xFFC6:
        return VK_F9;
      case 0xFFC7:
        return VK_F10;
      case 0xFFC8:
        return VK_F11;
      case 0xFFC9:
        return VK_F12;
      case 0xFFE1:
        return VK_SHIFT;
      case 0xFFE3:
        return VK_CONTROL;

      case 32:
        return VK_SPACE;

      case 59:
        return VK_SEMICOLON;
      case 61:
        return VK_EQUALS;
      case 44:
        return VK_COMMA;
      case 45:
        return VK_SEPARATOR;
      case 46:
        return VK_COLON;
        // TODO: Da fehlen noch einige!

      case 48:
        return VK_0;
      case 49:
        return VK_1;
      case 50:
        return VK_2;
      case 51:
        return VK_3;
      case 52:
        return VK_4;
      case 53:
        return VK_5;
      case 54:
        return VK_6;
      case 55:
        return VK_7;
      case 56:
        return VK_8;
      case 57:
        return VK_9;
    }

    if ((keySym >= 97) && (keySym <= 122) && !shift) {
      return keySym - 32;
    } else if ((keySym >= 65) && (keySym <= 90) && shift) {
      return keySym;
    }

    return -1;
  }
}