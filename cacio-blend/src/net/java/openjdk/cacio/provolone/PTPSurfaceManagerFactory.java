package net.java.openjdk.cacio.provolone;

import sun.awt.image.SunVolatileImage;
import sun.awt.image.VolatileSurfaceManager;
import sun.java2d.SurfaceManagerFactory;

class PTPSurfaceManagerFactory extends SurfaceManagerFactory {

  @Override
  public VolatileSurfaceManager createVolatileManager(SunVolatileImage image, Object context) {
    return new PTPVolatileSurfaceManager(image, context);
  }

}
