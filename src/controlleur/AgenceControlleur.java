package controlleur;

import java.util.Date;
import java.util.List;

import modele.Administrateur;
import modele.AgenceModele;
import modele.Client;
import modele.Conducteur;
import modele.Gestionnaire;
import modele.Location;
import modele.Manager;
import modele.Prepose;
import modele.Reservation;
import modele.TypeVehicule;
import modele.Vehicule;
import vue.AgenceVue;

public class AgenceControlleur {

	private AgenceModele modele;
	private AgenceVue vue;
	private Administrateur admin;
	private Prepose prepose;
	private Manager manager;
	private Gestionnaire gestionnaire;
	private Client client;
	
	public AgenceControlleur() {
        this.modele = new AgenceModele();
        chargerDonnees();
        this.vue = new AgenceVue(this);
        
    }
	
	public void setVue(AgenceVue vue) {
		this.vue = vue;
	}

	public Administrateur getAdmin() {
		return admin;
	}

	public void setAdmin(Administrateur admin) {
		this.admin = admin;
		connecter(1);
	}

	public Prepose getPrepose() {
		return prepose;
	}

	public void setPrepose(Prepose prepose) {
		this.prepose = prepose;
		connecter(2);
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
		connecter(3);
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
		connecter(4);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
		connecter(5);
	}

	public int getUserIndex() {
		if (admin != null)
			return 1;
		if (prepose != null)
			return 2;
		if (manager != null)
			return 3;
		if (gestionnaire != null)
			return 4;
		if (client != null)
			return 5;
		return 0;
	}
	
	private void connecter(int index) {
		switch(index) {
			case 1 : {
				prepose = null;
				manager = null;
				gestionnaire = null;
				client = null;
			}
			break;
			case 2 : {
				admin = null;
				manager = null;
				gestionnaire = null;
				client = null;
			}
			break;
			case 3 : {
				prepose = null;
				admin = null;
				gestionnaire = null;
				client = null;
			}
			break;
			case 4 : {
				prepose = null;
				manager = null;
				admin = null;
				client = null;
			}
			break;
			case 5 : {
				admin = null;
				prepose = null;
				manager = null;
				gestionnaire = null;
			}
			break;
			case 0 : {
				admin = null;
				prepose = null;
				manager = null;
				gestionnaire = null;
				client = null;
			}
			break;			
		}
	}

	//Mohamed Camara
	public void afficherCatalogue() {
		vue.afficherCatalogue(modele.getVehiculesDisponibles());
	}
	
	public void afficherGestionEmployes() {
		vue.afficherGestionEmployes();
	}
	
	public void afficherAjoutSuppVoiture(boolean estGestionnaire) {
		vue.afficherAjoutSuppVoiture(estGestionnaire);
	}
	
	public void afficherPreposeOutils() {
		vue.afficherPreposeOutils();
	}
	
	public void afficherDossiersClients() {
		vue.afficherDossiersClients();
	}
	
	public void afficherFichesRecaps() {
		vue.afficherFichesRecaps();
	}
	
	public List<Vehicule> getVehicules() {
		return modele.getVehicules();
	}
	
	public List<Vehicule> getVehiculesDisponibles() {
		return modele.getVehiculesDisponibles();
	}
	
	public List<Vehicule> getVehiculesParMarque(List<Vehicule> list, String termeRecherche) {
		return modele.getVehiculesParMarque(list, termeRecherche);
	}
	
	public Client getClientParUsername(String username) {
		return modele.getClientParUsername(username);
	}
	
	public void ajouterVehicule(Vehicule v) {
		modele.ajouterVehicule(v);
	}
	
	public void supprimerVehicule(String matricule) {
		Vehicule v = modele.getVehiculeParMatricule(matricule);
		modele.retirerVehicule(v);
	}
	
	public void creerEmploye(int id, String nom, String prenom, String adresse, String telephone, 
			String username, String motPasse, String fonction) {
		modele.creerEmploye(id, nom, prenom, adresse, telephone, username, motPasse, fonction);
	}
	
	public void creerEmploye(String nom, String prenom, String adresse, String telephone, 
			String username, String motPasse, String fonction) {
		modele.creerEmploye(nom, prenom, adresse, telephone, username, motPasse, fonction);
	}
	
	public boolean ajouterAdministrateur(Administrateur emp) {
		return modele.ajouterAdministrateur(emp);
	}
	
	public boolean ajouterPrepose(Prepose emp) {
		return modele.ajouterPrepose(emp);
	}
	
	public boolean ajouterManager(Manager emp) {
		return modele.ajouterManager(emp);
	}
	
	public boolean ajouterGestionnaire(Gestionnaire emp) {
		return modele.ajouterGestionnaire(emp);
	}
	
	public void creerVehicule(String matricule, String marque, String modele, int annee, int kilometrage, int nbrPlaces,
			TypeVehicule type, double prixJournalier) {
		this.modele.creerVehicule(matricule, marque, modele, annee, kilometrage, nbrPlaces, type, prixJournalier);
	}
	
	public void retirerVehicule(Vehicule v) {
		this.modele.retirerVehicule(v);
	}
	
	public boolean ajouterClient(Client client) {
		return modele.ajouterClient(client);
	}
	
	public boolean ajouterConducteur(Conducteur c) {
		return modele.ajouterConducteur(c);
	}
	
	public void ajouterLocation(Location location) {
		modele.ajouterLocation(location);
	}
	
	public void ajouterReservation(Reservation res) {
		modele.ajouterReservation(res);
	}
	
	public void modifierClient(String ancienUsername, String nom, String prenom, String adresse, String telephone, String permis, String carteBank, String username, String motPasse) {
		Client client = getClientParUsername(ancienUsername);
		modele.modifierClient(client, nom, prenom, adresse, telephone, permis, carteBank, username, motPasse);
		
	}
	
	public void supprimerAdmin(Administrateur emp) {
		modele.supprimerAdmin(emp);
	}
	
	public void supprimerManager(Manager emp) {
		modele.supprimerManager(emp);
	}
	
	public void supprimerPrepose(Prepose emp) {
		modele.supprimerPrepose(emp);
	}
	
	public void supprimerGestionnaire(Gestionnaire emp) {
		modele.supprimerGestionnaire(emp);
	}
	
	public void supprimerEmploye(String username) {
		for (Administrateur emp : modele.getAdmins()) {
			if (emp.getUsername().equals(username)) {
				supprimerAdmin(emp);
				break;
			}
		}
		for (Prepose emp : modele.getPreposes()) {
			if (emp.getUsername().equals(username)) {
				supprimerPrepose(emp);
				break;
			}
		}
		for (Manager emp : modele.getManagers()) {
			if (emp.getUsername().equals(username)) {
				supprimerManager(emp);
				break;
			}
		}
		for (Gestionnaire emp : modele.getGestionnaires()) {
			if (emp.getUsername().equals(username)) {
				supprimerGestionnaire(emp);
				break;
			}
		}
	}
	
	public void supprimerClient(String username) {
		Client client = getClientParUsername(username);
		modele.supprimerClient(client);
	}
	
	public void bloquerClient(String username) {
		Client client = modele.getClientParUsername(username);
		modele.bloquerClient(client);
	}
	
	public void activerClient(String username) {
		Client client = modele.getClientParUsername(username);
		modele.activerClient(client);
	}
	
	public void activerClient(Client client) {
		modele.activerClient(client);
	}
	
	public Client getClientParID(int id) {
		return modele.getClientParID(id);
	}
	
	public int connecterUser(String username, String motPasse) {
		for (Administrateur emp : modele.getAdmins()) {
			if (emp.seConnecter(username, motPasse)) {
				setAdmin(emp);
				return 1;
			}
		}
		for (Prepose emp : modele.getPreposes()) {
			if (emp.seConnecter(username, motPasse)) {
				setPrepose(emp);
				return 2;
			}
		}
		for (Manager emp : modele.getManagers()) {
			if (emp.seConnecter(username, motPasse)) {
				setManager(emp);
				return 3;
			}
		}
		for (Gestionnaire emp : modele.getGestionnaires()) {
			if (emp.seConnecter(username, motPasse)) {
				setGestionnaire(emp);
				return 4;
			}
		}
		for (Client client : modele.getClients()) {
			if (client.seConnecter(username, motPasse)) {
				setClient(client);
				return 5;
			}
		}
		return 0;
	}
	
	public void deconnecterUser() {
		connecter(0);
	}
	
	public int demanderConnexion() {
		return vue.demanderConnexion();
	}
	
	public void chargerDonnees() {
		modele.chargerDonnees();
	}
	
	public void ecrireDonnees() {
		modele.ecrireDonnees();
	}
	
	public boolean vehiculeOccupee(Vehicule v, Date dateDebut, Date dateFin) {
		for (Location loc : modele.getLocations()) {
			// vérifier si il y a une location en cours attribuée au vehicule
			if (loc.estAttribuer(v)) {
				// vérifier si les deux intervalles du temps s'intersectent
				if ((dateDebut.before(loc.getDateRetour()) && dateDebut.after(loc.getDateLocation()))
							|| (dateFin.before(loc.getDateRetour()) && dateFin.after(loc.getDateLocation()))) {
					return true;
				}
			}
		}
		for (Reservation res : modele.getReservations()) {
			// vérifier si il y a une réservation en cours attribuée au vehicule
			if (res.estAttribuer(v)) {
				// vérifier si les deux intervalles du temps s'intersectent
				if ((dateDebut.before(res.getDateRetour()) && dateDebut.after(res.getDateReservation()))
							|| (dateFin.before(res.getDateRetour()) && dateFin.after(res.getDateReservation()))) {
					return true;
				}
			}
		}
		return false;
	}

	//COMPAORE YANN DJAMEL
	public boolean enregistrerLocation(Date dateDebut, Date dateFin, String matricule, String username) {
		Vehicule v = modele.getVehiculeParMatricule(matricule);
		if (vehiculeOccupee(v, dateDebut, dateFin))
			return false;
		modele.enregistrerLocation(dateDebut, dateFin, matricule, username);
		return true;
	}
	
	public boolean enregistrerReservation(Date dateDebut, Date dateFin, String matricule, String username) {
		Vehicule v = modele.getVehiculeParMatricule(matricule);
		if (vehiculeOccupee(v, dateDebut, dateFin))
			return false;
		modele.enregistrerReservation(dateDebut, dateFin, matricule, username);
		return true;
	}
	
	public int enregistrerRetourLoc(Date dateDebut, String matricule, String username) {
		Client client = modele.getClientParUsername(username);
		Vehicule v = modele.getVehiculeParMatricule(matricule);
		Location loc = modele.getLocation(client, v, dateDebut);
		if (loc == null)
			return 0;
		if (loc.estArchivee())
			return -1;
		modele.enregistrerRetourLoc(dateDebut, matricule, username);
		return 1;
	}
	
	public int enregistrerRetourRes(Date dateDebut, String matricule, String username) {
		Client client = modele.getClientParUsername(username);
		Vehicule v = modele.getVehiculeParMatricule(matricule);
		Reservation res = modele.getReservation(client, v, dateDebut);
		if (res == null)
			return 0;
		if (res.estArchivee())
			return -1;
		modele.enregistrerRetourRes(dateDebut, matricule, username);
		return 1;
	}

	public List<Administrateur> getAdmins() {
		return modele.getAdmins();
	}
	
	public List<Prepose> getPreposes() {
		return modele.getPreposes();
	}
	
	public List<Manager> getManagers() {
		return modele.getManagers();
	}
	
	public List<Gestionnaire> getGestionnaires() {
		return modele.getGestionnaires();
	}
	
	public List<Client> getClients() {
		return modele.getClients();
	}
	
	public String genererListeClients() {
		StringBuilder sb = new StringBuilder("La liste des clients\n\n");
	    for (Client client : modele.getClients()) {
	         sb.append(client.description() + "\n");
	    }
	    return sb.toString();
	}
	
	public String genererListeVehicules() {
		StringBuilder sb = new StringBuilder("La liste des vehicules\n\n");
	    for (Vehicule vehicule : modele.getVehicules()) {
	         sb.append(vehicule.description() + "\n");
	    }
	    return sb.toString();
	}
	
	public String genererListeVehiculesDisponibles() {
		StringBuilder sb = new StringBuilder("La liste des vehicules disponibles\n\n");
	    for (Vehicule vehicule : modele.getVehiculesDisponibles()) {
	         sb.append(vehicule.description() + "\n");
	    }
	    return sb.toString();
	}
	
	public String genererListeVehiculesJamaisLouees() {
		StringBuilder sb = new StringBuilder("La liste des vehicules jamais louées\n\n");
		for (Vehicule v : modele.getVehicules()) {
			boolean trouvee = false;
		    for (Location loc : modele.getLocations()) {
		         if (loc.estAttribuer(v)) {
		        	 trouvee = true;
		        	 break;
		         }
		    }
		    if (!trouvee) 
		    	sb.append(v.description() + "\n");
		}
	    return sb.toString();
	}
	
	public String genererListeVehiculesNonDisponibles() {
		StringBuilder sb = new StringBuilder("La liste des vehicules non disponibles\n\n");
		for (Vehicule v : modele.getVehicules()) {
			boolean trouvee = false;
		    for (Vehicule v1 : modele.getVehiculesDisponibles()) {
		         if (v1 == v) {
		        	 trouvee = true;
		        	 break;
		         }
		    }
		    if (!trouvee) 
		    	sb.append(v.description() + "\n");
		}
	    return sb.toString();
	}
	
	public String genererListeLocations() {
		StringBuilder sb = new StringBuilder("La liste des locations\n\n");
		double total = 0;
		for (Location loc : modele.getLocations()) {
			sb.append(loc.description() + "\n");
			total += loc.getMontant();
		}
		sb.append(String.format("\nTotal : %.2f\n", total));
	    return sb.toString();
	}
	
	public String genererListeLocationsEnCours() {
		StringBuilder sb = new StringBuilder("La liste des locations en cours\n\n");
		double total = 0;
		for (Location loc : modele.getLocations()) {
			if (!loc.estArchivee()) {
				sb.append(loc.description() + "\n");
				total += loc.getMontant();
			}
		}
		sb.append(String.format("\nTotal : %.2f\n", total));
	    return sb.toString();
	}
}
