package pack;
import java.util.Date;

public class Trajet {

	//private int id;
	private Membre createur;
	private String depart;
	private String arrivee;
	private Date date_dep;
	private Date date_arr;
	
	public Trajet(Membre createur, String depart, String arrivee, Date date_dep, Date date_arr) {
		super();
		this.createur = createur;
		this.depart = depart;
		this.arrivee = arrivee;
		this.date_dep = date_dep;
		this.date_arr = date_arr;
	}
}
