package net.java.openjdk.cacio.provolone;

import net.java.openjdk.awt.peer.web.WebGraphicsConfiguration;
import net.java.openjdk.awt.peer.web.WebScreen;

public class PTPScreen extends WebScreen {
	private static PTPScreen instance;

	protected PTPScreen(WebGraphicsConfiguration config) {
		super(config);
	}

	public static PTPScreen getInstance() {
		if (instance == null)
			instance = new PTPScreen(null);
		return instance;
	}
}
