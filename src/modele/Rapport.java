/** Partie individuelle :COMPAORE YANN
		*
		*
		*/
package modele;

import controlleur.AgenceControlleur;

public class Rapport {

	private FicheRecapitulatives fiches;
	private AgenceControlleur controlleur;
	
	public Rapport(AgenceControlleur controlleur) {
		this.controlleur = controlleur;
		this.fiches = new FicheRecapitulatives(this);
	}

	public FicheRecapitulatives getFiches() {
		return fiches;
	}

	public AgenceControlleur getControlleur() {
		return controlleur;
	}
	
	
}
