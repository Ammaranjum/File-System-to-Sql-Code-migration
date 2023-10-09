package modele;

public class Conducteur {

	/* Définition des attributs */
	private int conducteurId;
	private String nom;
	private String prenom;
	private String adresse;
	private String telephone;
	
	private static int ids = 0;

    /* Constructeurs */
    
	public Conducteur(String nom, String prenom, String adresse, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.conducteurId = ++ids;
	}
	
	public Conducteur(int id, String nom, String prenom, String adresse, String telephone) {
		this(nom, prenom, adresse, telephone);
		this.conducteurId  = id;
	}
    
    /* getters et setters */


	public static int getIds() {
		return ids;
	}

	public static void setIds(int ids) {
		Conducteur.ids = ids;
	}

	public int getId() {
		return conducteurId;
	}

	public void setId(int conducteurId) {
		this.conducteurId = conducteurId;
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

	public void accompagneClient(Client client) {
		client.setAccompagnant(this);
	}
	
	
	/* implémentation des méthodes */
	@Override
	public String toString() {
		return conducteurId+", "+nom+", "+prenom+", "+adresse+", "+telephone;
	}
        

}
