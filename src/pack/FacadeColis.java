package pack;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/colis")
@Singleton
public class FacadeColis {
    @PersistenceContext
    private EntityManager em;

    @POST
    @Path("/createcolis")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Colis createColis(Colis colis) {
        em.persist(colis);
        return colis;
    }
    
    @GET
    @Path("/getcolisbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Colis getColisbyId(@PathParam("id") Long id) {
        return em.find(Colis.class, id);
    }
    
    @DELETE
    @Path("/deletecolis/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Colis deleteColis(@PathParam("id") Long id) {
        Colis c = em.find(Colis.class, id);
        em.remove(c);
        return c;
    }
    
    @GET
    @Path("/getmembrebycolis")
    @Produces(MediaType.APPLICATION_JSON)
    public Membre getMembrebyColis(Colis c) {
        return c.getProprietaire();
    }
}
