package net.java.openjdk.cacio.provolone.junit;

import static org.fest.swing.annotation.GUITestFinder.isGUITest;
import static org.fest.swing.junit.runner.Formatter.testNameFrom;

import java.lang.reflect.Method;

import org.fest.swing.junit.runner.FailureScreenshotTaker;
import org.fest.swing.junit.runner.ImageFolderCreator;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class ProvoloneTestRunner extends BlockJUnit4ClassRunner {

    static {
        try {
        	// TODO
//			new ProvoloneServer(8081, 125);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
    }

    class MethodInvoker extends Statement {

        private final FrameworkMethod testMethod;
        private final Object target;
        private final FailureScreenshotTaker screenshotTaker;

        MethodInvoker(FrameworkMethod testMethod, Object target, FailureScreenshotTaker screenshotTaker) {
          this.testMethod= testMethod;
          this.target= target;
          this.screenshotTaker = screenshotTaker;
        }

        public void evaluate() throws Throwable {
          try {
            testMethod.invokeExplosively(target);
          } catch (Throwable t) {
            takeScreenshot();
            throw t;
          }
        }

        private void takeScreenshot() {
          Method realMethod = testMethod.getMethod();
          final Class<?> testClass = realMethod.getDeclaringClass();
          if (!(isGUITest(testClass, realMethod))) return;
          screenshotTaker.saveScreenshot(testNameFrom(testClass, realMethod));
        }
    }

    public ProvoloneTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    /**
     * Returns a <code>{@link Statement}</code> that invokes {@code method} on {@code test}. The created statement will
     * take and save the screenshot of the desktop in case of a failure.
     */
    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
      return new MethodInvoker(method, test, new FailureScreenshotTaker(new ImageFolderCreator().createImageFolder()));
    }
}
