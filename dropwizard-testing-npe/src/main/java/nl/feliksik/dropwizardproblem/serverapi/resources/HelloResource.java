package nl.feliksik.dropwizardproblem.serverapi.resources;

import javax.validation.Valid;
import javax.ws.rs.*;

@Path("/mypath")
public class HelloResource {

    @POST
    @Consumes({"application/json"})
    public String echo(@Valid String body) {
        return body;
    }

    @GET
    @Produces({"application/json"})
    public String sayHi() {
        return "hi";
    }
}
