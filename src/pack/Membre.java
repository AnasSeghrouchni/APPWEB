package pack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Membre {

	String prenom;
	String nom;
	String email;
	String mdp;
	String adresse;

	Collection<Colis> ListeColis;
	Collection<Trajet> ListeTrajets;
	HashMap<Membre, ArrayList<String>> ListeAvis;

	
	public Membre(String n, String p, String e, String adr, String m) {
		this.nom = n;
		this.prenom = p;
		this.email = e;
		this.mdp = m;
		this.adresse = adr;
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

	public void ajouterAvis(Membre client, String avis){
		if (ListeAvis.containsKey(client)) ListeAvis.get(client).add(avis);
		else {
			ArrayList<String> a = new ArrayList<String>();
			a.add(avis);
			ListeAvis.put(client, a);
		}
	}
	
}
