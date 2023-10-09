/** Partie individuelle :MOHAMED CAMARA
 *
 *
 */
package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlleur.AgenceControlleur;
import modele.Vehicule;


public class CatalogueForm extends JPanel implements ActionListener{
	
	private AgenceControlleur controlleur;
	private List<Vehicule> list;
	
	private JTable table;
    private DefaultTableModel tableModel;
    private JTextField rechercheField;
    private JButton rechercheButton, louerButton, reserverButton,  
    		connecterButton, deconnecterButton, retourButton;
	
	public CatalogueForm(AgenceControlleur controlleur, List<Vehicule> list) {
		
		this.controlleur = controlleur;
		this.list = list;
		
		tableModel = new DefaultTableModel(new String[]{"Marque", "Modèle", "Année", "Type", "Nombre de places"}, 0);
        table = new JTable(tableModel);
        JLabel label = new JLabel("Chercher par marque: ");
        rechercheField = new JTextField(20);
        rechercheButton = new JButton("Rechercher");
        reserverButton = new JButton("Reserver");
        louerButton = new JButton("Louer");
        connecterButton = new JButton("Se connecter");
        deconnecterButton = new JButton("Se déconnecter");
        retourButton = new JButton("Retour au menu");
        if (controlleur.getUserIndex() == 0) {
        	connecterButton.setVisible(true);
        	deconnecterButton.setVisible(false);
        	retourButton.setVisible(false);
        } else {
        	connecterButton.setVisible(false);
        	deconnecterButton.setVisible(true);
        	if (controlleur.getUserIndex() == 2)
        		retourButton.setVisible(true);
        	else 
        		retourButton.setVisible(false);
        }
        JPanel entetePanel = new JPanel();
        entetePanel.add(label);
        entetePanel.add(rechercheField);
        entetePanel.add(rechercheButton);
        entetePanel.add(reserverButton);
        entetePanel.add(louerButton);
        entetePanel.add(connecterButton);
        entetePanel.add(deconnecterButton);
        entetePanel.add(retourButton);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(entetePanel, BorderLayout.NORTH);
        
        add(panel);

        rechercheButton.addActionListener(this);
        reserverButton.addActionListener(this);
        louerButton.addActionListener(this);
        connecterButton.addActionListener(this);
        deconnecterButton.addActionListener(this);
        retourButton.addActionListener(this);
        
        // Simulet le click du bouton rechercher
        rechercheButton.doClick();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		int index = controlleur.getUserIndex();
		
		if ( button == rechercheButton) {
			String termRecherche = rechercheField.getText().toLowerCase();
			List<Vehicule> voituresDispo = controlleur.getVehiculesDisponibles();
	        list = controlleur.getVehiculesParMarque(voituresDispo, termRecherche);
	        remplirTable();
		} else if (button == louerButton) {
			int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	        	if (index == 5) {
					JOptionPane.showMessageDialog(null, "Demande enregistrée!");
					
				} else {
					if (controlleur.demanderConnexion() != 0) {
						JOptionPane.showMessageDialog(null, "Demande enregistrée!");
						controlleur.afficherCatalogue();
					}
				}
	        } else {
	        	JOptionPane.showMessageDialog(null, "Aucune sélection!");
	        }
		} else if (button == reserverButton) {
			int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
				if (index == 5) {
					JOptionPane.showMessageDialog(null, "Demande enregistrée!");
					
				} else {
					if (controlleur.demanderConnexion() != 0) {
						JOptionPane.showMessageDialog(null, "Demande enregistrée!");
						controlleur.afficherCatalogue();
					}
				}
	        } else {
	        	JOptionPane.showMessageDialog(null, "Aucune sélection!");
	        }
		} else if (button == connecterButton) {
			
			if (index == 0) {
				int useIndex = controlleur.demanderConnexion();
				if (useIndex != 0) {
					connecterButton.setVisible(false);
					deconnecterButton.setVisible(true);
					switch(useIndex) {
						case 1 : {
							// afficher l'interface de gestion d'utilisateurs (admin)
							controlleur.afficherGestionEmployes();
						}
						break;
						case 2 : {
							// afficher l'interface pour le preposé
							controlleur.afficherPreposeOutils();
						}
						break;
						case 3 : {
							// afficher l'interface pour le manager
							controlleur.afficherAjoutSuppVoiture(false);
						}
						break;
						case 4 : {
							// afficher l'interface pour le gestionnaire
							controlleur.afficherAjoutSuppVoiture(true);
						}
						break;
					}
				}
	        } 
		} else if (button == deconnecterButton) {
			System.out.println(index);
			if (controlleur.getUserIndex() != 0) {
				controlleur.deconnecterUser();
				connecterButton.setVisible(true);	
				deconnecterButton.setVisible(false);				
	        } 
		} else if (button == retourButton) {
			controlleur.afficherPreposeOutils();
		}
	}
	
	public void remplirTable() {
        tableModel.setRowCount(0);
        for (Vehicule v : list) {
            tableModel.addRow(new Object[]{v.getMarque(), v.getModele(), v.getAnnee(), 
            		v.getTypeVehicule().toString(), v.getNbrPlaces()});
        }
    }
	

}
