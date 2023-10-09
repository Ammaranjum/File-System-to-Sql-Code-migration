package vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controlleur.AgenceControlleur;
import modele.Client;


public class ClientsForm extends JPanel implements ActionListener{

	private AgenceControlleur controlleur;
	
	private JTable table;
    private DefaultTableModel tableModel;
    JButton ajouterButton, modifierButton, supprimerButton, bloquerClientButton, 
    			debloquerClientButton, retourButton;
	
	public ClientsForm(AgenceControlleur controlleur) {
		
		this.controlleur = controlleur;
		
		tableModel = new DefaultTableModel(new String[]{"Nom", "Prénom", "Adresse", "Téléphone", 
        		"Permis", "CarteBank", "Username", "Mot de passe"}, 0);
        table = new JTable(tableModel);
        ajouterButton = new JButton("Ajouter");
        modifierButton = new JButton("Mettre à jour");
        supprimerButton = new JButton("Supprimer");
        bloquerClientButton = new JButton("Bloquer");
        debloquerClientButton = new JButton("Debloquer");
        retourButton = new JButton("Retour");
		
        JPanel entetePanel = new JPanel();
        entetePanel.add(ajouterButton);
        entetePanel.add(modifierButton);
        entetePanel.add(supprimerButton);
        entetePanel.add(bloquerClientButton);
        entetePanel.add(debloquerClientButton);
        entetePanel.add(retourButton);
        
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(entetePanel, BorderLayout.NORTH);
        
        add(panel);

        ajouterButton.addActionListener(this);
        supprimerButton.addActionListener(this);
        modifierButton.addActionListener(this);
        bloquerClientButton.addActionListener(this);
        debloquerClientButton.addActionListener(this);
        retourButton.addActionListener(this);
        remplirTable();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if (button == ajouterButton) {
			inscrireClient();
			remplirTable();
		} else if (button == bloquerClientButton) {
			bloquerClient();
			remplirTable();
		} else if (button == debloquerClientButton) {
			debloquerClient();
			remplirTable();
		} else if (button == supprimerButton) {
			int selectedRow = table.getSelectedRow();
	        if (selectedRow != -1) {
	        	int result = JOptionPane.showConfirmDialog(this, "Etes-vous sur de vouloir supprimer le client ?", "Confirmer suppression",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		        if (result == JOptionPane.OK_OPTION) {
		        	String username = tableModel.getValueAt(selectedRow, 6).toString();
		        	controlleur.supprimerClient(username);
		            remplirTable();
		        }
		        	        	
	        } else {
	        	JOptionPane.showMessageDialog(null, "Aucune sélection!");
	        }
		} else if (button == modifierButton) {
			majClient();
			remplirTable();
		} else if (button == retourButton) {
			controlleur.afficherPreposeOutils();
		}
	}
	
	public void remplirTable() {
        tableModel.setRowCount(0);
        for (Client client : controlleur.getClients()) {
        	tableModel.addRow(new Object[]{client.getNom(), client.getPrenom(), client.getAdresse(), 
        			client.getTelephone(), client.getPermis(), client.getCarteBank(),
        			client.getUsername(), client.getMotPasse()});
		}
    }
    
    public void inscrireClient() {
		String[] fieldNames = {"Nom", "Prénom", "Adresse", "Téléphone", "Num Permis", "Carte bancaire", 
					"Username", "Mot de passe"};
	    JTextField[] textFields = new JTextField[fieldNames.length];
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (int i = 0; i < fieldNames.length; i++) {
            JPanel fieldPanel = new JPanel();
            fieldPanel.add(new JLabel(fieldNames[i] + ":"));
            textFields[i] = new JTextField(10);
            fieldPanel.add(textFields[i]);
            panel.add(fieldPanel);
        }
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Ajout d'un client",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nom = textFields[0].getText();
            String prenom = textFields[1].getText();
            String adresse = textFields[2].getText();
            String telephone = textFields[3].getText();
            String permis = textFields[4].getText();
            String carteBank = textFields[5].getText();
            String username = textFields[6].getText();
            String motPasse = textFields[7].getText();
            Client client = new Client(nom, prenom, adresse, telephone, permis, carteBank, username, motPasse);
            controlleur.ajouterClient(client);
        }

	}
	
	public void bloquerClient() {
		int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String username = tableModel.getValueAt(selectedRow, 6).toString();
		    controlleur.bloquerClient(username);
        } else {
        	JOptionPane.showMessageDialog(null, "Aucune sélection!");
        }
		
	}
	
	public void debloquerClient() {
		int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String username = tableModel.getValueAt(selectedRow, 6).toString();
		    controlleur.activerClient(username);
        } else {
        	JOptionPane.showMessageDialog(null, "Aucune sélection!");
        }
	}
	
	public void majClient() {
		int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
			String[] fieldNames = {"Nom", "Prénom", "Adresse", "Téléphone", 
					"Permis", "CarteBank", "Username", "Mot de passe"};
			String ancienUsername = tableModel.getValueAt(selectedRow, 6).toString();
		    JTextField[] textFields = new JTextField[fieldNames.length];
			JPanel panel = new JPanel();
	        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	        for (int i = 0; i < fieldNames.length; i++) {
	            JPanel fieldPanel = new JPanel();
	            fieldPanel.add(new JLabel(fieldNames[i] + ":"));
	            textFields[i] = new JTextField(10);
	            textFields[i].setText(tableModel.getValueAt(selectedRow, i).toString());
	            fieldPanel.add(textFields[i]);
	            panel.add(fieldPanel);
	        }
	        
	        int result = JOptionPane.showConfirmDialog(this, panel, "Mise à jour d'un client",
	                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

	        if (result == JOptionPane.OK_OPTION) {
	            String nom = textFields[0].getText();
	            String prenom = textFields[1].getText();
	            String adresse = textFields[2].getText();
	            String telephone = textFields[3].getText();
	            String permis = textFields[4].getText();
	            String carteBank = textFields[5].getText();
	            String username = textFields[6].getText();
	            String motPasse = textFields[7].getText();
	            controlleur.modifierClient(ancienUsername, nom, prenom, adresse, telephone, permis, carteBank, username, motPasse);
	        }
        } else {
        	JOptionPane.showMessageDialog(null, "Aucune sélection!");
        }
	}

}
