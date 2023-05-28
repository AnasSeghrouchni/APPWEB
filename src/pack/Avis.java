package pack;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Avis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	private int etoiles;
	private String commentaire;
	
	@ManyToOne
	private Membre auteur;
	@ManyToOne
	private Membre cible;
	
	
	public Avis() {};
	
	public int getEtoiles() {
		return etoiles;
	}
	public void setEtoiles(int etoiles) {
		this.etoiles = etoiles;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Membre getAuteur() {
		return auteur;
	}

	public void setAuteur(Membre auteur) {
		this.auteur = auteur;
	}

	public Membre getCible() {
		return cible;
	}

	public void setCible(Membre cible) {
		this.cible = cible;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
