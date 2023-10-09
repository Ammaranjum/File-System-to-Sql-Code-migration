package modele;

public class VehiculeSimple extends Vehicule {

	public VehiculeSimple(String matricule, String marque, String modele, int annee, int kilometrage, int nbrPlaces,
			double prixJournalier) {
		super(matricule, marque, modele, annee, kilometrage, nbrPlaces, prixJournalier);
		typeVehicule = TypeVehicule.SIMPLE;
		
	}

}
