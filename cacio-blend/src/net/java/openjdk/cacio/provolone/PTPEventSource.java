package net.java.openjdk.cacio.provolone;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import sun.awt.peer.cacio.CacioEventSource;
import sun.awt.peer.cacio.managed.EventData;

public class PTPEventSource implements CacioEventSource {

  private static PTPEventSource instance;

  static PTPEventSource getInstance() {
    if (instance == null) {
      instance = new PTPEventSource();
    }
    return instance;
  }

  private final BlockingQueue<EventData> queue = new LinkedBlockingQueue<EventData>();

  private PTPEventSource() {
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