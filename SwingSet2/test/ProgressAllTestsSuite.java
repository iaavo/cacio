import javax.swing.plaf.metal.MetalLookAndFeel;

import net.java.openjdk.cacio.ctc.CTCGraphicsEnvironment;
import net.java.openjdk.cacio.ctc.CTCToolkit;

import org.jenkinsci.testinprogress.runner.ProgressSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(ProgressSuite.class)
@SuiteClasses({ SimpleFESTTest.class })
public class ProgressAllTestsSuite {
	static {
		System.setProperty("awt.toolkit", CTCToolkit.class.getName());
		System.setProperty("java.awt.graphicsenv",
				CTCGraphicsEnvironment.class.getName());
		System.setProperty("swing.defaultlaf", MetalLookAndFeel.class.getName());
		System.setProperty("java.awt.headless", "false");
	}
}