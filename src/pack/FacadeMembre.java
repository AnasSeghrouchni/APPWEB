package pack;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Path("/membre")
@Singleton
public class FacadeMembre {

    @PersistenceContext
    private EntityManager em;

    @POST
    @Path("/createMembre")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMembre(Membre membre) {
    	String email = membre.getEmail();
    	List<Membre> membres = em.createQuery("SELECT m FROM Membre m WHERE m.email = :email", Membre.class)
        						.setParameter("email", email)
        						.getResultList();
    	if (!membres.isEmpty()) {
    		return Response.status(Response.Status.UNAUTHORIZED).build();
    	}
    	em.persist(membre);
        return Response.ok(membre).build();
    }
    
    @POST
    @Path("/connect")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response connectMembre(Membre membre) {
    	Membre exMembre = em.createQuery("SELECT m FROM Membre m WHERE m.email = :mail", Membre.class)
    						.setParameter("mail", membre.getEmail())
    						.getSingleResult();
    	if(exMembre == null || !exMembre.getMdp().equals(membre.getMdp())) {
    		return Response.status(Response.Status.UNAUTHORIZED).build();
    	}
    	return Response.ok(exMembre).build();
    }
    
    
    @GET
    @Path("/getmembrebyid")
    @Produces(MediaType.APPLICATION_JSON)
    public Membre getMembrebyId(@QueryParam("id") int id) {
        return em.find(Membre.class, id);
    }
    
    @PUT
    @Path("/updatemembre")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMembre(@QueryParam("id") int memberId, Membre membre) {
    	Membre ancien_m = em.find(Membre.class, memberId);
    	System.out.println("");
    	System.out.println("member id = ");
    	System.out.println(memberId);
    	if (ancien_m == null) { 
    		System.out.println("Ancien_m = null");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    	
    	String email_ancien = ancien_m.getEmail();
    	String emailnew = membre.getEmail();
    	if (!email_ancien.equals(emailnew)) {
    		List<Membre> membres = em.createQuery("SELECT m FROM Membre m WHERE m.email = :email", Membre.class)
					.setParameter("email", emailnew)
					.getResultList();
			if (!membres.isEmpty()) {
	    		System.out.println("Adress = adress");
				return Response.status(Response.Status.UNAUTHORIZED).build();
			}
    	}
        ancien_m.setAdresse(membre.getAdresse());
        ancien_m.setEmail(membre.getEmail());
        ancien_m.setNom(membre.getNom());
        ancien_m.setPrenom(membre.getPrenom());
        ancien_m.setMdp(membre.getMdp());
        em.merge(ancien_m);
        return Response.ok(ancien_m).build();
    	
    }
    
    @PUT
    @Path("/addavis/{id_membre}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Membre addAvisMembre(@PathParam("id_membre") int id, Avis avis) {
        Membre membre = em.find(Membre.class, id);
        Collection<Avis> la = membre.getListeAvisRecu();
        la.add(avis);
        membre.setListeAvisRecu(la);
        em.merge(membre);
        return membre;
    }

    @PUT
    @Path("/addcolis/{id_membre}/{id_colis}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Membre addColisMembre(@PathParam("id_membre") int id_membre, @PathParam("id_colis") int id_colis) {
        Membre membre = em.find(Membre.class, id_membre);
        Colis colis = em.find(Colis.class, id_colis);
        Collection<Colis> lc = membre.getListeColis();
        lc.add(colis);
        membre.setListeColis(lc);
        em.merge(membre);
        return membre;
    }

    @DELETE
    @Path("deleteuser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Membre deleteUser(@PathParam("id") int id) {
        Membre user = em.find(Membre.class, id);
        em.remove(user);
        return user;
    }

    @GET
    @Path("/getallmembres")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Membre> getAllMembres() {
        return em.createQuery("select m FROM Membre m", Membre.class).getResultList();
    }
    
    @GET
    @Path("/getallcolis")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Colis> getAllColis(@QueryParam("id") int id) {
    	Membre m = em.find(Membre.class, id);
        return m.getListeColis();
    }
    
//    @GET
//    @Path("/getallcolislivreur/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Collection<Colis> getAllColisLivreur(@PathParam("id") int id) {
//    	Membre m = em.find(Membre.class, id);
//        return m.getListeColisLivreur();
//    }
    
    @GET
    @Path("/getallavisrecu")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Avis> getAllAvisRecu(@QueryParam("id") int id) {
    	Membre m = em.find(Membre.class, id);
        return m.getListeAvisRecu();
    }
    
    @GET
    @Path("/getallavisredige/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Avis> getAllAvisRedige(@PathParam("id") int id) {
    	Membre m = em.find(Membre.class, id);
        return m.getListeAvisRedige();
    }
}
