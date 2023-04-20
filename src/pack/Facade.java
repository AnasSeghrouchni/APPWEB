package pack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Pattern;

import javax.ejb.Singleton;

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
	public boolean verifierInscription(String nom, String prenom, String mdp, String mdp2, String email) throws NomException, MailException {
		if (Facade.estNom(nom) && Facade.estNom(prenom) && Facade.estMail(email) && mdp.equals(mdp2)) {
			return true;
		}
		return false;
	}
	
	public void ajouterCompte(Membre c) {
		this.ListeMembre.add(c);
		System.out.println("Add");
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
	
	public void deposerAnnonce(Membre client, float poids, float taille, String photo, String depart, Date date_max, double prix) {
		Colis c = new Colis(poids, taille, photo, client.getAdresse(), depart, date_max, client, prix);
		client.ajouterColis(c);
	}
} 
