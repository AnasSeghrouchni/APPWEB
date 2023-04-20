package pack;
import java.util.Date;

public class Trajet {

	private int id;
	private String depart;
	private String arrivee;
	private Date date_dep;
	private Date date_arr;
	
	public Trajet(int id, String depart, String arrivee, Date date_dep, Date date_arr) {
		super();
		this.id = id;
		this.depart = depart;
		this.arrivee = arrivee;
		this.date_dep = date_dep;
		this.date_arr = date_arr;
	}
}
