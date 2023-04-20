package pack;

import java.util.Collection;

public class Membre {

	String prenom;
	String nom;
	String email;
	String mdp;
	String adresse;

	Collection<Colis> ListeColis;
	Collection<Trajet> ListeTrajets;
	
	public Membre(String p, String n, String e,String m) {
		this.prenom = p;
		this.nom = n;
		this.email = e;
		this.mdp = m;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public String getPW() {
		return mdp;
	}

	public void setPW(String mdp) {
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
	
}
