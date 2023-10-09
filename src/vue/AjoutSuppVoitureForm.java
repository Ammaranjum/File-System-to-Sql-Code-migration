package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlleur.AgenceControlleur;
import modele.TypeVehicule;
import modele.Vehicule;

public class AjoutSuppVoitureForm extends JPanel implements ActionListener{

	private AgenceControlleur controlleur;
	private List<Vehicule> list;
	
	private JTable table;
    private DefaultTableModel tableModel;
    private JTextField rechercheField;
    JButton rechercheButton, ajouterButton, supprimerButton, sauvegarderButton;
	
	public AjoutSuppVoitureForm(AgenceControlleur controlleur, List<Vehicule> list, boolean estGestionnaire) {
		
		this.controlleur = controlleur;
		this.list = list;
		
		// Créer les composants
        tableModel = new DefaultTableModel(new String[]{"Matricule", "Marque", "Modèle", "Année",
        		"Kilometrage", "Nbr places", "Type", "Prix journalier"}, 0);
        table = new JTable(tableModel);
        JLabel label = new JLabel("Chercher par marque: ");
        rechercheField = new JTextField(20);
        rechercheButton = new JButton("Rechercher");
        ajouterButton = new JButton("Ajouter");
        supprimerButton = new JButton("Retirer");
        sauvegarderButton = new JButton("Sauvegarder");
        ajouterButton.setVisible(estGestionnaire);
        supprimerButton.setVisible(!estGestionnaire);
        JPanel entetePanel = new JPanel();
        entetePanel.add(label);
        entetePanel.add(rechercheField);
        entetePanel.add(rechercheButton);
        entetePanel.add(ajouterButton);
        entetePanel.add(supprimerButton);
        entetePanel.add(sauvegarderButton);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(entetePanel, BorderLayout.NORTH);
        
        add(panel);

        rechercheButton.addActionListener(this);
        ajouterButton.addActionListener(this);
        supprimerButton.addActionListener(this);
        sauvegarderButton.addActionListener(this);
        
        rechercheButton.doClick();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if ( button == rechercheButton) {
			String termRecherche = rechercheField.getText().toLowerCase();
			List<Vehicule> vehiculesDispo = controlleur.getVehiculesDisponibles();
	        list = controlleur.getVehiculesParMarque(vehiculesDispo, termRecherche);
	        remplirTable();
		} else if (button == ajouterButton) {
			ajouterVehicule();
		} else if (button == supprimerButton) {
			int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	        	int result = JOptionPane.showConfirmDialog(this, "Etes-vous sur de vouloir retirer la voiture ?", "Confirmer suppression",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		        if (result == JOptionPane.OK_OPTION) {
		        	String matricule = tableModel.getValueAt(selectedRow, 0).toString();
			        controlleur.supprimerVehicule(matricule);
			        list  = controlleur.getVehicules();
		            remplirTable();
		        }
		        	        	
	        } else {
	        	JOptionPane.showMessageDialog(null, "Aucune sélection!");
	        }
		} else {
			controlleur.ecrireDonnees();
		}
	}
	
	// Méthode pour remplir la table avec la liste de véhicules
    public void remplirTable() {
    	tableModel.setRowCount(0);
        for (Vehicule v : list) {
            tableModel.addRow(new Object[]{v.getMatricule(), v.getMarque(), v.getModele(), 
            						v.getAnnee(), v.getKilometrage(), v.getNbrPlaces(),
            						v.getTypeVehicule().toString(), v.getPrixJournalier()});
        }
    }
    
    private void ajouterVehicule() {
    	String[] fieldNames = {"Matricule", "Marque", "Modèle", "Année", "Kilometrage", "Nbr places", "Prix journalier"};
	    JTextField[] textFields = new JTextField[fieldNames.length];
	    String[] types = new String[TypeVehicule.values().length];
	    for (int i = 0; i < TypeVehicule.values().length; i++) {
	    	types[i] = TypeVehicule.values()[i].toString();
	    }
        JComboBox<String> typeCombo = new JComboBox<>(types);
        JPanel comboPanel = new JPanel();
        comboPanel.add(new JLabel("Type:"));
        comboPanel.add(typeCombo);
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < fieldNames.length; i++) {
            JPanel fieldPanel = new JPanel();
            fieldPanel.add(new JLabel(fieldNames[i] + ":"));
            textFields[i] = new JTextField(10);
            fieldPanel.add(textFields[i]);
            panel.add(fieldPanel);
        }
        panel.add(comboPanel);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Ajout d'une voiture",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String matricule = textFields[0].getText();
            String marque = textFields[1].getText();
            String modele = textFields[2].getText();
            int annee = Integer.parseInt(textFields[3].getText());
            int kilometrage = Integer.parseInt(textFields[4].getText());
            int nbrPlaces = Integer.parseInt(textFields[5].getText());
            double prixJournalier = Double.parseDouble(textFields[6].getText());
            TypeVehicule type = TypeVehicule.valueOf((String)typeCombo.getSelectedItem());
            controlleur.creerVehicule(matricule, marque, modele, annee, kilometrage, nbrPlaces, type, prixJournalier);
            remplirTable();
        }
    }

}
