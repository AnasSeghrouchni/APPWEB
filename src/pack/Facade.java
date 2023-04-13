package pack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import javax.ejb.Singleton;

@Singleton
public class Facade { 
	Collection<Compte> ListeCompte = new ArrayList<Compte>();
	
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
	
	public void ajouterCompte(Compte c) {
		this.ListeCompte.add(c);
		System.out.println("Add");
	}
	

} 
