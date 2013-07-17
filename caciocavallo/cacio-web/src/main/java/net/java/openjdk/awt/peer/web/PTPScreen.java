/*
 * Copyright (c) 2011, Clemens Eisserer, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package net.java.openjdk.awt.peer.web;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import net.java.openjdk.awt.peer.web.WebGraphicsConfiguration;
import net.java.openjdk.awt.peer.web.WebScreen;
import net.java.openjdk.cacio.servlet.transport.Transport;
import sun.awt.peer.cacio.managed.FullScreenWindowFactory;

/**
 * Screen Implementation for Caciocavallo-Web.
 * 
 * WebScreen is basically the "hub" where all things come together. Each
 * Caciocavallo-Web session has a single screen, which itself consists of a
 * WebSurfaceData.
 * 
 * @author Clemens Eisserer <linuxhippy@gmail.com>
 */
public class PTPScreen extends WebScreen {

    private static PTPScreen instance;

    public static PTPScreen getInstance() {
        if (instance == null) {
            instance = new PTPScreen();
        }
        return instance;
    }

    private PTPScreen() {
    	super();
    }
    
    public PTPScreen(WebGraphicsConfiguration webGraphicsConfiguration) {
		super(webGraphicsConfiguration);
	}

	@Override
    public WebGraphicsConfiguration getConfig() {
    	return (WebGraphicsConfiguration) getGraphicsConfiguration();
    }

	public GraphicsConfiguration getGraphicsConfiguration() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	}

	public Rectangle getBounds() {
		Dimension d = FullScreenWindowFactory.getScreenDimension();
		return new Rectangle(0, 0, d.width, d.height);
	}
	
	@Override
	public void resizeScreen(int width, int height) {
		throw new UnsupportedOperationException();
	}

}
