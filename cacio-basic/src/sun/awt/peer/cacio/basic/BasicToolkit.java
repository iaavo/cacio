package sun.awt.peer.cacio.basic;

import sun.awt.peer.cacio.PlatformWindowFactory;
import sun.awt.peer.cacio.managed.FullScreenWindowFactory;

/**
 * @author $Author$
 * @version $Revision$ ($Date$)
 */
public class BasicToolkit extends BasicToolkitAdapter {
  private PlatformWindowFactory platformWindowFactory;

  public BasicToolkit() {
    super();
    setDecorateWindows(true);
  }

  @Override
  public PlatformWindowFactory getPlatformWindowFactory() {
    if (this.platformWindowFactory == null) {
      BasicScreen screen = BasicScreen.getInstance();
      BasicEventSource eventSource = BasicEventSource.getInstance();
      this.platformWindowFactory = new FullScreenWindowFactory(screen, eventSource);
    }
    return this.platformWindowFactory;
  }

}
