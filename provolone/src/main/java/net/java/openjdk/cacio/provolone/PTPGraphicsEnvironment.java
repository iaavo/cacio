package net.java.openjdk.cacio.provolone;

import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.image.BufferedImage;

import net.java.openjdk.awt.peer.web.WebGraphicsEnvironment;
import net.java.openjdk.cacio.ctc.CTCGraphicsDevice;

public class PTPGraphicsEnvironment extends WebGraphicsEnvironment {

	
	boolean rootGraphicsCreated = false;

	@Override
	public Graphics2D createGraphics(BufferedImage img) {
//		System.out.println("Created");
//		if (rootGraphicsCreated) {
			return super.createGraphics(img);
//		} else {
//			rootGraphicsCreated = true;
//			return new PTPGraphics(super.createGraphics(img),
//					PTPScreen.getInstance().tracker);
//		}
	}
	
    @Override
    protected GraphicsDevice makeScreenDevice(int screennum) {
        return new CTCGraphicsDevice();
    }

}
