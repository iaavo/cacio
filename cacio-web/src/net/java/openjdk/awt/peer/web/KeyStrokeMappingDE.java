package net.java.openjdk.awt.peer.web;

import static java.awt.event.InputEvent.ALT_MASK;
import static java.awt.event.InputEvent.CTRL_MASK;
import static java.awt.event.InputEvent.SHIFT_MASK;
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
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_ADD;
import static java.awt.event.KeyEvent.VK_B;
import static java.awt.event.KeyEvent.VK_BACK_QUOTE;
import static java.awt.event.KeyEvent.VK_BACK_SLASH;
import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_CLOSE_BRACKET;
import static java.awt.event.KeyEvent.VK_COMMA;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_DECIMAL;
import static java.awt.event.KeyEvent.VK_DELETE;
import static java.awt.event.KeyEvent.VK_DIVIDE;
import static java.awt.event.KeyEvent.VK_E;
import static java.awt.event.KeyEvent.VK_EQUALS;
import static java.awt.event.KeyEvent.VK_F;
import static java.awt.event.KeyEvent.VK_G;
import static java.awt.event.KeyEvent.VK_H;
import static java.awt.event.KeyEvent.VK_I;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_K;
import static java.awt.event.KeyEvent.VK_L;
import static java.awt.event.KeyEvent.VK_M;
import static java.awt.event.KeyEvent.VK_MINUS;
import static java.awt.event.KeyEvent.VK_MULTIPLY;
import static java.awt.event.KeyEvent.VK_N;
import static java.awt.event.KeyEvent.VK_NUMBER_SIGN;
import static java.awt.event.KeyEvent.VK_NUMPAD0;
import static java.awt.event.KeyEvent.VK_NUMPAD1;
import static java.awt.event.KeyEvent.VK_NUMPAD2;
import static java.awt.event.KeyEvent.VK_NUMPAD3;
import static java.awt.event.KeyEvent.VK_NUMPAD4;
import static java.awt.event.KeyEvent.VK_NUMPAD5;
import static java.awt.event.KeyEvent.VK_NUMPAD6;
import static java.awt.event.KeyEvent.VK_NUMPAD7;
import static java.awt.event.KeyEvent.VK_NUMPAD8;
import static java.awt.event.KeyEvent.VK_NUMPAD9;
import static java.awt.event.KeyEvent.VK_O;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_PERIOD;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_SLASH;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_SUBTRACT;
import static java.awt.event.KeyEvent.VK_T;
import static java.awt.event.KeyEvent.VK_U;
import static java.awt.event.KeyEvent.VK_V;
import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_X;
import static java.awt.event.KeyEvent.VK_Y;
import static java.awt.event.KeyEvent.VK_Z;

import java.awt.AWTKeyStroke;
import java.awt.event.KeyEvent;
import java.util.Map;

class KeyStrokeMappingDE extends AbstractKeyStrokeMapping implements KeyStrokeMapping {

  public final static int VK_A_UML = KeyEvent.getExtendedKeyCodeForChar('??');

  public final static int VK_O_UML = KeyEvent.getExtendedKeyCodeForChar('??');

  public final static int VK_U_UML = KeyEvent.getExtendedKeyCodeForChar('??');

  private final Map<AWTKeyStroke, Character> map;

  KeyStrokeMappingDE() {
    this.map = getDefaultMap();

    this.map.put(keyStroke(VK_0, NO_MASK), '0');
    this.map.put(keyStroke(VK_0, SHIFT_MASK), '=');
    this.map.put(keyStroke(VK_0, ALT_MASK | CTRL_MASK), '}');
    this.map.put(keyStroke(VK_1, NO_MASK), '1');
    this.map.put(keyStroke(VK_1, SHIFT_MASK), '!');
    this.map.put(keyStroke(VK_2, NO_MASK), '2');
    this.map.put(keyStroke(VK_2, SHIFT_MASK), '"');
    this.map.put(keyStroke(VK_2, ALT_MASK | CTRL_MASK), '\u00b2');
    this.map.put(keyStroke(VK_3, NO_MASK), '3');
    this.map.put(keyStroke(VK_3, SHIFT_MASK), '\u00a7');
    this.map.put(keyStroke(VK_3, ALT_MASK | CTRL_MASK), '\u00b3');
    this.map.put(keyStroke(VK_4, NO_MASK), '4');
    this.map.put(keyStroke(VK_4, SHIFT_MASK), '$');
    this.map.put(keyStroke(VK_5, NO_MASK), '5');
    this.map.put(keyStroke(VK_5, SHIFT_MASK), '%');
    this.map.put(keyStroke(VK_6, NO_MASK), '6');
    this.map.put(keyStroke(VK_6, SHIFT_MASK), '&');
    this.map.put(keyStroke(VK_7, NO_MASK), '7');
    this.map.put(keyStroke(VK_7, SHIFT_MASK), '/');
    this.map.put(keyStroke(VK_7, ALT_MASK | CTRL_MASK), '{');
    this.map.put(keyStroke(VK_8, NO_MASK), '8');
    this.map.put(keyStroke(VK_8, SHIFT_MASK), '(');
    this.map.put(keyStroke(VK_8, ALT_MASK | CTRL_MASK), '[');
    this.map.put(keyStroke(VK_9, NO_MASK), '9');
    this.map.put(keyStroke(VK_9, SHIFT_MASK), ')');
    this.map.put(keyStroke(VK_9, ALT_MASK | CTRL_MASK), ']');
    this.map.put(keyStroke(VK_A, NO_MASK), 'a');
    this.map.put(keyStroke(VK_A, SHIFT_MASK), 'A');
    this.map.put(keyStroke(VK_B, NO_MASK), 'b');
    this.map.put(keyStroke(VK_B, SHIFT_MASK), 'B');
    this.map.put(keyStroke(VK_BACK_QUOTE, NO_MASK), '^');
    this.map.put(keyStroke(VK_BACK_QUOTE, SHIFT_MASK), '\u00b0');
    this.map.put(keyStroke(VK_BACK_SLASH, NO_MASK), '<');
    this.map.put(keyStroke(VK_BACK_SLASH, SHIFT_MASK), '>');
    this.map.put(keyStroke(VK_BACK_SLASH, ALT_MASK | CTRL_MASK), '|');
    this.map.put(keyStroke(VK_C, NO_MASK), 'c');
    this.map.put(keyStroke(VK_C, SHIFT_MASK), 'C');
    this.map.put(keyStroke(VK_CLOSE_BRACKET, NO_MASK), '+');
    this.map.put(keyStroke(VK_CLOSE_BRACKET, SHIFT_MASK), '*');
    this.map.put(keyStroke(VK_CLOSE_BRACKET, ALT_MASK | CTRL_MASK), '~');
    this.map.put(keyStroke(VK_COMMA, NO_MASK), ',');
    this.map.put(keyStroke(VK_COMMA, SHIFT_MASK), ';');
    this.map.put(keyStroke(VK_D, NO_MASK), 'd');
    this.map.put(keyStroke(VK_D, SHIFT_MASK), 'D');
    this.map.put(keyStroke(VK_DELETE, NO_MASK), '\u007f');
    this.map.put(keyStroke(VK_E, NO_MASK), 'e');
    this.map.put(keyStroke(VK_E, SHIFT_MASK), 'E');
    this.map.put(keyStroke(VK_E, ALT_MASK | CTRL_MASK), '\u20ac');
    this.map.put(keyStroke(VK_EQUALS, NO_MASK), '\u00b4');
    this.map.put(keyStroke(VK_EQUALS, SHIFT_MASK), '`');
    this.map.put(keyStroke(VK_F, NO_MASK), 'f');
    this.map.put(keyStroke(VK_F, SHIFT_MASK), 'F');
    this.map.put(keyStroke(VK_G, NO_MASK), 'g');
    this.map.put(keyStroke(VK_G, SHIFT_MASK), 'G');
    this.map.put(keyStroke(VK_H, NO_MASK), 'h');
    this.map.put(keyStroke(VK_H, SHIFT_MASK), 'H');
    this.map.put(keyStroke(VK_I, NO_MASK), 'i');
    this.map.put(keyStroke(VK_I, SHIFT_MASK), 'I');
    this.map.put(keyStroke(VK_J, NO_MASK), 'j');
    this.map.put(keyStroke(VK_J, SHIFT_MASK), 'J');
    this.map.put(keyStroke(VK_K, NO_MASK), 'k');
    this.map.put(keyStroke(VK_K, SHIFT_MASK), 'K');
    this.map.put(keyStroke(VK_L, NO_MASK), 'l');
    this.map.put(keyStroke(VK_L, SHIFT_MASK), 'L');
    this.map.put(keyStroke(VK_M, NO_MASK), 'm');
    this.map.put(keyStroke(VK_M, SHIFT_MASK), 'M');
    this.map.put(keyStroke(VK_M, ALT_MASK | CTRL_MASK), '\u00b5');
    this.map.put(keyStroke(VK_SLASH, NO_MASK), '\u00DF');
    this.map.put(keyStroke(VK_SLASH, SHIFT_MASK), '?');
    this.map.put(keyStroke(VK_SLASH, ALT_MASK | CTRL_MASK), '\\');
    this.map.put(keyStroke(VK_N, NO_MASK), 'n');
    this.map.put(keyStroke(VK_N, SHIFT_MASK), 'N');
    this.map.put(keyStroke(VK_O, NO_MASK), 'o');
    this.map.put(keyStroke(VK_O, SHIFT_MASK), 'O');
    this.map.put(keyStroke(VK_P, NO_MASK), 'p');
    this.map.put(keyStroke(VK_P, SHIFT_MASK), 'P');
    this.map.put(keyStroke(VK_PERIOD, NO_MASK), '.');
    this.map.put(keyStroke(VK_PERIOD, SHIFT_MASK), ':');
    this.map.put(keyStroke(VK_Q, NO_MASK), 'q');
    this.map.put(keyStroke(VK_Q, SHIFT_MASK), 'Q');
    this.map.put(keyStroke(VK_Q, ALT_MASK | CTRL_MASK), '@');
    this.map.put(keyStroke(VK_R, NO_MASK), 'r');
    this.map.put(keyStroke(VK_R, SHIFT_MASK), 'R');
    this.map.put(keyStroke(VK_S, NO_MASK), 's');
    this.map.put(keyStroke(VK_S, SHIFT_MASK), 'S');
    this.map.put(keyStroke(VK_MINUS, NO_MASK), '-');
    this.map.put(keyStroke(VK_MINUS, SHIFT_MASK), '_');
    this.map.put(keyStroke(VK_SPACE, NO_MASK), ' ');
    this.map.put(keyStroke(VK_T, NO_MASK), 't');
    this.map.put(keyStroke(VK_T, SHIFT_MASK), 'T');
    this.map.put(keyStroke(VK_U, NO_MASK), 'u');
    this.map.put(keyStroke(VK_U, SHIFT_MASK), 'U');
    this.map.put(keyStroke(VK_V, NO_MASK), 'v');
    this.map.put(keyStroke(VK_V, SHIFT_MASK), 'V');
    this.map.put(keyStroke(VK_W, NO_MASK), 'w');
    this.map.put(keyStroke(VK_W, SHIFT_MASK), 'W');
    this.map.put(keyStroke(VK_X, NO_MASK), 'x');
    this.map.put(keyStroke(VK_X, SHIFT_MASK), 'X');
    this.map.put(keyStroke(VK_Y, NO_MASK), 'y');
    this.map.put(keyStroke(VK_Y, SHIFT_MASK), 'Y');
    this.map.put(keyStroke(VK_Z, NO_MASK), 'z');
    this.map.put(keyStroke(VK_Z, SHIFT_MASK), 'Z');
    this.map.put(keyStroke(VK_A_UML, NO_MASK), '\u00E4');
    this.map.put(keyStroke(VK_A_UML, SHIFT_MASK), '\u00C4');
    this.map.put(keyStroke(VK_O_UML, NO_MASK), '\u00F6');
    this.map.put(keyStroke(VK_O_UML, SHIFT_MASK), '\u00D6');
    this.map.put(keyStroke(VK_U_UML, NO_MASK), '\u00FC');
    this.map.put(keyStroke(VK_U_UML, SHIFT_MASK), '\u00DC');
    this.map.put(keyStroke(VK_NUMBER_SIGN, NO_MASK), '#');
    this.map.put(keyStroke(VK_NUMBER_SIGN, SHIFT_MASK), '\'');

    this.map.put(keyStroke(VK_NUMPAD0, NO_MASK), '0');
    this.map.put(keyStroke(VK_NUMPAD1, NO_MASK), '1');
    this.map.put(keyStroke(VK_NUMPAD2, NO_MASK), '2');
    this.map.put(keyStroke(VK_NUMPAD3, NO_MASK), '3');
    this.map.put(keyStroke(VK_NUMPAD4, NO_MASK), '4');
    this.map.put(keyStroke(VK_NUMPAD5, NO_MASK), '5');
    this.map.put(keyStroke(VK_NUMPAD6, NO_MASK), '6');
    this.map.put(keyStroke(VK_NUMPAD7, NO_MASK), '7');
    this.map.put(keyStroke(VK_NUMPAD8, NO_MASK), '8');
    this.map.put(keyStroke(VK_NUMPAD9, NO_MASK), '9');
    this.map.put(keyStroke(VK_ADD, NO_MASK), '+');
    this.map.put(keyStroke(VK_SUBTRACT, NO_MASK), '-');
    this.map.put(keyStroke(VK_MULTIPLY, NO_MASK), '*');
    this.map.put(keyStroke(VK_DIVIDE, NO_MASK), '/');
    this.map.put(keyStroke(VK_DECIMAL, NO_MASK), ',');
  }

  @Override
  public char getKeyChar(int keyCode, int modifiers) {
    AWTKeyStroke stroke = keyStroke(keyCode, modifiers);
    Character ch = this.map.get(stroke);
    if (ch == null) {
      return KeyEvent.CHAR_UNDEFINED;
    } else {
      return ch.charValue();
    }
  }

}
