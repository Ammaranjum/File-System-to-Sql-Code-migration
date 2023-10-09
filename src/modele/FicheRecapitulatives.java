/** Partie individuelle :Mohamed Camara
 *
 *
 */
package modele;

public class FicheRecapitulatives {
	
	Rapport rapport;
	
	public FicheRecapitulatives(Rapport rapport) {
		this.rapport = rapport;
	}

	public String genererListeClients() {
	    return rapport.getControlleur().genererListeClients();
	}
	
	public String genererListeVehicules() {
		return rapport.getControlleur().genererListeVehicules();
	}
	
	public String genererListeVehiculesDisponibles() {
		return rapport.getControlleur().genererListeVehiculesDisponibles();
	}
	
	public String genererListeVehiculesJamaisLouees() {
		return rapport.getControlleur().genererListeVehiculesJamaisLouees();
	}
	
	public String genererListeVehiculesNonDisponibles() {
		return rapport.getControlleur().genererListeVehiculesNonDisponibles();
	}
	
	public String genererListeLocations() {
		return rapport.getControlleur().genererListeLocations();
	}
	
	public String genererListeLocationsEnCours() {
		return rapport.getControlleur().genererListeLocationsEnCours();
	}
	
}
