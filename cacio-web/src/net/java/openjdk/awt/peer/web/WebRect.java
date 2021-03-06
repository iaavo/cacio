/*
 * Copyright (c) 2011, Clemens Eisserer, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package net.java.openjdk.awt.peer.web;

import java.util.List;

/**
 * Lightweight rectangle implementation. WebRect uses x1/y1 x2/y2 coordinates,
 * instead of width/height.
 * 
 * @See java.awt.Rectangle
 * @author Clemens Eisserer <linuxhippy@gmail.com>
 */
public class WebRect {
  int x1, y1, x2, y2;

  /**
   * Construct WebRect with all coordinates set to 0.
   */
  public WebRect() {

  }

  /**
   * Constructs essentially a copy of the passed rect.
   * 
   * @param rect
   */
  public WebRect(WebRect rect) {
    this(rect.x1, rect.y1, rect.x2, rect.y2);
  }

  /**
   * Constructs a WebRect and initializes it with the coordinates passed in.
   * 
   * @param x1
   * @param y1
   * @param x2
   * @param y2
   */
  public WebRect(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  public int getX1() {
    return x1;
  }

  public int getY1() {
    return y1;
  }

  public int getX2() {
    return x2;
  }

  public int getY2() {
    return y2;
  }

  public void setX1(int x1) {
    this.x1 = x1;
  }

  public void setY1(int y1) {
    this.y1 = y1;
  }

  public void setX2(int x2) {
    this.x2 = x2;
  }

  public void setY2(int y2) {
    this.y2 = y2;
  }

  /**
   * Builds a WebRect where all WebRects in rectList are contained in.
   * 
   * @param rectList
   * @return
   */
  public WebRect union(List<WebRect> rectList) {
    if (rectList.size() == 0) {
      return null;
    }

    WebRect unionRect = new WebRect(rectList.get(0));
    for (WebRect rect : rectList) {
      unionRect.union(rect);
    }
    return unionRect;
  }

  /**
   * Sets the coordinates of this rect, so that rect2 is contained.
   * 
   * @param rect2
   */
  public void union(WebRect rect2) {
    x1 = Math.min(x1, rect2.x1);
    y1 = Math.min(y1, rect2.y1);
    x2 = Math.max(x2, rect2.x2);
    y2 = Math.max(y2, rect2.y2);
  }

  /**
   * Shrinks the current WebRect, so that it is completly contained in the
   * corrdinates passed.
   * 
   * @param rx1
   * @param ry1
   * @param rx2
   * @param ry2
   */
  public void restrictToArea(int rx1, int ry1, int rx2, int ry2) {
    x1 = Math.max(x1, rx1);
    y1 = Math.max(y1, ry1);
    x2 = Math.min(x2, rx2);
    y2 = Math.min(y2, ry2);
  }

  /**
   * Tests for intersection with another WebRect.
   * 
   * @param r the "other" WebRect
   * @return True if this intersects the other webrect
   */
  public boolean intersects(WebRect r) {
    return (r.getX2() > getX1()) && (r.getY2() > getY1()) && (getX2() > r.getX1()) && (getY2() > r.getY1());
  }

  public int getWidth() {
    return x2 - x1;
  }

  public int getHeight() {
    return y2 - y1;
  }

  public String toString() {
    return "x1:" + x1 + " y1:" + y1 + " x2:" + x2 + " y2:" + y2 + "   w:" + getWidth() + " h:" + getHeight();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x1;
    result = prime * result + x2;
    result = prime * result + y1;
    result = prime * result + y2;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    WebRect other = (WebRect)obj;
    if (x1 != other.x1) return false;
    if (x2 != other.x2) return false;
    if (y1 != other.y1) return false;
    if (y2 != other.y2) return false;
    return true;
  }
}
