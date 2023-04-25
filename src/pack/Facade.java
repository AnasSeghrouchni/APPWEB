package pack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

import javax.ejb.Singleton;

import pack.Colis.Status;

@Singleton
public class Facade { 
	Collection<Membre> ListeMembre = new ArrayList<Membre>();
	
	/**
	 * Verifie si un string est composé uniquement de lettres et d'espaces
	 * @param str le string à vérifier
	 * @return un boolean 
	 * @throws Exceptions 
	 */
	private static boolean estNom(String str) throws NomException {
	    for (int i = 0; i < str.length(); i++) {
	        char c = str.charAt(i);
	        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
	        	throw new NomException("Le nom n'est pas bon, Veuillez réessayer");
	        }
	    }
	    return true;
	}
	
	/**
	 * Vérifie si un string passé en paramètre est dans le format d'une addresse mail
	 * @param str le string à vérifier
	 * @return boolean
	 * @throws MailException 
	 */
	private static boolean estMail(String str) throws MailException {
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		Pattern p = Pattern.compile(regex);
        if (!p.matcher(str).matches()) {
        	throw new MailException("Le format email n'est pas bon");
        };
        return true;
	}
	
	/**
	 * Vérifie si les informations fournies ont un sens
	 * @param nom
	 * @param prenom
	 * @param mdp
	 * @param mdp2
	 * @param email
	 * @return
	 * @throws MailException 
	 * @throws NomException 
	 */
	//Faux. A regarder
	public boolean verifierInscription(String nom, String prenom, String mdp, String mdp2, String email) throws NomException, MailException {
		if (Facade.estNom(nom) && Facade.estNom(prenom) && Facade.estMail(email)) {
			mdp.equals(mdp2);
			
			return true;
		}
		return false;
	}

	public void ajouterCompte( String nom, String prenom, String email, String adresse, String mdp) {
		Membre m = new Membre(nom, prenom, email, adresse, mdp);
		this.ListeMembre.add(m);
		System.out.println("Add "+nom+" "+prenom);
	}
	
	public void changePassword(Membre client, String old_pw, String new_pw) throws IncorrectPWException {
		if (old_pw.equals(client.getPW())) {
			client.setPW(new_pw);
		} else {
			throw new IncorrectPWException("Ancien mot de passe incorrect");
		}
	}
	
	public boolean verifyPassword(Membre client, String pw) {
		return pw.equals(client.getPW());
	}

	public void choisirColis(Membre livreur, Colis colis){
		colis.setListeLivreurs(livreur);
		if (colis.getStatusColis() != Status.CHOISI) colis.setStatusColis(Status.CHOISI);
	}
	// à modifier
	public void supprimerChoixColis(Membre livreur, Colis colis){
		
	}

	public void creerTrajet(Membre createur, String depart, String arrivee, Date date_dep, Date date_arr){
		Trajet t = new Trajet(createur, depart, arrivee, date_dep, date_arr);
	}
	// à modifier
	public void modifierTrajet(Membre createur, String depart, String arrivee, Date date_dep, Date date_arr){
		
	}
	public void supprimerTrajet(Trajet trajet){
		
	}
	
	public void deposerAnnonceColis(Membre client, float poids, float taille, String photo, String depart, String destination, Date date_max, double prix) {
		Colis c = new Colis(client, poids, taille, photo, depart, destination, date_max, prix);
		client.ajouterColis(c);
	}

	// à modifier
	public void modifierAnnonceColis(Membre client, float poids, float taille, String photo, String depart, String destination, Date date_max, double prix) {
		
	}
	public void supprimerAnnonce(Colis colis){
		
	}

	public void donnerAvis(Membre client, Membre livreur, String avis) {
		livreur.ajouterAvis(client, avis);
	}
	public void supprimerAvis(Membre client, Membre livreur, String avis) {
		
	}
	//Pas terminé
	public void confirmerLivreur(Membre client, Colis colis){
		colis.setStatusColis(Status.EN_COURS);
	}
} 
