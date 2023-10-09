package modele;

public class VehiculePrestige extends Vehicule {

	public VehiculePrestige(String matricule, String marque, String modele, int annee, int kilometrage, int nbrPlaces,
			double prixJournalier) {
		super(matricule, marque, modele, annee, kilometrage, nbrPlaces, prixJournalier);
		typeVehicule = TypeVehicule.PRESTIGE;
		
	}

}
