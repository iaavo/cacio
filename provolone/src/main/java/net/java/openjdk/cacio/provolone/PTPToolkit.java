package net.java.openjdk.cacio.provolone;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.Robot;
import java.awt.peer.RobotPeer;

import net.java.openjdk.awt.peer.web.WebToolkit;

public class PTPToolkit extends WebToolkit {

  public PTPToolkit() {
    super();
    setDecorateWindows(true);
    System.setProperty("swing.defaultlaf", "javax.swing.plaf.metal.MetalLookAndFeel");
  }

  @Override
	public RobotPeer createRobot(Robot target, GraphicsDevice screen)
			throws AWTException {
		return new PTPRobotPeer();
	}
    
}
