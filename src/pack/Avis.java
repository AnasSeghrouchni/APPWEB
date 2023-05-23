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
	private Membre Auteur;
	@ManyToOne
	private Membre Cible;
	
	
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
		return Auteur;
	}

	public void setAuteur(Membre auteur) {
		Auteur = auteur;
	}

	public Membre getCible() {
		return Cible;
	}

	public void setCible(Membre cible) {
		Cible = cible;
	}
	

}
