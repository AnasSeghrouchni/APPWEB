package pack;

import java.util.Collection;

public class Membre {

	String prenom;
	String nom;
	String email;
	String mdp;
	Collection<Colis> ListeColis;
	
	public Membre(String p, String n, String e,String m) {
		this.prenom = p;
		this.nom = n;
		this.email = e;
		this.mdp = m;
	}
	
	public void changePassword(String old_pw, String new_pw) throws IncorrectPWException {
		if (old_pw.equals(this.mdp)) {
			this.mdp = new_pw;
		} else {
			throw new IncorrectPWException("Ancien mot de passe incorrect");
		}
	}
	
	public boolean verifyPassword(String pw) {
		return pw.equals(this.mdp);
	}
	
	
}
