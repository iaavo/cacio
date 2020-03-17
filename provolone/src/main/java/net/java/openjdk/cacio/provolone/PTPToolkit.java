package net.java.openjdk.cacio.provolone;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.Robot;
import java.awt.peer.RobotPeer;

import sun.awt.peer.cacio.managed.FullScreenWindowFactory;

import net.java.openjdk.awt.peer.web.WebToolkit;

public class PTPToolkit extends WebToolkit {

	public PTPToolkit() {
		super();
	}

	@Override
	public RobotPeer createRobot(Robot target, GraphicsDevice screen)
			throws AWTException {
		return new PTPRobotPeer();
	}

	@Override
	protected int getScreenWidth() {
		return FullScreenWindowFactory.getScreenDimension().width;
	}

	@Override
	protected int getScreenHeight() {
		return FullScreenWindowFactory.getScreenDimension().height;
	}
}
