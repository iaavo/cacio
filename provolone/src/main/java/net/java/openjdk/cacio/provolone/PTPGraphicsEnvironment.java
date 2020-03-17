package net.java.openjdk.cacio.provolone;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import net.java.openjdk.awt.peer.web.WebGraphicsEnvironment;

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
}
