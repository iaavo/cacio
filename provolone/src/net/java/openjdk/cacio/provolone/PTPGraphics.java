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

public class PTPGraphics extends Graphics2D {

  private final Graphics2D graphics;

  private final GridDamageTracker tracker;

  public PTPGraphics(Graphics2D graphics, GridDamageTracker damageTracker) {
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

  private void track(WebRect rect) {
    System.out.println(rect.getX1() + "," + rect.getY1() + "," + rect.getY1() + "," + rect.getY2());
    // this.tracker.trackDamageRect(rect);
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
    // TODO Auto-generated method stub
    this.graphics.translate(x, y);
  }

  @Override
  public void translate(double tx, double ty) {
    // TODO Auto-generated method stub
    this.graphics.translate(tx, ty);
  }

  @Override
  public void rotate(double theta) {
    // TODO Auto-generated method stub
    this.graphics.rotate(theta);
  }

  @Override
  public void rotate(double theta, double x, double y) {
    // TODO Auto-generated method stub
    this.graphics.rotate(theta, x, y);
  }

  @Override
  public void scale(double sx, double sy) {
    // TODO Auto-generated method stub
    this.graphics.scale(sx, sy);
  }

  @Override
  public void shear(double shx, double shy) {
    // TODO Auto-generated method stub
    this.graphics.shear(shx, shy);
  }

  @Override
  public void transform(AffineTransform Tx) {
    // TODO Auto-generated method stub
    this.graphics.transform(Tx);
  }

  @Override
  public void setTransform(AffineTransform Tx) {
    // TODO Auto-generated method stub
    this.graphics.setTransform(Tx);
  }

  @Override
  public AffineTransform getTransform() {
    // TODO Auto-generated method stub
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
    this.graphics.drawPolyline(xPoints, yPoints, nPoints);
    // TODO Auto-generated method stub
  }

  @Override
  public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
    this.graphics.drawPolygon(xPoints, yPoints, nPoints);
    // TODO Auto-generated method stub
  }

  @Override
  public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
    this.graphics.fillPolygon(xPoints, yPoints, nPoints);
    // TODO Auto-generated method stub
  }

  @Override
  public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
    // TODO
    return this.graphics.drawImage(img, x, y, observer);
  }

  @Override
  public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
    // TODO Auto-generated method stub
    return this.graphics.drawImage(img, x, y, width, height, observer);
  }

  @Override
  public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
    // TODO Auto-generated method stub
    return drawImage(img, x, y, bgcolor, observer);
  }

  @Override
  public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
    // TODO Auto-generated method stub
    return drawImage(img, x, y, width, height, bgcolor, observer);
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
    // TODO Auto-generated method stub
    return drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
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
    // TODO Auto-generated method stub
    return drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
  }

  @Override
  public void dispose() {
    this.graphics.dispose();
  }
}
