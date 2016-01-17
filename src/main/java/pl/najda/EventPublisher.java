package pl.najda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class EventPublisher {
    private static final Logger LOG = LoggerFactory.getLogger(EventPublisher.class);
    private static final List<SseEventSource> LISTENERS = Collections.synchronizedList(new ArrayList<>());

    public static void pub(final String message) {
        LOG.info("pushing: " + message);
        synchronized (LISTENERS) {
            LISTENERS.stream().forEach(
                    listener -> {
                        try {
                            listener.emitEvent(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
    }

    public static void addListener(SseEventSource l) {
        LISTENERS.add(l);
    }

    public static void removeListener(SseEventSource l) {
        LISTENERS.remove(l);
    }
}
