package pl.najda;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

// https://github.com/jetty-project/jetty-eventsource-servlet/wiki
// https://jersey.java.net/documentation/latest/sse.html
// http://www.w3.org/TR/eventsource/

// FOR SSE:
// 1.) curl localhost:8080/sse -H"Accept: text/event-stream"
// 2.) curl localhost:8080/publish?msg=HelloWorld

// FOR LONG-POLLING
// 1.) curl localhost:8080/poll
// 2.) curl -XPOST localhost:8080/printed

class SseRelayApplication extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new SseRelayApplication().run(args);
    }

    @Override
    public String getName() {
        return "sse-relay";
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(new Resource());
        environment.servlets().addServlet("sseServlet", SseEventSourceServlet.class).addMapping("/sse");
    }
}
