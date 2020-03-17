package net.java.openjdk.cacio.provolone;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.image.BufferedImage;

import sun.java2d.SunGraphicsEnvironment;
import sun.java2d.SurfaceManagerFactory;

public class PTPGraphicsEnvironment extends SunGraphicsEnvironment {

  public PTPGraphicsEnvironment() {
    SurfaceManagerFactory.setInstance(new PTPSurfaceManagerFactory());
  }

  @Override
  public Graphics2D createGraphics(BufferedImage img) {
    return new PTPGraphics(super.createGraphics(img), null);
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
