package sun.awt.peer.cacio.basic;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import sun.awt.peer.cacio.CacioEventSource;
import sun.awt.peer.cacio.managed.EventData;

public class BasicEventSource implements CacioEventSource {

  private static BasicEventSource instance;

  static BasicEventSource getInstance() {
    if (instance == null) {
      instance = new BasicEventSource();
    }
    return instance;
  }

  private final BlockingQueue<EventData> queue = new LinkedBlockingQueue<EventData>();

  private BasicEventSource() {
    // Singleton.
  }

  @Override
  public EventData getNextEvent() throws InterruptedException {
    return this.queue.take();
  }

  void postEvent(EventData ev) {
    this.queue.offer(ev);
  }
}
