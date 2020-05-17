package psw.rest;
import psw.ejb.ArticoloSessionBean;
import psw.model.Articolo;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/articoloRS")
@RequestScoped
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class ArticoloRS {
    @EJB
    ArticoloSessionBean aEJB;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(final Articolo articolo) {
        Articolo a = aEJB.inserisciArticolo(articolo);
        return Response.ok(a).build();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    public Response findById(@PathParam("id") final Long id) {
        Articolo art = aEJB.find(id);
        if (art == null) {
            System.out.println("QUI");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(art).build();
    }

    @GET
    public Response listAll(@QueryParam("start") final Integer startPosition,
                            @QueryParam("max") final Integer maxResult) {
        //TODO: retrieve the prodottoes
        final List<Articolo> articoli = aEJB.findAllArticoli(startPosition, maxResult);
        return Response.ok(articoli).build();
    }

    @PUT
    @Path("/{id:[0-9][0-9]*}")
    public Response update(@PathParam("id") Long id, final Articolo articolo) {
        Articolo p = aEJB.aggiorna(articolo);
        return Response.ok(articolo).build();
    }

    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deleteById(@PathParam("id") final Long id) {
        Articolo a = aEJB.find(id);
        if (a!=null)
            aEJB.remove(a);
        return Response.noContent().build();
    }

    @OPTIONS
    @Path("{path : .*}")
    public Response options() {
        return Response.ok("")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .build();
    }
}
