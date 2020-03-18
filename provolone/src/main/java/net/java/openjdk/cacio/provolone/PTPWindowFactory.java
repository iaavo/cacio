package net.java.openjdk.cacio.provolone;

import java.awt.GraphicsConfiguration;

import net.java.openjdk.awt.peer.web.WebGraphicsConfiguration;
import sun.awt.peer.cacio.managed.FullScreenWindowFactory;
import sun.awt.peer.cacio.managed.PlatformScreen;
import sun.awt.peer.cacio.managed.PlatformScreenSelector;

public class PTPWindowFactory extends FullScreenWindowFactory {

	public PTPWindowFactory() {
		super(new SessionScreenSelector(), null);
	}

	private static final class SessionScreenSelector implements
			PlatformScreenSelector {
		@Override
		public PlatformScreen getPlatformScreen(GraphicsConfiguration config) {
			return ((WebGraphicsConfiguration) config).getScreen();
		}
	}
}
