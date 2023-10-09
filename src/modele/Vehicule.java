package modele;

/**
 * la classe Vehicule 
 */

public class Vehicule {

	/* définition des attributs */
	protected String matricule;
	protected String marque;
	protected String modele;
	protected int annee;
	protected int kilometrage;
	protected int nbrPlaces;
	protected TypeVehicule typeVehicule;
	protected double prixJournalier;
	
	protected boolean louee;
	protected boolean reservee;
	
	/* Constructeurs */
	public Vehicule(String matricule, String marque, String modele, int annee, int kilometrage, int nbrPlaces,
			double prixJournalier) {
		this.matricule = matricule;
		this.marque = marque;
		this.modele = modele;
		this.annee = annee;
		this.kilometrage = kilometrage;
		this.nbrPlaces = nbrPlaces;
		this.prixJournalier = prixJournalier;
	}
	
	/* getters et setters */
	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}

	public int getNbrPlaces() {
		return nbrPlaces;
	}

	public void setNbrPlaces(int nbrPlaces) {
		this.nbrPlaces = nbrPlaces;
	}

	public TypeVehicule getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(TypeVehicule typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

	public double getPrixJournalier() {
		return prixJournalier;
	}

	public void setPrixJournalier(double prixJournalier) {
		this.prixJournalier = prixJournalier;
	}
	
	public boolean estLouee() {
		return louee;
	}
	
	public void louer() {
		this.louee = true;
	}
	
	public void retourLocation() {
		this.louee = false;
	}
	
	public boolean estReservee() {
		return reservee;
	}
	
	public void reserver() {
		this.reservee = true;
	}
	
	public void retourReservation() {
		this.reservee = false;
	}

	/* implémentation des méthodes */
	/**
	 * méthode toString pour renvoyer une représentation textuelle
	 * des informations du véhicule
	 */
	@Override
	public String toString() {
		
		return matricule+", "+marque+", "+modele+", "+annee+", "+kilometrage
				+", "+nbrPlaces+", "+typeVehicule.toString()+", "+prixJournalier;
	}	
	
	public String description() {
		return "Véhicule"
				+"\n\tMatricule: "+matricule
				+"\n\tMarque: "+marque
				+"\n\tModèle: "+modele
				+"\n\tAnnée: "+annee
				+"\n\tKilometrage: "+kilometrage
				+"\n\tNombre de places: "+nbrPlaces
				+"\n\tType: "+typeVehicule.toString()
				+"\n\tPrix de location/jour: "+prixJournalier;
	}
}
