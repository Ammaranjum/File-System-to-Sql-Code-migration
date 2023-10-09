package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import com.sun.source.doctree.EndElementTree;

import controlleur.AgenceControlleur;
import modele.Administrateur;
import modele.Gestionnaire;
import modele.Manager;
import modele.Prepose;

public class EmployeForm extends JPanel implements ActionListener{

	private AgenceControlleur controlleur;
	
	private JTable table;
    private DefaultTableModel tableModel;
    JButton ajouterButton, supprimerButton, sauvegarderButton;
	
	public EmployeForm(AgenceControlleur controlleur) {
		
		this.controlleur = controlleur;
		
		tableModel = new DefaultTableModel(new String[]{"Nom", "Prénom", "Username", "Mot de passe", "Fonction"}, 0);
        table = new JTable(tableModel);
        ajouterButton = new JButton("Ajouter");
        supprimerButton = new JButton("Supprimer");
        sauvegarderButton = new JButton("Sauvegarder");
        JPanel entetePanel = new JPanel();
        entetePanel.add(ajouterButton);
        entetePanel.add(supprimerButton);
        entetePanel.add(sauvegarderButton);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(entetePanel, BorderLayout.NORTH);
        

        add(panel);

        ajouterButton.addActionListener(this);
        supprimerButton.addActionListener(this);
        sauvegarderButton.addActionListener(this);
        
        remplirTable();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if (button == ajouterButton) {
			ajouterUtilisateur();
		} else if (button == supprimerButton) {
			int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	        	int result = JOptionPane.showConfirmDialog(this, "Etes-vous sur de vouloir supprimer l'employé ?", "Confirmer suppression",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		        if (result == JOptionPane.OK_OPTION) {
		        	String username = tableModel.getValueAt(selectedRow, 2).toString();
		        	controlleur.supprimerEmploye(username);
		            remplirTable();
		        }
		        	        	
	        } else {
	        	JOptionPane.showMessageDialog(null, "Aucune sélection!");
	        }
		} else {
			controlleur.ecrireDonnees();
		}
	}
	
	public void remplirTable() {
        tableModel.setRowCount(0);
        for (Administrateur emp : controlleur.getAdmins()) {
        	tableModel.addRow(new Object[]{emp.getNom(), emp.getPrenom(), 
        			emp.getUsername(), emp.getMotPasse(), "Administrator"});
		}
		for (Prepose emp : controlleur.getPreposes()) {
			tableModel.addRow(new Object[]{emp.getNom(), emp.getPrenom(), 
        			emp.getUsername(), emp.getMotPasse(), "Préposé"});
		}
		for (Manager emp : controlleur.getManagers()) {
			tableModel.addRow(new Object[]{emp.getNom(), emp.getPrenom(), 
        			emp.getUsername(), emp.getMotPasse(), "Manager"});
		}
		for (Gestionnaire emp : controlleur.getGestionnaires()) {
			tableModel.addRow(new Object[]{emp.getNom(), emp.getPrenom(), 
        			emp.getUsername(), emp.getMotPasse(), "Gestionnaire"});
		}
    }
	
	private void ajouterUtilisateur() {
		String[] fieldNames = {"Nom", "Prénom", "Adresse", "Téléphone", "Username", "Mot de passe"};
	    JTextField[] textFields = new JTextField[fieldNames.length];
	    String[] types = {"administrateur", "prepose", "manager", "gestionnaire"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        JPanel comboPanel = new JPanel();
        comboPanel.add(new JLabel("Fonction:"));
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
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Ajout d'un employé",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nom = textFields[0].getText();
            String prenom = textFields[1].getText();
            String username = textFields[2].getText();
            String adresse = textFields[3].getText();
            String telephone = textFields[4].getText();
            String motPasse = textFields[5].getText();
            String fonction = (String)typeCombo.getSelectedItem();
            controlleur.creerEmploye(nom, prenom, adresse, telephone, username, motPasse, fonction);
            remplirTable();
        }
	}

}
