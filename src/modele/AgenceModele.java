
package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * la classe qui représente le Modèle dans l'architecture MVC
 */

public class AgenceModele {
	
	/* définition des attributs */
	
	private static final String VEHICULES_FILE = "src/data/vehicules.txt";
	private static final String EMPLOYES_FILE = "src/data/employes.txt";
	private static final String CLIENTS_FILE = "src/data/clients.txt";
	private static final String LOCATIONS_FILE = "src/data/locations.txt";
	private static final String RESERVATIONS_FILE = "src/data/reservations.txt";
	private static final String CONDUCTEURS_FILE = "src/data/conducteurs.txt";
	
	/* les attributs représentant les relations */
	private List<Administrateur> admins;	 
	private List<Prepose> preposes; 	 
	private List<Manager> managers;	 
	private List<Gestionnaire> gestionnaires;	 
	private Catalogue catalogue; 	
	private List<Location> locations;   
	private List<Reservation> reservations;  
	private List<Client> clients;	 
	private List<Conducteur> conducteurs;	 
	private List<Vehicule> vehicules;
	
	private List<String> usernames;
	
	/* Constructeurs */
	
	/**
	 * Constructeur 
	 * 
	 */
	public AgenceModele() {
		
		this.catalogue = new Catalogue();
		
		this.locations = new ArrayList<>();
		this.reservations = new ArrayList<>();
		this.admins = new ArrayList<>();
		this.conducteurs = new ArrayList<>();
		this.gestionnaires = new ArrayList<>();
		this.preposes = new ArrayList<>();
		this.managers = new ArrayList<>();
		this.clients = new ArrayList<>();
		this.vehicules = new ArrayList<>();
		
		this.usernames = new ArrayList<>();
	}
	
	
	
	/* getters et setters */
	
	public Catalogue getCatalogue() {
		return this.catalogue;
	}
	
	public List<Vehicule> getVehicules() {
		return vehicules;
	}

	public List<Administrateur> getAdmins() {
		return admins;
	}

	public List<Prepose> getPreposes() {
		return preposes;
	}

	public List<Manager> getManagers() {
		return managers;
	}

	public List<Gestionnaire> getGestionnaires() {
		return gestionnaires;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public List<Client> getClients() {
		return clients;
	}

	
	/* implémentation des méthodes */

	
	public void creerEmploye(int id, String nom, String prenom, String adresse, String telephone, 
			String username, String motPasse, String fonction) {
		switch (fonction) {
			case "administrateur" : {
				ajouterAdministrateur(new Administrateur(id, nom, prenom , adresse, telephone, username, motPasse));
			}
			break;
			case "prepose" : {
				ajouterPrepose(new Prepose(id, nom, prenom , adresse, telephone, username, motPasse, this));
			}
			break;
			case "manager" : {
				ajouterManager(new Manager(id, nom, prenom , adresse, telephone, username, motPasse, this));
			}
			break;
			case "gestionnaire" : {
				ajouterGestionnaire(new Gestionnaire(id, nom, prenom , adresse, telephone, username, motPasse, this));
			}
			break;		
		}
	}
	
	public void creerEmploye(String nom, String prenom, String adresse, String telephone, 
			String username, String motPasse, String fonction) {
		switch (fonction) {
			case "administrateur" : {
				ajouterAdministrateur(new Administrateur(nom, prenom , adresse, telephone, username, motPasse));
			}
			break;
			case "prepose" : {
				ajouterPrepose(new Prepose(nom, prenom , adresse, telephone, username, motPasse, this));
			}
			break;
			case "manager" : {
				ajouterManager(new Manager(nom, prenom , adresse, telephone, username, motPasse, this));
			}
			break;
			case "gestionnaire" : {
				ajouterGestionnaire(new Gestionnaire(nom, prenom , adresse, telephone, username, motPasse, this));
			}
			break;		
		}
	}
	
	public boolean ajouterAdministrateur(Administrateur emp) {
		if (!admins.contains(emp) && !usernames.contains(emp.getUsername())) {
			admins.add(emp);
			usernames.add(emp.getUsername());
			return true;
		}
		return false;
	}
	
	public boolean ajouterPrepose(Prepose emp) {
		if (!preposes.contains(emp) && !usernames.contains(emp.getUsername())) {
			preposes.add(emp);
			usernames.add(emp.getUsername());
			return true;
		}
		return false;
	}
	
	public boolean ajouterManager(Manager emp) {
		if (!managers.contains(emp) && !usernames.contains(emp.getUsername())) {
			managers.add(emp);
			usernames.add(emp.getUsername());
			return true;
		}
		return false;
	}
	
	public boolean ajouterGestionnaire(Gestionnaire emp) {
		if (!gestionnaires.contains(emp) && !usernames.contains(emp.getUsername())) {
			gestionnaires.add(emp);
			usernames.add(emp.getUsername());
			return true;
		}
		return false;
	}
	
	public void creerVehicule(String matricule, String marque, String modele, int annee, int kilometrage, int nbrPlaces,
			TypeVehicule type, double prixJournalier) {
		switch(type) {
			case SIMPLE : 
				ajouterVehicule(new VehiculeSimple(matricule, marque, modele, annee, kilometrage, nbrPlaces, prixJournalier));
				break;
			case PRESTIGE : 
				ajouterVehicule(new VehiculePrestige(matricule, marque, modele, annee, kilometrage, nbrPlaces, prixJournalier));
				break;
			case UTILITAIRE : 
				ajouterVehicule(new VehiculeUtilitaire(matricule, marque, modele, annee, kilometrage, nbrPlaces, prixJournalier));
				break;
		}
	}
	
	public void ajouterVehicule(Vehicule v) {
		if (!vehicules.contains(v)) {
			vehicules.add(v);
			ajouterAuCatalogue(v);
		}
	}
	
	private void ajouterAuCatalogue(Vehicule v) {
		catalogue.ajouterVehicule(v);
	}
	
	private void retirerDuCatalogue(Vehicule v) {
		catalogue.retirerVehicule(v);
	}
	
	public void retirerVehicule(Vehicule v) {
		retirerDuCatalogue(v);
		vehicules.remove(v);
	}
	
	public boolean ajouterClient(Client client) {
		if (!clients.contains(client) && !usernames.contains(client.getUsername())) {
			clients.add(client);
			usernames.add(client.getUsername());
			return true;
		}
		return false;
	}
	
	public boolean ajouterConducteur(Conducteur c) {
		if (!conducteurs.contains(c)) {
			conducteurs.add(c);
			return true;
		}
		return false;
	}
	
	public void ajouterLocation(Location location) {
		if (!locations.contains(location)) {
			locations.add(location);
			Vehicule v = location.getVehicule();
			v.louer();
			retirerDuCatalogue(v);
		}
	}
	
	public void ajouterReservation(Reservation res) {
		if (!reservations.contains(res)) {
			reservations.add(res);
			Vehicule v = res.getVehicule();
			v.reserver();
			retirerDuCatalogue(v);
		}
	}
	
	public void modifierClient(Client client, String nom, String prenom, String adresse, String telephone, String permis, String carteBank, String username, String motPasse) {
		client.setNom(prenom); 
		client.setPrenom(prenom);
		client.setAdresse(adresse);
		client.setTelephone(telephone);
		client.setPermis(permis);
		client.setCarteBank(carteBank);
		client.setUsername(username);
		client.setMotPasse(motPasse);
		
	}
	
	public void supprimerClient(Client client) {
		clients.remove(client);
	}
	
	public void supprimerAdmin(Administrateur emp) {
		admins.remove(emp);
	}
	
	public void supprimerManager(Manager emp) {
		managers.remove(emp);
	}
	
	public void supprimerPrepose(Prepose emp) {
		preposes.remove(emp);
	}
	
	public void supprimerGestionnaire(Gestionnaire emp) {
		gestionnaires.remove(emp);
	}
	
	public void bloquerClient(Client client) {
		client.bloquer();
	}
	
	public void activerClient(Client client) {
		client.activer();
	}
	
	public Client getClientParUsername(String username) {
		for (Client client : clients) {
			if (client.getUsername().equals(username))
				return client;
		}
		return null;
	}
	
	public Client getClientParID(int id) {
		for (Client client : clients) {
			if (client.getId() == id)
				return client;
		}
		return null;
	}
	
	public Vehicule getVehiculeParMatricule(String matricule) {
		for (Vehicule v : vehicules) {
			if (v.getMatricule().equals(matricule))
				return v;
		}
		return null;
	}

	public List<Vehicule> getVehiculesDisponibles() {		
		return catalogue.getListeVehicules();
	}
	
	public List<Vehicule> getVehiculesParMarque(List<Vehicule> list, String termeRecherche) {
		return catalogue.getVehiculesParMarque(list, termeRecherche);
	}
	
	public boolean estDisponible(Vehicule v) {
		return catalogue.estDisponible(v);
	}
	
	public Location getLocation(Client client, Vehicule v, Date dateLocation) {
		for (Location location : locations) {
			if (location.correspond(client, v, dateLocation))
				return location;
		}
		return null;
	}
	
	public Reservation getReservation(Client client, Vehicule v, Date dateReservation) {
		for (Reservation reservation : reservations) {
			if (reservation.correspond(client, v, dateReservation))
				return reservation;
		}
		return null;
	}

    public List<Location> getLocationsPour(Client client) {
    	
    	List<Location> list = new ArrayList<>();
    	for (int i = 0; i < locations.size(); i++) {
    		if (locations.get(i).getClient().equals(client)) {
    			list.add(locations.get(i));
    		}
        }
    	return list;
    }
    
    public List<Reservation> getReservationsPour(Client client) {
    	
    	List<Reservation> list = new ArrayList<>();
    	for (int i = 0; i < reservations.size(); i++) {
    		if (reservations.get(i).getClient().equals(client)) {
    			list.add(reservations.get(i));
    		}
        }
    	return list;
    }
    
    public void enregistrerLocation(Date dateLocation, Date dateRetour, String matricule, String username) {
		Vehicule v = getVehiculeParMatricule(matricule);
		Client client = getClientParUsername(username);
		if (v != null && client != null)
			ajouterLocation(new Location(dateLocation, dateRetour, v, client));
	}
    
    public void enregistrerReservation(Date dateReservation, Date dateRetour, String matricule, String username) {
    	Vehicule v = getVehiculeParMatricule(matricule);
    	Client client = getClientParUsername(username);
		if (v != null && client != null)
			ajouterReservation(new Reservation(dateReservation, dateRetour, v, client));
	}
    
    public void enregistrerRetourLoc(Date dateLocation, String matricule, String username) {
    	Vehicule v = getVehiculeParMatricule(matricule);
    	Client client = getClientParUsername(username);
		if (v != null && client != null) {
			for (Location location : locations) {
	    		if (!location.estArchivee() && v.equals(location.getVehicule()) 
	    				&& client.equals(location.getClient()) && dateLocation.equals(location.getDateLocation())) {
	    			location.archiver();
	    			v.retourLocation();
	    			ajouterAuCatalogue(v);
	    			break;
	    		}
	    	}
		}
	}
    
    public void enregistrerRetourRes(Date dateReservation, String matricule, String username) {
    	Vehicule v = getVehiculeParMatricule(matricule);
    	Client client = getClientParUsername(username);
		if (v != null && client != null) {
			for (Reservation res : reservations) {
	    		if (!res.estArchivee() && v.equals(res.getVehicule()) 
	    				&& client.equals(res.getClient()) && dateReservation.equals(res.getDateReservation())) {
	    			res.archiver();
	    			v.retourReservation();
	    			ajouterAuCatalogue(v);
	    			break;
	    		}
	    	}
		}
	}
    
    public void genererRapport() {
    	
    }
    
    public void chargerDonnees() {
    	vehicules = new ArrayList<>();  
        try (BufferedReader br = new BufferedReader(new FileReader(VEHICULES_FILE))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] donnees = ligne.split(","); 
                String matricule = donnees[0].trim();  
                String marque = donnees[1].trim(); 
                String modele = donnees[2].trim(); 
                int annee = Integer.parseInt(donnees[3].trim()); 
                int kilometrage = Integer.parseInt(donnees[4].trim());
                int nbrPlaces = Integer.parseInt(donnees[5].trim()); 
                TypeVehicule type = TypeVehicule.valueOf(donnees[6].trim()); 
                double prixJournalier = Double.parseDouble(donnees[7].trim()); 
                creerVehicule(matricule, marque, modele, annee, kilometrage, nbrPlaces, type, prixJournalier);                 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
     
        admins = new ArrayList<>();
        preposes = new ArrayList<>();
        managers = new ArrayList<>();
        gestionnaires = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(EMPLOYES_FILE))) {
            String ligne;
            int[] nbr = new int[4];
            if((ligne = br.readLine()) != null) {
            	String[] donnees = ligne.split(",");
            	for (int i=0; i<4; i++) {
            		nbr[i] = Integer.parseInt(donnees[i].trim());
            	}
            }
            while ((ligne = br.readLine()) != null) {
                String[] donnees = ligne.split(","); 
                int id = Integer.parseInt(donnees[0].trim());  
                String nom = donnees[1].trim(); 
                String prenom = donnees[2].trim();
                String adresse = donnees[3].trim();
                String telephone = donnees[4].trim();
                String username = donnees[5].trim(); 
                String motPasse = donnees[6].trim();
                String fonction = donnees[7].trim();
                creerEmploye(id, nom, prenom, adresse, telephone, username, motPasse, fonction);
            }
            Administrateur.setIds(nbr[0]);
            Prepose.setIds(nbr[1]);
            Manager.setIds(nbr[2]);
            Gestionnaire.setIds(nbr[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        clients = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(CLIENTS_FILE))) {
            String ligne;
            int nbr = 0;
            if((ligne = br.readLine()) != null)
            	nbr = Integer.parseInt(ligne.trim()); 
            while ((ligne = br.readLine()) != null) {
                String[] donnees = ligne.split(","); 
                int id = Integer.parseInt(donnees[0].trim());  
                String nom = donnees[1].trim(); 
                String prenom = donnees[2].trim();
                String adresse = donnees[3].trim();
                String telephone = donnees[4].trim();
                String permis = donnees[5].trim();
                String carteBank = donnees[6].trim();
                String username = donnees[7].trim(); 
                String motPasse = donnees[8].trim();
                int conductId = Integer.parseInt(donnees[9].trim());
                Client client = new Client(id, nom, prenom, adresse, telephone, permis, carteBank, username, motPasse);
                ajouterClient(client);
            }
            Client.setIds(nbr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        String pattern = "dd-MM-yyyy"; 
        SimpleDateFormat sdf = new SimpleDateFormat(pattern); 

        locations = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(LOCATIONS_FILE))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] donnees = ligne.split(",");
                try {
                    Date dateDebut = sdf.parse(donnees[0].trim()); 
                    Date dateFin = sdf.parse(donnees[1].trim());
                    String matricule = donnees[2].trim();
                    int id = Integer.parseInt(donnees[3].trim());
                    Vehicule v = getVehiculeParMatricule(matricule);
                    Client client = getClientParID(id);
                    Location location = new Location(dateDebut, dateFin, v, client);
                    ajouterLocation(location); 
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        reservations = new ArrayList<>();  
        try (BufferedReader br = new BufferedReader(new FileReader(RESERVATIONS_FILE))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] donnees = ligne.split(",");
                try {
                    Date dateDebut = sdf.parse(donnees[0].trim()); 
                    Date dateFin = sdf.parse(donnees[1].trim());
                    String matricule = donnees[2].trim();
                    int id = Integer.parseInt(donnees[3].trim());
                    Vehicule v = getVehiculeParMatricule(matricule);
                    Client client = getClientParID(id);
                    Reservation res = new Reservation(dateDebut, dateFin, v, client);
                    ajouterReservation(res); 
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        conducteurs = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(CONDUCTEURS_FILE))) {
            String ligne;
            int nbr = 0;
            if((ligne = br.readLine()) != null)
            	nbr = Integer.parseInt(ligne.trim()); 
            while ((ligne = br.readLine()) != null) {
                String[] donnees = ligne.split(","); 
                int id = Integer.parseInt(donnees[0].trim());  
                String nom = donnees[1].trim(); 
                String prenom = donnees[2].trim();
                String adresse = donnees[3].trim();
                String telephone = donnees[4].trim();
                Conducteur conducteur = new Conducteur(id, nom, prenom, adresse, telephone);
                ajouterConducteur(conducteur); 
            }
            Conducteur.setIds(nbr);
        } catch (IOException e) {
            e.printStackTrace();
        } 
		
    }
    
    public void ecrireDonnees() {
    	try {
            FileWriter fileWriter = new FileWriter(VEHICULES_FILE);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Vehicule v : vehicules) {
            	String line = v.toString();
            	printWriter.println(line);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	try {
            FileWriter fileWriter = new FileWriter(EMPLOYES_FILE);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String nbr = String.valueOf(Administrateur.getIds());
            nbr += ", "+ String.valueOf(Prepose.getIds());
            nbr += ", "+ String.valueOf(Manager.getIds());
            nbr += ", "+ String.valueOf(Gestionnaire.getIds());
            printWriter.println(nbr);
                	
            for (Administrateur emp : admins) {
            	String line = emp.toString()+", administrateur";
            	printWriter.println(line);
            }
            for (Prepose emp : preposes) {
            	String line = emp.toString()+", prepose";
            	printWriter.println(line);
            }
            for (Manager emp : managers) {
            	String line = emp.toString()+", manager";
            	printWriter.println(line);
            }
            for (Gestionnaire emp : gestionnaires) {
            	String line = emp.toString()+", gestionnaire";
            	printWriter.println(line);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	try {
            FileWriter fileWriter = new FileWriter(CLIENTS_FILE);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String nbr = String.valueOf(Client.getIds());
            printWriter.println(nbr);
            for (Client client : clients) {
            	String line = client.toString();
            	printWriter.println(line);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	try {
            FileWriter fileWriter = new FileWriter(CONDUCTEURS_FILE);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            String nbr = String.valueOf(Conducteur.getIds());
            printWriter.println(nbr);
            for (Conducteur c : conducteurs) {
            	String line = c.toString();
            	printWriter.println(line);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	try {
            FileWriter fileWriter = new FileWriter(LOCATIONS_FILE);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Location location : locations) {
            	String line = location.toString();
            	printWriter.println(line);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    	try {
            FileWriter fileWriter = new FileWriter(RESERVATIONS_FILE);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Reservation res : reservations) {
            	String line = res.toString();
            	printWriter.println(line);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
