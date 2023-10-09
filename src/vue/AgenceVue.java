package vue;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlleur.AgenceControlleur;
import modele.Vehicule;

public class AgenceVue extends JFrame {
	
	private AgenceControlleur controlleur;
	
	private CatalogueForm catalogueForm;
	private EmployeForm employeForm;
	private AjoutSuppVoitureForm ajoutSuppVoitureForm;
	private PreposeForm preposeForm;
	private ClientsForm clientsForm;
	private FichesRecapForm fichesRecapForm;
	
	public void setControlleur(AgenceControlleur controlleur) {
		this.controlleur = controlleur;	
	}

	public AgenceVue(AgenceControlleur controlleur) {
		
		this.controlleur = controlleur;
		this.controlleur.setVue(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gestion de location de voitures");

        JPanel entete = new JPanel();
        JLabel enteteLabel = new JLabel("Gestion de location de voitures");
        entete.add(enteteLabel);
        
        getContentPane().add(entete, BorderLayout.NORTH);
        
        afficherCatalogue(this.controlleur.getVehiculesDisponibles());

        pack();
        setLocationRelativeTo(null); 
        setVisible(true);
	}
	
	public void afficherCatalogue(List<Vehicule> list) {
		catalogueForm = new CatalogueForm(this.controlleur, list);
		if (this.getContentPane().countComponents()>1)
			this.getContentPane().remove(1);
		this.getContentPane().add(catalogueForm, BorderLayout.CENTER);
		pack();
	}
	
	public void afficherGestionEmployes() {
		employeForm = new EmployeForm(this.controlleur);
		if (this.getContentPane().countComponents()>1)
			this.getContentPane().remove(1);
		this.getContentPane().add(employeForm, BorderLayout.CENTER);
		pack();		
	}
	
	public void afficherAjoutSuppVoiture(boolean estGestionnaire) {
		ajoutSuppVoitureForm = new AjoutSuppVoitureForm(controlleur, controlleur.getVehicules(), estGestionnaire);
		if (this.getContentPane().countComponents()>1)
			this.getContentPane().remove(1);
		this.getContentPane().add(ajoutSuppVoitureForm, BorderLayout.CENTER);
		pack();	
	}
	
	public void afficherPreposeOutils() {
		preposeForm = new PreposeForm(controlleur);
		if (this.getContentPane().countComponents()>1)
			this.getContentPane().remove(1);
		this.getContentPane().add(preposeForm, BorderLayout.CENTER);
		pack();	
	}
	
	public void afficherDossiersClients() {
		clientsForm = new ClientsForm(controlleur);
		if (this.getContentPane().countComponents()>1)
			this.getContentPane().remove(1);
		this.getContentPane().add(clientsForm, BorderLayout.CENTER);
		pack();	
	}
	
	public void afficherFichesRecaps() {
		fichesRecapForm = new FichesRecapForm(controlleur);
		if (this.getContentPane().countComponents()>1)
			this.getContentPane().remove(1);
		this.getContentPane().add(fichesRecapForm, BorderLayout.CENTER);
		pack();	
	}
	
	public int demanderConnexion() {
		int index, result = 0;
		do {
			index = 0;
			JLabel userLabel = new JLabel("Username:");
			JLabel passLabel = new JLabel("Mot de passe:");
		    
			JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        
	        JPanel fieldPanel1 = new JPanel();
	        JPanel fieldPanel2 = new JPanel();
	        fieldPanel1.add(userLabel);
	        fieldPanel2.add(passLabel);
	        JTextField userField = new JTextField(10);
	        JTextField passField = new JTextField(10);
	        fieldPanel1.add(userField);
	        fieldPanel2.add(passField);
	        panel.add(fieldPanel1);
	        panel.add(fieldPanel2);
	            
	        result = JOptionPane.showConfirmDialog(this, panel, "Connexion",
	                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	
	        if (result == JOptionPane.OK_OPTION) {
	            String username = userField.getText();
	            String motPasse = passField.getText();
	            index = controlleur.connecterUser(username, motPasse);
	            if (index == 0) {
	            	JOptionPane.showMessageDialog(null, "Informations de connexion incorrectes!");
	    		}
	            else
	            	break;
	        } 
		} while (result == JOptionPane.OK_OPTION);
		
		return index;
	}

}
