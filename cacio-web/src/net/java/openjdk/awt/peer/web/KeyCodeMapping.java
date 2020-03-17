/*
 * icon Systemhaus GmbH
 * www.icongmbh.de
 */
package net.java.openjdk.awt.peer.web;

import java.awt.event.KeyEvent;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class KeyCodeMapping {
  /** version number */
  public static final String VER = "$Revision$";

  public final static int WINDOWS = 0;

  public final static int MAC = 1;

  public final static int UNIX = 2;

  /**
   * Internet Explorer, Maxthon, etc.
   */
  public final static int TRIDENT = 0;

  /**
   * Firefox, Netscape 6, etc.
   */
  public final static int GECKO = 1;

  /**
   * Opera
   */
  public final static int PRESTO = 2;

  /**
   * Safari, Chrome, etc.
   */
  public final static int WEBKIT = 3;

  public static int toJava(int jsKeyCode, String keyboard, int browserEngine, int os) {
    if (jsKeyCode >= 65 && jsKeyCode <= 90) {
      return jsKeyCode - 65 + KeyEvent.VK_A;
    }
    switch (jsKeyCode) {
      case 8:
        return KeyEvent.VK_BACK_SPACE;
      case 9:
        return KeyEvent.VK_TAB;
      case 12:
        return KeyEvent.VK_CLEAR;
      case 13:
        return KeyEvent.VK_ENTER;
      case 16:
        return KeyEvent.VK_SHIFT;
      case 17:
        return KeyEvent.VK_CONTROL;
      case 18:
        return KeyEvent.VK_ALT;
      case 20:
        return KeyEvent.VK_CAPS_LOCK;
      case 27:
        return KeyEvent.VK_ESCAPE;
      case 32:
        return KeyEvent.VK_SPACE;
      case 33:
        return KeyEvent.VK_PAGE_DOWN;
      case 34:
        return KeyEvent.VK_PAGE_UP;
      case 35:
        return KeyEvent.VK_END;
      case 36:
        return KeyEvent.VK_HOME;
      case 37:
        return KeyEvent.VK_LEFT;
      case 38:
        return KeyEvent.VK_UP;
      case 39:
        return KeyEvent.VK_RIGHT;
      case 40:
        return KeyEvent.VK_DOWN;
      case 45:
        return KeyEvent.VK_INSERT;
      case 46:
        return KeyEvent.VK_DELETE;
      case 48:
        return KeyEvent.VK_0;
      case 49:
        return KeyEvent.VK_1;
      case 50:
        return KeyEvent.VK_2;
      case 51:
        return KeyEvent.VK_3;
      case 52:
        return KeyEvent.VK_4;
      case 53:
        return KeyEvent.VK_5;
      case 54:
        return KeyEvent.VK_6;
      case 55:
        return KeyEvent.VK_7;
      case 56:
        return KeyEvent.VK_8;
      case 57:
        return KeyEvent.VK_9;
      case 59:
        if (browserEngine == GECKO) return KeyEvent.VK_SEMICOLON;
        break;
      case 60:
        if (keyboard.equals("de") && browserEngine == GECKO) return KeyEvent.VK_BACK_SLASH;
        break;
      case 61:
        if (browserEngine == GECKO) return KeyEvent.VK_EQUALS;
        break;
      case 63:
        if (keyboard.equals("de") && browserEngine == GECKO) return KeyEvent.VK_SLASH;
        break;
      case 91:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_WINDOWS;
        break;
      case 96:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD0;
        break;
      case 97:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD1;
        break;
      case 98:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD2;
        break;
      case 99:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD3;
        break;
      case 100:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD4;
        break;
      case 101:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD5;
        break;
      case 102:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD6;
        break;
      case 103:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD7;
        break;
      case 104:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD8;
        break;
      case 105:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_NUMPAD9;
        break;
      case 106:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_MULTIPLY;
        break;
      case 107:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_ADD;
        break;
      case 109:
        if (browserEngine == GECKO) return KeyEvent.VK_MINUS;
        if (browserEngine == TRIDENT) return KeyEvent.VK_SUBTRACT;
        break;
      case 110:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_DECIMAL;
        break;
      case 111:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_DIVIDE;
        break;
      case 112:
        return KeyEvent.VK_F1;
      case 113:
        return KeyEvent.VK_F2;
      case 114:
        return KeyEvent.VK_F3;
      case 115:
        return KeyEvent.VK_F4;
      case 116:
        return KeyEvent.VK_F5;
      case 117:
        return KeyEvent.VK_F6;
      case 118:
        return KeyEvent.VK_F7;
      case 119:
        return KeyEvent.VK_F8;
      case 120:
        return KeyEvent.VK_F9;
      case 121:
        return KeyEvent.VK_F10;
      case 122:
        return KeyEvent.VK_F11;
      case 123:
        return KeyEvent.VK_F12;
      case 144:
        return KeyEvent.VK_NUM_LOCK;
      case 160:
        if (keyboard.equals("de") && browserEngine == GECKO) return KeyEvent.VK_BACK_QUOTE;
      case 163:
        if (keyboard.equals("de") && browserEngine == GECKO) return KeyEvent.VK_NUMBER_SIGN;
        break;
      case 171:
        if (keyboard.equals("de") && browserEngine == GECKO) return KeyEvent.VK_CLOSE_BRACKET;
        break;
      case 173:
        if (keyboard.equals("de") && browserEngine == GECKO) return KeyEvent.VK_MINUS;
        break;
      case 186:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO))
          return KeyStrokeMappingDE.VK_U_UML;
        if (browserEngine == TRIDENT) return KeyEvent.VK_SEMICOLON;
        break;
      case 187:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO))
          return KeyEvent.VK_CLOSE_BRACKET;
        if (browserEngine == TRIDENT) return KeyEvent.VK_EQUALS;
        break;
      case 188:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_COMMA;
        break;
      case 189:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO)) return KeyEvent.VK_MINUS;
        if (browserEngine == TRIDENT) return KeyEvent.VK_MINUS;
        break;
      case 190:
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_PERIOD;
        break;
      case 191:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO))
          return KeyEvent.VK_NUMBER_SIGN;
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_SLASH;
        break;
      case 192:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO))
          return KeyStrokeMappingDE.VK_O_UML;
        if (keyboard.equals("de") && browserEngine == GECKO) return KeyEvent.VK_EQUALS;
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_BACK_QUOTE;
        break;
      case 219:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO)) return KeyEvent.VK_SLASH;
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_OPEN_BRACKET;
        break;
      case 220:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO))
          return KeyEvent.VK_BACK_QUOTE;
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_BACK_SLASH;
        break;
      case 221:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO)) return KeyEvent.VK_EQUALS;
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_CLOSE_BRACKET;
        break;
      case 222:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO))
          return KeyStrokeMappingDE.VK_A_UML;
        if (browserEngine == GECKO || browserEngine == TRIDENT) return KeyEvent.VK_QUOTE;
        break;
      case 226:
        if (keyboard.equals("de") && (browserEngine == TRIDENT || browserEngine == PRESTO))
          return KeyEvent.VK_BACK_SLASH;
        break;
    }
    return 0;
  }
}
