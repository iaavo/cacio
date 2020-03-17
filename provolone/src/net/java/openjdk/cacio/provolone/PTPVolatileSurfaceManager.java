package net.java.openjdk.cacio.provolone;

import sun.awt.image.SunVolatileImage;
import sun.awt.image.VolatileSurfaceManager;
import sun.java2d.SurfaceData;

class PTPVolatileSurfaceManager extends VolatileSurfaceManager {

  protected PTPVolatileSurfaceManager(SunVolatileImage vImg, Object context) {
    super(vImg, context);
  }

  @Override
  protected boolean isAccelerationEnabled() {
    return false;
  }

  @Override
  protected SurfaceData initAcceleratedSurface() {
    return null;
  }

}
