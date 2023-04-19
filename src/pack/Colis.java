package pack;
import java.util.Date;

public class Colis {

	private float poids;
	private float taille;
	private String photo;
	private String destination;
	private String depart;
	private Date date_max;
	private Compte client;

	public Colis(float poids, float taille, String photo, String destination, String depart, Date date_max,
			Compte client) {
		super();
		this.poids = poids;
		this.taille = taille;
		this.photo = photo;
		this.destination = destination;
		this.depart = depart;
		this.date_max = date_max;
		this.client = client;
	}
}