/** Partie individuelle :Mohamed Camara
 *
 *
 */
package modele;

public class Client {
	
	/* Définition des attributs */
	private int clientId;
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	private String permis;
	private String carteBank;
	private String username;
	private String motPasse;
	private boolean connecte;
	// actif ou bloqué
	private boolean actif;
	
	private int accompagnantId;
	
	private static int ids = 0;

    /* Constructeurs */
    
	public Client(String nom, String prenom, String adresse, String telephone, String permis, String carteBank,
			String username, String motPasse) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.permis = permis;
		this.carteBank = carteBank;
		this.username = username;
		this.motPasse = motPasse;
		this.clientId = ++ids;
	}
	
	public Client(int id, String nom, String prenom, String adresse, String telephone, String permis, String carteBank,
			String username, String motPasse) {
		this(nom, prenom, adresse, telephone, permis, carteBank, username, motPasse);
		this.clientId = id;
	}
    
    /* getters et setters */


	public static int getIds() {
		return ids;
	}

	public static void setIds(int ids) {
		Client.ids = ids;
	}

	public boolean estActif() {
		return actif;
	}

	public int getId() {
		return clientId;
	}

	public void setId(int clientId) {
		this.clientId = clientId;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPermis() {
		return permis;
	}

	public void setPermis(String permis) {
		this.permis = permis;
	}

	public String getCarteBank() {
		return carteBank;
	}

	public void setCarteBank(String carteBank) {
		this.carteBank = carteBank;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public void bloquer() {
		this.actif = false;
	}
	
	public void activer() {
		this.actif = true;
	}

	public boolean estConnecte() {
		return connecte;
	}
	
	public boolean seConnecter(String username, String pass) {
		if (this.username.equals(username) && this.motPasse.equals(pass)) {
			this.connecte = true;
		}
		return this.connecte;
	}
	
	public void seDeconnecter() {
		this.connecte = false;
	}
	
	public void setAccompagnant(Conducteur c) {
		this.accompagnantId = c.getId();
	}
	
	public int getAccompagnant() {
		return accompagnantId;
	}

	/* implémentation des méthodes */
	@Override
	public String toString() {
		return clientId+", "+nom+", "+prenom+", "+adresse+", "+telephone+", "+permis
				+", "+carteBank+", "+username+", "+motPasse+", "+accompagnantId;
	}
        
	public String description() {
		return "Client "+nom+" "+prenom
				+"\n\tAdresse: "+adresse
				+"\n\tTéléphone: "+telephone
				+"\n\tNuméro de permis: "+permis
				+"\n\tNuméro de carte bancaire: "+carteBank
				+"\n\tUsername: "+username;
	}

}