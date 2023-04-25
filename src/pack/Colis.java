package pack;
import java.io.ObjectInputFilter.Status;
import java.util.Collection;
import java.util.Date;

public class Colis {

	private float poids;
	private float taille;
	private String photo;
	private String destination;
	private String depart;
	private Date date_max;
	private Membre proprietaire;
	private Collection<Membre> listeLivreurs;
	private double prix;
	private Status status;

	enum Status {
		NON_LIVRE, CHOISI, EN_COURS, LIVRE
	}
	public Colis(Membre proprietaire, float poids, float taille, String photo, String depart, String destination, Date date_max,
			 double prix) {
		super();
		this.proprietaire = proprietaire;
		this.poids = poids;
		this.taille = taille;
		this.photo = photo;
		this.destination = destination;
		this.depart = depart;
		this.date_max = date_max;
		this.prix = prix;
		this.status = Status.NON_LIVRE;
	}
	public void setListeLivreurs(Membre livreur){
		if (!listeLivreurs.contains(livreur)) listeLivreurs.add(livreur);
	}
	public Status getStatusColis(){
		return this.status;
	}
	public void setStatusColis(Status status){
		this.status = status;
	}

	
}