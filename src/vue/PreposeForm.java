package vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlleur.AgenceControlleur;
import modele.Client;
import modele.Location;

public class PreposeForm extends JPanel implements ActionListener{
	
	private AgenceControlleur controlleur;
	
	private JButton enregLocationButton, enregReservButton, enregRetourButton, sauvegarderButton,
	dossiersButton, catalogueButton, fichesButton;
	
	public PreposeForm (AgenceControlleur controlleur) {
		
		this.controlleur = controlleur;
		
		enregLocationButton = new JButton("Enregistrer location");
		enregReservButton = new JButton("Enregistrer réservation");
		enregRetourButton = new JButton("Enregistrer retour");
		dossiersButton = new JButton("Accéder aux dossiers clients");
		catalogueButton = new JButton("Consulter catalogue");
		fichesButton = new JButton("Fiches récapulatives");
		sauvegarderButton = new JButton("Sauvegarder");
		
		JPanel panel = new JPanel(new BorderLayout(30, 30)); 
		JPanel gridPanel = new JPanel(new GridLayout(0, 3, 30, 100)); 
		gridPanel.add(enregLocationButton);
		gridPanel.add(enregReservButton);
		gridPanel.add(enregRetourButton);
		gridPanel.add(dossiersButton);
		gridPanel.add(catalogueButton);
		gridPanel.add(fichesButton);
		gridPanel.add(new JPanel());
		gridPanel.add(sauvegarderButton);
		
		panel.add(new JPanel(), BorderLayout.NORTH); 
		panel.add(new JPanel(), BorderLayout.SOUTH); 
		panel.add(new JPanel(), BorderLayout.WEST); 
		panel.add(new JPanel(), BorderLayout.EAST); 
		panel.add(gridPanel, BorderLayout.CENTER); 

		this.add(panel);
		
		enregLocationButton.addActionListener(this);
		enregReservButton.addActionListener(this);
		enregRetourButton.addActionListener(this);
		dossiersButton.addActionListener(this);
		catalogueButton.addActionListener(this);
		sauvegarderButton.addActionListener(this);
		fichesButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		if ( button == enregLocationButton) {
			try {
				enregistrerLocation();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		} else if (button == enregReservButton) {
			try {
				enregistrerReservation();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		} else if (button == enregRetourButton) {
			try {
				enregistrerRetourLoc();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		} else if (button == dossiersButton) {
			controlleur.afficherDossiersClients();
			
		} else if (button == catalogueButton) {
			controlleur.afficherCatalogue();
		} else if (button == sauvegarderButton) {
			controlleur.ecrireDonnees();
		} else {
			controlleur.afficherFichesRecaps();
		}
	}
	
	public void enregistrerLocation() throws ParseException {
		String[] fieldNames = {"Matricule", "Username", "Date debut", "Date retour"};
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
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Enregistrer une location",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String matricule = textFields[0].getText();
            String username = textFields[1].getText();
            String pattern = "dd-MM-yyyy"; 
            SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
            Date dateDebut = sdf.parse(textFields[2].getText().trim()); 
            Date dateFin = sdf.parse(textFields[3].getText().trim());
            if (dateFin.before(dateDebut))
            	JOptionPane.showMessageDialog(null, "Intervalle invalide!");
            if (!controlleur.enregistrerLocation(dateDebut, dateFin, matricule, username))
            	JOptionPane.showMessageDialog(null, "Vehicule occupée dans cette intervalle du temps!");
            else
            	JOptionPane.showMessageDialog(null, "Location enregistrée avec succès!");	
        }
	}
	
	public void enregistrerReservation() throws ParseException {
		String[] fieldNames = {"Matricule", "Username", "Date debut", "Date retour"};
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
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Enregistrer une reservation",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String matricule = textFields[0].getText();
            String username = textFields[1].getText();
            String pattern = "dd-MM-yyyy"; 
            SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
            Date dateDebut = sdf.parse(textFields[2].getText().trim()); 
            Date dateFin = sdf.parse(textFields[3].getText().trim());
            if (dateFin.before(dateDebut))
            	JOptionPane.showMessageDialog(null, "Intervalle invalide!");
            if (!controlleur.enregistrerReservation(dateDebut, dateFin, matricule, username))
            	JOptionPane.showMessageDialog(null, "Vehicule occupée dans cette intervalle du temps!");
            else
            	JOptionPane.showMessageDialog(null, "Réservation enregistrée avec succès!");
        }
	}
	
	public void enregistrerRetourLoc() throws ParseException {
		String[] fieldNames = {"Matricule", "Username", "Date debut"};
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
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Enregistrer une location",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String matricule = textFields[0].getText();
            String username = textFields[1].getText();
            String pattern = "dd-MM-yyyy"; 
            SimpleDateFormat sdf = new SimpleDateFormat(pattern); 
            Date dateDebut = sdf.parse(textFields[2].getText().trim());
            int rep = controlleur.enregistrerRetourLoc(dateDebut, matricule, username);
            if (rep == 0)
            	JOptionPane.showMessageDialog(null, "Les données fournies ne correspondent à aucune location enregistrée!");
            else if (rep == -1)
            	JOptionPane.showMessageDialog(null, "Retour déjà enregistré!");
            else
            	JOptionPane.showMessageDialog(null, "Retour enregistré avec succès!");
        }
	}

}
