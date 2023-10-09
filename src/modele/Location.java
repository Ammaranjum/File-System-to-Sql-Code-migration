package modele;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Partie individuelle :COMPAORE YANN DJAMEL
 * la classe Location 
 * Elle repr�sente une location d'une voiture pour un client 
 */
public class Location {
	
	private Date dateLocation;
    private Date dateRetour;
    private Vehicule vehicule;
    private Client client;
    
    private boolean archivee;
		
    /* Constructeurs */
    public Location(Date dateLocation, Date dateRetour, Vehicule vehicule, Client client) {
		this.dateLocation = dateLocation;
		this.dateRetour = dateRetour;
		this.vehicule = vehicule;
		this.client = client;
		this.archivee = false;
    }
    
	    
	public Date getDateLocation() {
		return dateLocation;
	}

	public void setDateLocation(Date dateLocation) {
		this.dateLocation = dateLocation;
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
	
	public double getMontant() {
		// calculer la dur�e de location en millieseconds en utilisant la m�thode getTime
		long dureeMillies = dateRetour.getTime() - dateLocation.getTime();
		// calculer la dur�e de location en jours en utilisant la m�thode convert
		// de la classe TimeUnit.DAYS
		long duree = TimeUnit.DAYS.convert(dureeMillies, TimeUnit.MILLISECONDS);
		
		// multiplier la dur�e par le prix journalier et renvoyer le r�sultat
		return vehicule.getPrixJournalier() * duree;
	}

	/**
	 * m�thode toString pour renvoyer une repr�sentation textuelle
	 * des informations de la location (pour l'�criture dans le fichier)
	 */
	@Override
	public String toString() {
		
		String pattern = "dd-MM-yyyy"; 
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
	    
		return sdf.format(dateLocation)+", "+sdf.format(dateRetour)+", "+vehicule.getMatricule()+", "+client.getId();
	}

	public boolean correspond(Client client, Vehicule v, Date dateLocation) {
		return (client.equals(this.client)
				&& v.equals(this.vehicule)
				&& dateLocation.equals(this.dateLocation));
	}
	
	/**
	 * m�thode pour renvoyer une repr�sentation textuelle
	 * des informations de la location (pour les fiches r�cap)
	 */
	
	public String description() {
		
		String pattern = "dd-MM-yyyy"; 
	    SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
	    
		return "Location"
				+"\n\tVehicule: "+vehicule.getMarque()+", "+vehicule.getMarque()+", "+vehicule.getModele()
				+"\n\tClient: "+client.getNom()+" "+client.getPrenom()
				+"\n\tDate location: "+sdf.format(dateLocation)+", "
				+"\n\tDate pr�vue de retour: "+sdf.format(dateRetour)
				+"\n\tMontant: "+String.format("%.2f", getMontant());
				
	}
	
}