package pl.najda;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.servlets.EventSource;
import org.eclipse.jetty.servlets.EventSourceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SseEventSourceServlet extends EventSourceServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SseEventSourceServlet.class);

    @Override
    protected EventSource newEventSource(HttpServletRequest request) {
        LOG.info("created SseEventSourceServlet");
        final SseEventSource l = new SseEventSource();
        EventPublisher.addListener(l);
        return l;
    }
}
