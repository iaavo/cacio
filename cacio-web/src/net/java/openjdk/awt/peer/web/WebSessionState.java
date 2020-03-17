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

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.InvocationEvent;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.java.openjdk.cacio.servlet.transport.Transport;
import sun.awt.AppContext;
import sun.awt.SunToolkit;

/**
 * WebSessionState holds, as its name implies, session-related state. It
 * provides access to the AWT "session" represented by AppContexts, from the
 * servlet session.
 * 
 * @author Clemens Eisserer <linuxhippy@gmail.com>
 */
public class WebSessionState {
  private final Logger logger = Logger.getLogger(this.getClass().getName());

  ReentrantLock sessionLock = new ReentrantLock();

  volatile WebEventManager eventManager;

  volatile WebGraphicsConfiguration config;

  volatile WebScreen screen;

  WebWindowFactory windowFactory;

  WebFocusManager focusManager;

  int subSessionID;

  String[] cmdLineParams;

  String mainClsName;

  String keyboard;

  Dimension initialScreenDimension;

  Transport backend;

  int compressLevel;

  AppContext appContext;

  HashMap<Integer, WebSessionState> subSessionMap;

  volatile boolean contextInitialized = false;

  private int os;

  private int browserEngine;

  public WebSessionState(int subSessionID, HashMap<Integer, WebSessionState> subSessionMap) {
    this.subSessionID = subSessionID;
    this.subSessionMap = subSessionMap;
  }

  /**
   * Lock the session lock
   */
  public void lockSession() {
    this.sessionLock.lock();
  }

  /**
   * Unlock the session lock
   */
  public void unlockSession() {
    this.sessionLock.unlock();
  }

  public WebGraphicsConfiguration getGraphicsConfiguration() {
    return this.config;
  }

  /**
   * Set the GraphicsConfiguration, and initialize InputState trackers
   * 
   * @param config
   */
  public void setGraphicsConfiguration(WebGraphicsConfiguration config) {
    this.config = config;
    this.eventManager = new WebEventManager(this);
  }

  public int getSubSessionID() {
    return this.subSessionID;
  }

  public AppContext getAppContext() {
    return this.appContext;
  }

  public void setAppContext(AppContext appContext) {
    this.appContext = appContext;
  }

  public void dispose() {
    if (this.appContext != null) {
      logSessionDispose();

      SunToolkit.postEvent(this.appContext, new InvocationEvent(this, new Runnable(){
        @Override
        public void run() {
          Window[] windowArray = Window.getWindows();
          for (Window w : windowArray) {
            w.dispose();
          }
        }
      }));

      this.appContext.dispose();
    }
    this.screen = null;
    this.windowFactory = null;
    this.focusManager = null;
  }

  private void logSessionDispose() {
    EventQueue queue = (EventQueue)this.appContext.get(AppContext.EVENT_QUEUE_KEY);
    if (queue != null) {
      try {
        Method getDispatchThreadMethod = queue.getClass().getDeclaredMethod("getDispatchThread", new Class[0]);
        getDispatchThreadMethod.setAccessible(true);
        Thread edt = (Thread)getDispatchThreadMethod.invoke(queue, new Object[0]);
        if (edt != null) {
          this.logger.log(Level.INFO, "Shutting down: " + edt.getName());
        }
      } catch (ReflectiveOperationException ex) {
        this.logger.log(Level.WARNING, "Unable to reflectivly accessing the EventQueue's Dispatch Thread", ex);
      }
    }
  }

  public String[] getCmdLineParams() {
    return this.cmdLineParams;
  }

  public void setCmdLineParams(String[] cmdLineParams) {
    this.cmdLineParams = cmdLineParams;
  }

  public String getMainClsName() {
    return this.mainClsName;
  }

  public void setMainClsName(String mainClsName) {
    this.mainClsName = mainClsName;
  }

  public Dimension getInitialScreenDimension() {
    return this.initialScreenDimension;
  }

  public void setInitialScreenDimension(Dimension initialScreenDimension) {
    this.initialScreenDimension = initialScreenDimension;
  }

  public Transport getBackend() {
    return this.backend;
  }

  public void setBackend(Transport backend) {
    this.backend = backend;
  }

  public int getCompressLevel() {
    return this.compressLevel;
  }

  public void setCompressLevel(int compressLevel) {
    this.compressLevel = compressLevel;
  }

  public WebScreen getScreen() {
    return this.screen;
  }

  public void setScreen(WebScreen screen) {
    this.screen = screen;
  }

  public WebWindowFactory getWindowFactory() {
    return this.windowFactory;
  }

  public void setWindowFactory(WebWindowFactory windowFactory) {
    this.windowFactory = windowFactory;
  }

  public WebFocusManager getFocusManager() {
    return this.focusManager;
  }

  public void setFocusManager(WebFocusManager focusManager) {
    this.focusManager = focusManager;
  }

  public WebEventManager getEventManager() {
    return this.eventManager;
  }

  public HashMap<Integer, WebSessionState> getSubSessionMap() {
    return this.subSessionMap;
  }

  public boolean isContextInitialized() {
    return this.contextInitialized;
  }

  public void setContextInitialized(boolean initialized) {
    this.contextInitialized = initialized;
  }

  public String getKeyboard() {
    return this.keyboard;
  }

  public void setKeyboard(String keyboard) {
    this.keyboard = keyboard;
  }

  public void setOS(int os) {
    this.os = os;
  }

  public int getOS() {
    return this.os;
  }

  public void setBrowser(int browserEngine) {
    this.browserEngine = browserEngine;
  }

  public int getBrowser() {
    return this.browserEngine;
  }
}
