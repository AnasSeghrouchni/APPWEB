package pack;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Colis {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String nom;
	private float poids;
	private float taille;
	private String photo;
	private String destination;
	private String depart;
	private String date_max;
	
	@ManyToOne
	private Membre proprietaire;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Membre> listePotentielLivreurs;
	private double prix;
	private Status status;

	enum Status {
		NON_LIVRE, CHOISI, EN_COURS, LIVRE
	}
	
	public Colis() {};
	
	public void setListeLivreurs(Membre livreur){
		if (!listePotentielLivreurs.contains(livreur)) listePotentielLivreurs.add(livreur);
	}
	public Status getStatusColis(){
		return this.status;
	}
	public void setStatusColis(Status status){
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public float getPoids() {
		return poids;
	}
	public void setPoids(float poids) {
		this.poids = poids;
	}
	public float getTaille() {
		return taille;
	}
	public void setTaille(float taille) {
		this.taille = taille;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public String getDate_max() {
		return date_max;
	}
	public void setDate_max(String date_max) {
		this.date_max = date_max;
	}
	public Membre getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Membre proprietaire) {
		this.proprietaire = proprietaire;
	}
	public Collection<Membre> getListePotentielLivreurs() {
		return listePotentielLivreurs;
	}
	public void setListePotentielLivreurs(Collection<Membre> listePotentielLivreurs) {
		this.listePotentielLivreurs = listePotentielLivreurs;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}