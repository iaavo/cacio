package net.java.openjdk.cacio.provolone;

import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.peer.RobotPeer;

import net.java.openjdk.cacio.ctc.KeyStrokeMappingFactory;
import sun.awt.peer.cacio.CacioMouseInfoPeer;
import sun.awt.peer.cacio.managed.EventData;

public class PTPRobotPeer implements RobotPeer {

  /**
     * 
     */
  private static final int BUTTON_MASK_CONVERSION_SHIFT = 6;

  /**
     * 
     */
  private static final int BUTTON_MASKS = InputEvent.BUTTON1_MASK | InputEvent.BUTTON2_MASK | InputEvent.BUTTON3_MASK;

  /**
     * 
     */
  private static final int BUTTON_DOWN_MASKS = InputEvent.BUTTON1_DOWN_MASK | InputEvent.BUTTON2_DOWN_MASK
                                               | InputEvent.BUTTON3_DOWN_MASK;

  private int currentModifiers = 0;

  private int currentX = 0;

  private int currentY = 0;

  private final PTPMouseClickSupport mouseClickSupport = new PTPMouseClickSupport();

  private EventData mouseEvent(int id, int currentButton, boolean popup) {
    EventData ev = new EventData();
    ev.setId(id);
    ev.setSource(PTPScreen.getInstance());
    ev.setTime(System.currentTimeMillis());
    ev.setModifiers(this.currentModifiers);
    ev.setX(this.currentX);
    ev.setY(this.currentY);
    ev.setButton(currentButton);
    ev.setPopup(popup);
    ev.setClickCount(this.mouseClickSupport.getClickCount());
    return ev;
  }

  @Override
  public void mouseMove(int x, int y) {
    this.currentX = x;
    this.currentY = y;
    int id = isButtonPressed() ? MouseEvent.MOUSE_MOVED : MouseEvent.MOUSE_DRAGGED;
    EventData ev = mouseEvent(id, MouseEvent.NOBUTTON, false);
    PTPEventSource.getInstance().postEvent(ev);
    CacioMouseInfoPeer.getInstance().setMouseScreenCoordinates(x, y);
  }

  private boolean isButtonPressed() {
    return (this.currentModifiers & BUTTON_MASKS) == 0;
  }

  @Override
  public void mousePress(int buttons) {
    int buttonMask = buttonDownToButtonMask(buttons);
    int buttonDownMask = buttonToButtonDownMask(buttons);

    if (buttonDownMask != 0 && buttonMask != 0) {
      this.currentModifiers |= buttonMask;
      EventData ev = mouseEvent(MouseEvent.MOUSE_PRESSED, buttonDownMask, false);
      this.mouseClickSupport.mouseEvent(ev);
      ev = mouseEvent(MouseEvent.MOUSE_PRESSED, buttonDownMask, false);
      // send mousePressed()-Event
      PTPEventSource.getInstance().postEvent(ev);
    }
  }

  @Override
  public void mouseRelease(int buttons) {
    int buttonMask = buttonDownToButtonMask(buttons);
    int buttonDownMask = buttonToButtonDownMask(buttons);

    if (buttonDownMask != 0 && buttonMask != 0) {
      boolean popup = (buttons & InputEvent.BUTTON3_MASK) != 0;
      EventData ev = mouseEvent(MouseEvent.MOUSE_RELEASED, buttonDownMask, popup);
      // send mouseReleased()-Event
      PTPEventSource.getInstance().postEvent(ev);
      // send mouseClicked()-Event
      ev = mouseEvent(MouseEvent.MOUSE_RELEASED, buttonDownMask, false);
      this.mouseClickSupport.mouseEvent(ev);
      this.currentModifiers &= ~buttonMask;
    }
  }

  private int buttonToButtonDownMask(int buttons) {
    int buttonDownMask = buttons & BUTTON_DOWN_MASKS;
    buttonDownMask |= (buttons & BUTTON_MASKS) << BUTTON_MASK_CONVERSION_SHIFT;
    return buttonDownMask;
  }

  private int buttonDownToButtonMask(int buttons) {
    int buttonMask = buttons & BUTTON_MASKS;
    buttonMask |= (buttons & BUTTON_DOWN_MASKS) >> BUTTON_MASK_CONVERSION_SHIFT;
    return buttonMask;
  }

  @Override
  public void mouseWheel(int wheelAmt) {
    // TODO Auto-generated method stub
  }

  private void keyEvent(int keycode, int id) {
    EventData ev = new EventData();
    ev.setSource(PTPScreen.getInstance());
    ev.setId(id);
    ev.setTime(System.currentTimeMillis());
    ev.setModifiers(this.currentModifiers);
    ev.setKeyCode(keycode);
    PTPEventSource.getInstance().postEvent(ev);
  }

  @Override
  public void keyPress(int keycode) {
    if (keycode == KeyEvent.VK_SHIFT) {
      this.currentModifiers |= InputEvent.SHIFT_MASK;
    }
    if (keycode == KeyEvent.VK_CONTROL) {
      this.currentModifiers |= InputEvent.CTRL_MASK;
    }
    if (keycode == KeyEvent.VK_ALT_GRAPH) {
      this.currentModifiers |= InputEvent.ALT_GRAPH_MASK;
    }
    if (keycode == KeyEvent.VK_ALT) {
      this.currentModifiers |= InputEvent.ALT_MASK;
    }
    keyEvent(keycode, KeyEvent.KEY_PRESSED);

    char keychar = getKeyCharFromCodeAndMods(keycode, this.currentModifiers);
    if (keychar != KeyEvent.CHAR_UNDEFINED) {
      EventData ev = new EventData();
      ev.setSource(PTPScreen.getInstance());
      ev.setId(KeyEvent.KEY_TYPED);
      ev.setTime(System.currentTimeMillis());
      ev.setModifiers(this.currentModifiers);
      ev.setKeyChar(keychar);
      PTPEventSource.getInstance().postEvent(ev);
    }
  }

  private char getKeyCharFromCodeAndMods(int keyCode, int modifiers) {
    return KeyStrokeMappingFactory.getInstance().getKeyStrokeMapping().getKeyChar(keyCode, modifiers);
  }

  @Override
  public void keyRelease(int keycode) {
    if (keycode == KeyEvent.VK_SHIFT) {
      this.currentModifiers &= ~InputEvent.SHIFT_MASK;
    }
    if (keycode == KeyEvent.VK_CONTROL) {
      this.currentModifiers &= ~InputEvent.CTRL_MASK;
    }
    if (keycode == KeyEvent.VK_ALT_GRAPH) {
      this.currentModifiers &= ~InputEvent.ALT_GRAPH_MASK;
    }
    if (keycode == KeyEvent.VK_ALT) {
      this.currentModifiers &= ~InputEvent.ALT_MASK;
    }
    keyEvent(keycode, KeyEvent.KEY_RELEASED);
  }

  @Override
  public int getRGBPixel(int x, int y) {
    return getRGBPixels(new Rectangle(x, y, 1, 1))[0];
  }

  @Override
  public int[] getRGBPixels(Rectangle bounds) {
    return PTPScreen.getInstance().getRGBPixels(bounds);
  }

  @Override
  public void dispose() {
    // TODO Auto-generated method stub

  }

}
