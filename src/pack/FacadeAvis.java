package pack;

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

@Path("/avis")
@Singleton
public class FacadeAvis {
	
	@PersistenceContext
	private EntityManager em;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Avis createAvis(Avis avis) {
		em.persist(avis);
		return avis;
	}
	
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Avis getAvisbyId(@PathParam("id") Long id) {
        return em.find(Avis.class, id);
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Avis deleteAvis(@PathParam("id") Long id) {
        Avis avis = em.find(Avis.class, id);
        em.remove(avis);
        return avis;
    }	

}
