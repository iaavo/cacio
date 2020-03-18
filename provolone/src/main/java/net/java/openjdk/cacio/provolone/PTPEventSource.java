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

    private BlockingQueue<EventData> queue = new LinkedBlockingQueue<EventData>();

    private PTPEventSource() {
        // Singleton.
    }

    @Override
    public EventData getNextEvent() throws InterruptedException {
        return queue.take();
    }

    void postEvent(EventData ev) {
        queue.offer(ev);
    }
}
