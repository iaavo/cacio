package net.java.openjdk.cacio.provolone;

import java.awt.Rectangle;

import net.java.openjdk.awt.peer.web.WebGraphicsConfiguration;
import net.java.openjdk.awt.peer.web.WebScreen;
import net.java.openjdk.awt.peer.web.WebSessionState;
import net.java.openjdk.cacio.servlet.WebSessionManager;

public class PTPScreen extends WebScreen {

	private int width;
	private int height;

	protected PTPScreen(WebGraphicsConfiguration config) {
		super(config);

		// TODO 
		WebSessionState state = WebSessionManager.getInstance()
				.getCurrentStateAWT();
		width = state.getInitialScreenDimension().width;
		width = width > 0 ? width : 1;
		height = height > 0 ? height : 1;

		height = state.getInitialScreenDimension().height;

	}

	@Override
	public void resizeScreen(int width, int height) {
		throw new UnsupportedOperationException("The screen size of a running test cannot be changed.");
	}
	
	public Rectangle getBounds() {
		return new Rectangle(0, 0, width, height);
	}
}
