package pack;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/colis")
@Singleton
public class FacadeColis {
    @PersistenceContext
    private EntityManager em;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Colis createColis(Colis colis) {
        em.persist(colis);
        return colis;
    }
    
    
}
