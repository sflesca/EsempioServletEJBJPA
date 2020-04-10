package psw.rest;


import psw.ejb.CategoriaBean;
import psw.model.Categoria;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import java.util.Collection;

// The Java class will be hosted at the URI path "/helloworld"
@RequestScoped
@Path("/categoriars")
@Produces({ "application/json" })
@Consumes({ "application/json" })
public class CategoriaRS {

    @EJB
    CategoriaBean ctEJB;

    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    public Collection<Categoria> getCategorie() {
        // Return some cliched textual content
        return ctEJB.findAll();
    }
}
