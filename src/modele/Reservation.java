package modele;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

	private Date dateReservation;
    private Date dateRetour;
    private Vehicule vehicule;
    private Client client;
    
    private boolean archivee;
		
    /* Constructeurs */
    public Reservation(Date dateReservation, Date dateRetour, Vehicule vehicule, Client client) {
		this.dateReservation = dateReservation;
		this.dateRetour = dateRetour;
		this.vehicule = vehicule;
		this.client = client;
		this.archivee = false;
    }
    
	    
	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public boolean estAttribuer(Vehicule vehicule) {
		return this.vehicule == vehicule;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void archiver() {
		this.archivee = true;
	}
	
	public boolean estArchivee() {
		return archivee;
	}
	
	/**
	 * méthode toString pour renvoyer une représentation textuelle
	 * des informations de la réservation (pour le fichier)
	 */
	@Override
	public String toString() {
		
		String pattern = "dd-MM-yyyy"; 
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
	    
		return sdf.format(dateReservation)+", "+sdf.format(dateRetour)+", "+vehicule.getMatricule()+", "+client.getId();
	}

	public boolean correspond(Client client, Vehicule v, Date dateLocation) {
		return (client.equals(this.client)
				&& v.equals(this.vehicule)
				&& dateLocation.equals(this.dateReservation));
	}
}
