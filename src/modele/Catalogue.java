package modele;

import java.util.ArrayList;
import java.util.List;
/*
* Partie individuelle :Mohamed Camara
**/
public class Catalogue {

	List<Vehicule> listeVehicules;
	
	public Catalogue() {
		listeVehicules = new ArrayList<>();
	}

	public List<Vehicule> getListeVehicules() {
		return listeVehicules;
	}
	
	public void ajouterVehicule(Vehicule v) {
		if (!listeVehicules.contains(v))
			listeVehicules.add(v);
	}
	
	public void retirerVehicule(Vehicule v) {
		listeVehicules.remove(v);
	}
	
	public List<Vehicule> getVehiculesParMarque(List<Vehicule> list, String termeRecherche) {
		if (termeRecherche.equals(null) || termeRecherche.equals(""))
			return listeVehicules;
		List<Vehicule> resultat = new ArrayList<>();
	    for (Vehicule v : list) {
	        String marque = v.getMarque().toString().toLowerCase();
	        if (marque.contains(termeRecherche.toLowerCase())) {
	        	resultat.add(v);
	        }
	    }
	    return resultat;
	}
	
	public boolean estDisponible(Vehicule v) {
		return listeVehicules.contains(v);
	}
}
