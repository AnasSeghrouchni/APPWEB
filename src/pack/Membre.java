package pack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import javax.persistence.*
;

@Entity
public class Membre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String prenom;
	String nom;
	String email;
	String mdp;
	String adresse;
	
	@OneToMany
	Collection<Colis> ListeColis;
	
	@ManyToMany
	Collection<Colis> ListeColisLivreur;
	
	@OneToMany(mappedBy="Cible")
	Collection<Avis> ListeAvisRecu;
	
	@OneToMany(mappedBy="Auteur")
	Collection<Avis> ListeAvisRedige;
	

	public Membre() {}
	

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Collection<Colis> getListeColisLivreur() {
		return ListeColisLivreur;
	}


	public void setListeColisLivreur(Collection<Colis> listeColisLivreur) {
		ListeColisLivreur = listeColisLivreur;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public void ajouterColis(Colis colis) {
		ListeColis.add(colis);
	}
	

	public Collection<Colis> getListeColis() {
		return ListeColis;
	}


	public void setListeColis(Collection<Colis> listeColis) {
		ListeColis = listeColis;
	}


	public Collection<Avis> getListeAvisRecu() {
		return ListeAvisRecu;
	}


	public void setListeAvisRecu(Collection<Avis> listeAvisRecu) {
		ListeAvisRecu = listeAvisRecu;
	}


	public Collection<Avis> getListeAvisRedige() {
		return ListeAvisRedige;
	}


	public void setListeAvisRedige(Collection<Avis> listeAvisRedige) {
		ListeAvisRedige = listeAvisRedige;
	}

	
}
