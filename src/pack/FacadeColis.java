package pack;

import java.text.SimpleDateFormat;
import java.util.Collection;
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

import org.w3c.dom.stylesheets.MediaList;

import pack.Colis.Status;

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
    
    @POST
    @Path("/proposerlivreur")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Colis proposerlivreur(@QueryParam("id") int id, Membre membre) {
    	Colis c = em.find(Colis.class,  id);
    	Collection<Membre> lm = c.getListePotentielLivreurs();
    	lm.add(membre);
    	c.setListePotentielLivreurs(lm);
    	c.setStatus(Status.CHOISI);
    	em.merge(c);
    	return c;
    }
    
    @GET
    @Path("/getcolisbyid")
    @Produces(MediaType.APPLICATION_JSON)
    public Colis getColisbyId(@QueryParam("id") int id) {
        return em.find(Colis.class, id);
    }
    
    @DELETE
    @Path("/deletecolis/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Colis deleteColis(@PathParam("id") int id) {
        Colis c = em.find(Colis.class, id);
        em.remove(c);
        return c;
    }
    
    @POST
    @Path("/getcolisbydepartarrivee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getcolisdest(Colis colis){
        List<Colis> colisList = em.createQuery("SELECT c FROM Colis c WHERE c.depart = :depart " +
                "AND c.destination = :destination AND c.date_max > :date", Colis.class)
                .setParameter("depart", colis.getDepart())
                .setParameter("destination", colis.getDestination())
                .setParameter("date",colis.getDate_max())
                .getResultList();
        System.out.println(colisList) ;
        return Response.ok(colisList).build();
    }
    
    @GET
    @Path("/getmembrebycolis")
    @Produces(MediaType.APPLICATION_JSON)
    public Membre getMembrebyColis(Colis c) {
        return c.getProprietaire();
    }
}
