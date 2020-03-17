package net.java.openjdk.cacio.provolone;

import java.awt.GraphicsDevice;

import sun.java2d.SunGraphicsEnvironment;
import sun.java2d.SurfaceManagerFactory;

public class PTPGraphicsEnvironment extends SunGraphicsEnvironment {

  public PTPGraphicsEnvironment() {
    SurfaceManagerFactory.setInstance(new PTPSurfaceManagerFactory());
  }

  @Override
  protected int getNumScreens() {
    return 1;
  }

  @Override
  protected GraphicsDevice makeScreenDevice(int screennum) {
    return new PTPGraphicsDevice();
  }

  @Override
  public boolean isDisplayLocal() {
    return true;
  }

}
