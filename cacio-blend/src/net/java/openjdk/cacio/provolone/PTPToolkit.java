package net.java.openjdk.cacio.provolone;

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
import sun.awt.peer.cacio.PlatformWindowFactory;
import sun.awt.peer.cacio.managed.FullScreenWindowFactory;

public class PTPToolkit extends CacioToolkit {

  private PlatformWindowFactory platformWindowFactory;

  private Window grabbedWindow;

  private boolean grabbed;

  static {
    // LibraryLoader.loadLibs();
  }

  public PTPToolkit() {
    super();
    setDecorateWindows(true);
    System.setProperty("swing.defaultlaf", "javax.swing.plaf.metal.MetalLookAndFeel");
  }

  @Override
  public PlatformWindowFactory getPlatformWindowFactory() {
    if (this.platformWindowFactory == null) {
      PTPScreen screen = PTPScreen.getInstance();
      PTPEventSource eventSource = PTPEventSource.getInstance();
      this.platformWindowFactory = new FullScreenWindowFactory(screen, eventSource);
    }
    return this.platformWindowFactory;
  }

  @Override
  public InputMethodDescriptor getInputMethodAdapterDescriptor() throws AWTException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setLockingKeyState(int keyCode, boolean on) throws UnsupportedOperationException {
    // Do nothing
  }

  @Override
  public boolean getLockingKeyState(int keyCode) throws UnsupportedOperationException {
    // Do nothing
    return false;
  }

  @Override
  public DragSourceContextPeer createDragSourceContextPeer(DragGestureEvent dge) throws InvalidDnDOperationException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TrayIconPeer createTrayIcon(TrayIcon target) throws HeadlessException, AWTException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SystemTrayPeer createSystemTray(SystemTray target) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isTraySupported() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public FontPeer getFontPeer(String name, int style) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public RobotPeer createRobot(Robot target, GraphicsDevice screen) throws AWTException {
    return new PTPRobotPeer();
  }

  @Override
  protected int getScreenWidth() {
    return FullScreenWindowFactory.getScreenDimension().width;
  }

  @Override
  protected int getScreenHeight() {
    return FullScreenWindowFactory.getScreenDimension().height;
  }

  @Override
  protected boolean syncNativeQueue(long timeout) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void grab(Window w) {
    System.out.println("Grab");
    if (this.grabbedWindow != null) {
      ungrab(this.grabbedWindow, true);
    }
    this.grabbed = true;
    this.grabbedWindow = w;
    this.grabbedWindow.toFront();
  }

  private void ungrab(Window w, boolean doPost) {
    System.out.println("Ungrab");
    if (this.grabbed && this.grabbedWindow == w) {
      if (doPost) {
        this.grabbedWindow.setBounds(3, 3, 51, 51);
      }
      this.grabbedWindow = null;
      this.grabbed = false;
    }
  }

  @Override
  public void ungrab(Window w) {
    ungrab(w, true);
  }

  @Override
  public boolean isDesktopSupported() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  protected DesktopPeer createDesktopPeer(Desktop target) throws HeadlessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ColorModel getColorModel() throws HeadlessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void sync() {
    // TODO Auto-generated method stub
  }

  @Override
  public PrintJob getPrintJob(Frame frame, String jobtitle, Properties props) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void beep() {
    // not needed
  }

  @Override
  public Map<TextAttribute, ?> mapInputMethodHighlight(InputMethodHighlight highlight) throws HeadlessException {
    // TODO Auto-generated method stub
    return null;
  }
}
