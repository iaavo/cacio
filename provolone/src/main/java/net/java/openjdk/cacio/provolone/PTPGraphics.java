package net.java.openjdk.cacio.provolone;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import net.java.openjdk.awt.peer.web.GridDamageTracker;
import net.java.openjdk.awt.peer.web.WebRect;

/**
 * Experimental Class
 */
public class PTPGraphics extends Graphics2D {

  private static boolean lock = false;

  private final Graphics2D graphics;

  private final GridDamageTracker tracker;

  public PTPGraphics(Graphics2D graphics, GridDamageTracker damageTracker) {
    if (graphics == null) throw new NullPointerException("graphics cannot be null.");
    this.graphics = graphics;
    this.tracker = damageTracker;
  }

  private WebRect toWebRect(Rectangle rect) {
    return new WebRect(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
  }

  private WebRect toWebRect(Rectangle rect, int offsetX, int offsetY) {
    return new WebRect(rect.x + offsetX, rect.y + offsetY, rect.x + rect.width + offsetX, rect.y + rect.height
                                                                                          + offsetY);
  }

  private WebRect toWebRect(int[] xPoints, int[] yPoints) {
    int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE, x2 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
    for (int i = 0; i < xPoints.length; i++) {
      if (xPoints[i] < x1) x1 = xPoints[i];
      if (xPoints[i] > x2) x2 = xPoints[i];
    }
    for (int i = 0; i < yPoints.length; i++) {
      if (yPoints[i] < y1) y1 = yPoints[i];
      if (yPoints[i] > y2) y2 = yPoints[i];
    }
    return new WebRect(x1, y1, x2, y2);
  }

  private void track(WebRect rect) {
	  if(lock)
		  return;
    float[] dstPts = new float[8];
    this.graphics.getTransform().transform(new float[] {rect.getX1(),
                                                        rect.getY1(),
                                                        rect.getX2(),
                                                        rect.getY1(),
                                                        rect.getX2(),
                                                        rect.getY2(),
                                                        rect.getX1(),
                                                        rect.getY2()},
                                           0,
                                           dstPts,
                                           0,
                                           4);
    rect = toWebRect(new int[] {(int)dstPts[0], (int)dstPts[2], (int)dstPts[4], (int)dstPts[6]},
                     new int[] {(int)dstPts[1], (int)dstPts[3], (int)dstPts[5], (int)dstPts[7]});
    System.out.println(rect);
    if (this.tracker != null) this.tracker.trackDamageRect(rect);
  }

  @Override
  public void draw(Shape s) {
    track(toWebRect(s.getBounds()));
    this.graphics.draw(s);
  }

  @Override
  public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
    // TODO
    return this.graphics.drawImage(img, xform, obs);
  }

  @Override
  public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
    track(toWebRect(op.getBounds2D(img).getBounds()));
    this.graphics.drawImage(img, op, x, y);
  }

  @Override
  public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
    // TODO Auto-generated method stub
    this.graphics.drawRenderedImage(img, xform);
  }

  @Override
  public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
    // TODO Auto-generated method stub
    this.graphics.drawRenderableImage(img, xform);
  }

  @Override
  public void drawString(String str, int x, int y) {
    track(toWebRect(this.graphics.getFontMetrics().getStringBounds(str, this.graphics).getBounds(), x, y));
    this.graphics.drawString(str, x, y);
  }

  @Override
  public void drawString(String str, float x, float y) {
    track(toWebRect(this.graphics.getFontMetrics().getStringBounds(str, this.graphics).getBounds(), (int)x, (int)y));
    this.graphics.drawString(str, x, y);
  }

  @Override
  public void drawString(AttributedCharacterIterator iterator, int x, int y) {
    // TODO Auto-generated method stub
    this.graphics.drawString(iterator, x, y);
  }

  @Override
  public void drawString(AttributedCharacterIterator iterator, float x, float y) {
    // TODO Auto-generated method stub
    this.graphics.drawString(iterator, x, y);
  }

  @Override
  public void drawGlyphVector(GlyphVector g, float x, float y) {
    // TODO Auto-generated method stub
    this.graphics.drawGlyphVector(g, x, y);
  }

  @Override
  public void fill(Shape s) {
    track(toWebRect(s.getBounds()));
    this.graphics.draw(s);
  }

  @Override
  public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
    return this.graphics.hit(rect, s, onStroke);
  }

  @Override
  public GraphicsConfiguration getDeviceConfiguration() {
    return this.graphics.getDeviceConfiguration();
  }

  @Override
  public void setComposite(Composite comp) {
    this.graphics.setComposite(comp);
  }

  @Override
  public void setPaint(Paint paint) {
    this.graphics.setPaint(paint);
  }

  @Override
  public void setStroke(Stroke s) {
    this.graphics.setStroke(s);
  }

  @Override
  public void setRenderingHint(Key hintKey, Object hintValue) {
    this.graphics.setRenderingHint(hintKey, hintValue);
  }

  @Override
  public Object getRenderingHint(Key hintKey) {
    return this.graphics.getRenderingHint(hintKey);
  }

  @Override
  public void setRenderingHints(Map<?, ?> hints) {
    this.graphics.setRenderingHints(hints);
  }

  @Override
  public void addRenderingHints(Map<?, ?> hints) {
    this.graphics.addRenderingHints(hints);
  }

  @Override
  public RenderingHints getRenderingHints() {
    return this.graphics.getRenderingHints();
  }

  @Override
  public void translate(int x, int y) {
    this.graphics.translate(x, y);
  }

  @Override
  public void translate(double tx, double ty) {
    this.graphics.translate(tx, ty);
  }

  @Override
  public void rotate(double theta) {
    this.graphics.rotate(theta);
  }

  @Override
  public void rotate(double theta, double x, double y) {
    this.graphics.rotate(theta, x, y);
  }

  @Override
  public void scale(double sx, double sy) {
    this.graphics.scale(sx, sy);
  }

  @Override
  public void shear(double shx, double shy) {
    this.graphics.shear(shx, shy);
  }

  @Override
  public void transform(AffineTransform Tx) {
    this.graphics.transform(Tx);
  }

  @Override
  public void setTransform(AffineTransform Tx) {
    this.graphics.setTransform(Tx);
  }

  @Override
  public AffineTransform getTransform() {
    return this.graphics.getTransform();
  }

  @Override
  public Paint getPaint() {
    return this.graphics.getPaint();
  }

  @Override
  public Composite getComposite() {
    return this.graphics.getComposite();
  }

  @Override
  public void setBackground(Color color) {
    this.graphics.setBackground(color);
  }

  @Override
  public Color getBackground() {
    return this.graphics.getBackground();
  }

  @Override
  public Stroke getStroke() {
    return this.graphics.getStroke();
  }

  @Override
  public void clip(Shape s) {
    // TODO Auto-generated method stub
    this.graphics.clip(s);
  }

  @Override
  public FontRenderContext getFontRenderContext() {
    return this.graphics.getFontRenderContext();
  }

  @Override
  public Graphics create() {
    // TODO
    return this.graphics.create();
  }

  @Override
  public Color getColor() {
    return this.graphics.getColor();
  }

  @Override
  public void setColor(Color c) {
    this.graphics.setColor(c);
  }

  @Override
  public void setPaintMode() {
    this.graphics.setPaintMode();
  }

  @Override
  public void setXORMode(Color c1) {
    this.graphics.setXORMode(c1);
  }

  @Override
  public Font getFont() {
    return this.graphics.getFont();
  }

  @Override
  public void setFont(Font font) {
    this.graphics.setFont(font);
  }

  @Override
  public FontMetrics getFontMetrics(Font f) {
    return this.graphics.getFontMetrics(f);
  }

  @Override
  public Rectangle getClipBounds() {
    return this.graphics.getClipBounds();
  }

  @Override
  public void clipRect(int x, int y, int width, int height) {
    // TODO Auto-generated method stub
    this.graphics.clipRect(x, y, width, height);
  }

  @Override
  public void setClip(int x, int y, int width, int height) {
    // TODO Auto-generated method stub
    this.graphics.setClip(x, y, width, height);
  }

  @Override
  public Shape getClip() {
    return this.graphics.getClip();
  }

  @Override
  public void setClip(Shape clip) {
    // TODO
    this.graphics.setClip(clip);
  }

  @Override
  public void copyArea(int x, int y, int width, int height, int dx, int dy) {
    track(new WebRect(dx, dy, dx + width, dy + height));
    this.graphics.copyArea(x, y, width, height, dx, dy);
  }

  @Override
  public void drawLine(int x1, int y1, int x2, int y2) {
    track(new WebRect(x1, y1, x2, y2));
    this.graphics.drawLine(x1, y1, x2, y2);
  }

  @Override
  public void fillRect(int x, int y, int width, int height) {
    track(new WebRect(x, y, x + width, y + height));
    this.graphics.fillRect(x, y, width, height);
  }

  @Override
  public void clearRect(int x, int y, int width, int height) {
    track(new WebRect(x, y, x + width, y + height));
    this.graphics.clearRect(x, y, width, height);
  }

  @Override
  public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    track(new WebRect(x, y, x + width, y + height));
    this.graphics.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
  }

  @Override
  public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
    track(new WebRect(x, y, x + width, y + height));
    this.graphics.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
  }

  @Override
  public void drawOval(int x, int y, int width, int height) {
    track(new WebRect(x, y, x + width, y + height));
    this.graphics.drawOval(x, y, width, height);
  }

  @Override
  public void fillOval(int x, int y, int width, int height) {
    track(new WebRect(x, y, x + width, y + height));
    this.graphics.drawOval(x, y, width, height);
  }

  @Override
  public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
    track(new WebRect(x, y, x + width, y + height));
    this.graphics.drawArc(x, y, width, height, startAngle, arcAngle);
  }

  @Override
  public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
    track(new WebRect(x, y, x + width, y + height));
    this.graphics.fillArc(x, y, width, height, startAngle, arcAngle);
  }

  @Override
  public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
    track(toWebRect(xPoints, yPoints));
    this.graphics.drawPolyline(xPoints, yPoints, nPoints);
  }

  @Override
  public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
    track(toWebRect(xPoints, yPoints));
    this.graphics.drawPolygon(xPoints, yPoints, nPoints);
  }

  @Override
  public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
    track(toWebRect(xPoints, yPoints));
    this.graphics.fillPolygon(xPoints, yPoints, nPoints);
  }

  @Override
  public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
    // TODO Observer problems
    track(new WebRect(x, y, x + img.getWidth(observer), y + img.getHeight(observer)));
    return this.graphics.drawImage(img, x, y, observer);
  }

  @Override
  public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
    track(new WebRect(x, y, x + width, y + height));
    return this.graphics.drawImage(img, x, y, width, height, observer);
  }

  @Override
  public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
    // TODO Observer problems
    track(new WebRect(x, y, x + img.getWidth(observer), y + img.getHeight(observer)));
    return this.graphics.drawImage(img, x, y, bgcolor, observer);
  }

  @Override
  public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
    track(new WebRect(x, y, x + width, y + height));
    return this.graphics.drawImage(img, x, y, width, height, bgcolor, observer);
  }

  @Override
  public boolean drawImage(Image img,
                           int dx1,
                           int dy1,
                           int dx2,
                           int dy2,
                           int sx1,
                           int sy1,
                           int sx2,
                           int sy2,
                           ImageObserver observer) {
    track(new WebRect(dx1, dy1, dx2, dy2));
    return this.graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
  }

  @Override
  public boolean drawImage(Image img,
                           int dx1,
                           int dy1,
                           int dx2,
                           int dy2,
                           int sx1,
                           int sy1,
                           int sx2,
                           int sy2,
                           Color bgcolor,
                           ImageObserver observer) {
    track(new WebRect(dx1, dy1, dx2, dy2));
    return this.graphics.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
  }

  @Override
  public void dispose() {
    this.graphics.dispose();
  }

  public static void lock() {
	lock = true;
  }

  public static void unlock() {
	lock = false;
  }
}