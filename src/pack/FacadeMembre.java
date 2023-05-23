package pack;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@Path("/membre")
@Singleton
public class FacadeMembre {

    @PersistenceContext
    private EntityManager em;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Membre createMembre(Membre membre) {
        em.persist(membre);
        return membre;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Membre getMembrebyId(@PathParam("id") Long id) {
        return em.find(Membre.class, id);
    }

    @PUT
    @Path("/{id_m1}/{id_m2}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Membre updateMembre(@PathParam("id_m1") Long id_m1, @PathParam("id_m2") Long id_m2) {
        Membre ancien_m = em.find(Membre.class, id_m1);
        Membre membre = em.find(Membre.class, id_m2);
        ancien_m.setAdresse(membre.getAdresse());
        ancien_m.setEmail(membre.getEmail());
        ancien_m.setNom(membre.getNom());
        ancien_m.setPrenom(membre.getPrenom());
        em.merge(ancien_m);
        return ancien_m;
    }

    @PUT
    @Path("/{id_membre}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Membre addAvisMembre(@PathParam("id_membre") Long id, Avis avis) {
        Membre membre = em.find(Membre.class, id);
        Collection<Avis> la = membre.getListeAvisRecu();
        la.add(avis);
        membre.setListeAvisRecu(la);
        em.merge(membre);
        return membre;
    }

    @PUT
    @Path("/{id_membre}/{id_colis}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Membre addColisMembre(@PathParam("id_membre") Long id_membre, @PathParam("id_colis") Long id_colis) {
        Membre membre = em.find(Membre.class, id_membre);
        Colis colis = em.find(Colis.class, id_colis);
        Collection<Colis> lc = membre.getListeColis();
        lc.add(colis);
        membre.setListeColis(lc);
        em.merge(membre);
        return membre;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Membre deleteUser(@PathParam("id") Long id) {
        Membre user = em.find(Membre.class, id);
        em.remove(user);
        return user;
    }

    @GET
    @Path("/getallmembres")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Membre> getAllMembres() {
        return em.createQuery("SELECT m FROM Membre m", Membre.class).getResultList();
    }
}