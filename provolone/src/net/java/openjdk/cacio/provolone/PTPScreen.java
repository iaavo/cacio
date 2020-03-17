package net.java.openjdk.cacio.provolone;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.List;

import sun.awt.peer.cacio.WindowClippedGraphics;
import sun.awt.peer.cacio.managed.FullScreenWindowFactory;
import sun.awt.peer.cacio.managed.PlatformScreen;

public class PTPScreen implements PlatformScreen {

  private final BufferedImage screenBuffer;

  private static PTPScreen instance;

  static PTPScreen getInstance() {
    if (instance == null) {
      instance = new PTPScreen();
    }
    return instance;
  }

  private PTPScreen() {
    Dimension d = FullScreenWindowFactory.getScreenDimension();
    this.screenBuffer = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
  }

  @Override
  public ColorModel getColorModel() {
    return this.screenBuffer.getColorModel();
  }

  @Override
  public GraphicsConfiguration getGraphicsConfiguration() {
    return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
  }

  @Override
  public Rectangle getBounds() {
    Dimension d = FullScreenWindowFactory.getScreenDimension();
    return new Rectangle(0, 0, d.width, d.height);
  }

  @Override
  public Graphics2D getClippedGraphics(Color fg, Color bg, Font f, List<Rectangle> clipRects) {
    Graphics2D g2d = (Graphics2D)this.screenBuffer.getGraphics();
    if (clipRects != null && clipRects.size() > 0) {
      Area a = new Area(getBounds());
      for (Rectangle clip : clipRects) {
        a.subtract(new Area(clip));
      }
      g2d = new WindowClippedGraphics(g2d, a);
    }
    return g2d;
  }

  int[] getRGBPixels(Rectangle bounds) {
    return this.screenBuffer.getRGB(bounds.x, bounds.y, bounds.width, bounds.height, null, 0, bounds.width);
  }
}
