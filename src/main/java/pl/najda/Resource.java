package pl.najda;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Path("/")
public class Resource {

    private AsyncResponse asyncResponse;

    @Path("publish")
    @GET
    public Response trigger(@QueryParam("msg") String msg) {
        EventPublisher.pub(msg);
        return Response.ok().build();
    }

    @Path("poll")
    @GET
    public void poll(@Suspended final AsyncResponse asyncResponse) {

        asyncResponse.setTimeout(30, TimeUnit.SECONDS);
        this.asyncResponse = asyncResponse;
    }

    @Path("printed")
    @POST
    public Response printCallback(String barcode) {

        this.asyncResponse.resume("MESSAGE");
        return Response.ok().build();
    }
}
