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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/avis")
@Singleton
public class FacadeAvis {
	
	@PersistenceContext
	private EntityManager em;
	
	@POST
	@Path("/createavis")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Avis createAvis(Avis avis) {
		em.persist(avis);
		return avis;
	}
	
    @GET
    @Path("/getavisbyid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Avis getAvisbyId(@PathParam("id") int id) {
        return em.find(Avis.class, id);
    }
    
    @GET
    @Path("/getavis")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getavis(@QueryParam("id") int id) {
    	List<Avis> la = em.createQuery("SELECT a FROM Avis a WHERE a.cible.id = :id", Avis.class)
                .setParameter("id", id)
                .getResultList();
    	return Response.ok(la).build();
    }
    
    @DELETE
    @Path("/deleteavis/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Avis deleteAvis(@PathParam("id") int id) {
        Avis avis = em.find(Avis.class, id);
        em.remove(avis);
        return avis;
    }	

}
