package sun.awt.peer.cacio.basic;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.HeadlessException;
import java.awt.PrintJob;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.awt.font.TextAttribute;
import java.awt.im.InputMethodHighlight;
import java.awt.im.spi.InputMethodDescriptor;
import java.awt.image.ColorModel;
import java.awt.peer.DesktopPeer;
import java.awt.peer.FontPeer;
import java.awt.peer.RobotPeer;
import java.awt.peer.SystemTrayPeer;
import java.awt.peer.TrayIconPeer;
import java.util.Map;
import java.util.Properties;

import sun.awt.peer.cacio.CacioToolkit;

public abstract class BasicToolkitAdapter extends CacioToolkit {

  /*
   * (non-Javadoc)
   * @see sun.awt.InputMethodSupport#getInputMethodAdapterDescriptor()
   */
  @Override
  public InputMethodDescriptor getInputMethodAdapterDescriptor() throws AWTException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#createDragSourceContextPeer(java.awt.dnd.DragGestureEvent)
   */
  @Override
  public DragSourceContextPeer createDragSourceContextPeer(DragGestureEvent arg0) throws InvalidDnDOperationException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#createRobot(java.awt.Robot, java.awt.GraphicsDevice)
   */
  @Override
  public RobotPeer createRobot(Robot arg0, GraphicsDevice arg1) throws AWTException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#createSystemTray(java.awt.SystemTray)
   */
  @Override
  public SystemTrayPeer createSystemTray(SystemTray arg0) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#createTrayIcon(java.awt.TrayIcon)
   */
  @Override
  public TrayIconPeer createTrayIcon(TrayIcon arg0) throws HeadlessException, AWTException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#getFontPeer(java.lang.String, int)
   */
  @Override
  public FontPeer getFontPeer(String arg0, int arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#getScreenHeight()
   */
  @Override
  protected int getScreenHeight() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#getScreenWidth()
   */
  @Override
  protected int getScreenWidth() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#grab(java.awt.Window)
   */
  @Override
  public void grab(Window arg0) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#isDesktopSupported()
   */
  @Override
  public boolean isDesktopSupported() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#isTraySupported()
   */
  @Override
  public boolean isTraySupported() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#syncNativeQueue(long)
   */
  @Override
  protected boolean syncNativeQueue(long arg0) {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * @see sun.awt.SunToolkit#ungrab(java.awt.Window)
   */
  @Override
  public void ungrab(Window arg0) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * @see java.awt.Toolkit#createDesktopPeer(java.awt.Desktop)
   */
  @Override
  protected DesktopPeer createDesktopPeer(Desktop target) throws HeadlessException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see java.awt.Toolkit#getColorModel()
   */
  @Override
  public ColorModel getColorModel() throws HeadlessException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see java.awt.Toolkit#sync()
   */
  @Override
  public void sync() {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * @see java.awt.Toolkit#getPrintJob(java.awt.Frame, java.lang.String, java.util.Properties)
   */
  @Override
  public PrintJob getPrintJob(Frame frame, String jobtitle, Properties props) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see java.awt.Toolkit#beep()
   */
  @Override
  public void beep() {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * @see java.awt.Toolkit#mapInputMethodHighlight(java.awt.im.InputMethodHighlight)
   */
  @Override
  public Map<TextAttribute, ?> mapInputMethodHighlight(InputMethodHighlight highlight) throws HeadlessException {
    // TODO Auto-generated method stub
    return null;
  }

}
