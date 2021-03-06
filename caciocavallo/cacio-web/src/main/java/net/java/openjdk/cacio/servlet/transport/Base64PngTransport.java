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

package net.java.openjdk.cacio.servlet.transport;

import java.awt.image.*;

import java.io.*;
import java.util.*;

import net.java.openjdk.awt.peer.web.*;
import net.java.openjdk.cacio.servlet.imgformat.*;
import sun.misc.*;

/**
 * String-based transport. Encodes the command-list as string and encodes the
 * image-data in png-format applies base64 encoding.
 * 
 * On the client the command-list part and the base64 encoded image-data are
 * split, and the image-data is loaded using data-URIs.
 * 
 * Although less efficient space-wise, quite efficient for devices with weak
 * CPUs, as the image-data has not to be processed using javascript. Base64
 * decoding and data-URI interpretation is usually very fast using native code.
 * 
 * Using this backend a Sony Xperia Arc (Android 2.2, Snapdragon 1Ghz) is able
 * to play Java2Demo almost real-time, while its a slide-show with the RLE based
 * backends due the additional processing required on the client-side.
 * 
 * Javascript counterpart: XHRBase64Transport.js
 * 
 * @author Clemens Eisserer <linuxhippy@gmail.com>
 */
public class Base64PngTransport extends Transport {
    private static byte[] emptyResponseData = "0".getBytes();
    private static String emptyResponseString = "0";

    BASE64Encoder base64Encoder;
    int compressionLevel;

    String cmdString;
    BufferedImage packedImage;

    public Base64PngTransport(int compressionLevel) {
	super("text/plain");

	this.compressionLevel = compressionLevel;
	base64Encoder = new BASE64Encoder();
    }

    /**
     * Encodes the command-list.
     * 
     * @param cmdList
     * @return a string where each integer-value is seperated by a colon.
     */
    protected String encodeImageCmdStream(List<Integer> cmdList) {
	StringBuilder cmdBuilder = new StringBuilder(cmdList.size() * 4);
	cmdBuilder.append(cmdList.size());
	for (int i = 0; i < cmdList.size(); i++) {
	    cmdBuilder.append(':');
	    cmdBuilder.append(cmdList.get(i));
	}

	// Delimeter for following image data
	cmdBuilder.append(':');

	return cmdBuilder.toString();
    }

    @Override
    public void prepareUpdate(List<ScreenUpdate> pendingUpdateList, TreeImagePacker packer, List<Integer> cmdList) {
	dataAvailable = true;
	cmdString = encodeImageCmdStream(cmdList);

	WebRect packedRegionBox = packer.getBoundingBox();
	if (packedRegionBox.getWidth() > 0 && packedRegionBox.getHeight() > 0) {
	    packedImage = new BufferedImage(packedRegionBox.getWidth(), packedRegionBox.getHeight(), BufferedImage.TYPE_INT_RGB);
	    copyUpdatesToPackedImage(pendingUpdateList, packedImage, 0);
	}
    }

    public String asString() {
	if (dataAvailable) {
	    return cmdString + new String(getEncodedImageData());
	} else {
	    return emptyResponseString;
	}
    }

    @Override
    public void writeToStream(OutputStream os) throws IOException {
	if (dataAvailable) {
	    os.write(cmdString.getBytes());
	    os.write(getEncodedImageData());
	} else {
	    os.write(emptyResponseData);
	}
    }

    protected byte[] getEncodedImageData() {
	dataAvailable = false;

	if (packedImage != null) {
	    byte[] bData = PNGEncoder.getInstance().encode(packedImage, compressionLevel);
	    packedImage = null;
	    return Base64Encoder.encode(bData);
	}

	return new byte[0];
    }
}
