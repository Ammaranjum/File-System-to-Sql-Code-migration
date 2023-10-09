package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controlleur.AgenceControlleur;

public class FichesRecapForm extends JPanel implements ActionListener {
	
	private AgenceControlleur controlleur;

	private JTextArea rapportTextArea;
	private JButton listeClientsButton, listeVehiculesButton, listeVehiculesDispButton,
			listeVehiculesNonDispoButton, listeVehiculesJamaisButton, listeLocationsButton,
			listeLocationsEnCoursButton, retourButton;
	
public FichesRecapForm (AgenceControlleur controlleur) {
		
		this.controlleur = controlleur;
		
		listeClientsButton = new JButton("Liste des clients");
		listeVehiculesButton = new JButton("Liste des véhicules");
		listeVehiculesDispButton = new JButton("Liste des véhicules disponibles");
		listeVehiculesNonDispoButton = new JButton("Liste des véhicules non disponibles");
		listeVehiculesJamaisButton = new JButton("Liste des véhicules jamais louées");
		listeLocationsButton  = new JButton("Liste des locations");
		listeLocationsEnCoursButton = new JButton("Liste des locations en cours");
		retourButton = new JButton("Retour");
		
		JPanel gridPanel = new JPanel(new GridLayout(3, 3)); 
		gridPanel.add(listeClientsButton);
		gridPanel.add(listeVehiculesButton);
		gridPanel.add(listeVehiculesDispButton);
		gridPanel.add(listeVehiculesNonDispoButton);
		gridPanel.add(listeVehiculesJamaisButton);
		gridPanel.add(listeLocationsEnCoursButton);
		gridPanel.add(listeLocationsButton);
		gridPanel.add(new JPanel());
		gridPanel.add(retourButton);
		
		
		JPanel textPanel = new JPanel(new BorderLayout()); 
		rapportTextArea = new JTextArea();
		rapportTextArea.setRows(30);
		JScrollPane scrollPane = new JScrollPane(rapportTextArea);

		textPanel.add(scrollPane, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		
		add(gridPanel, BorderLayout.NORTH); 
		add(textPanel, BorderLayout.CENTER); 

		listeClientsButton.addActionListener(this);
		listeVehiculesButton.addActionListener(this);
		listeVehiculesDispButton.addActionListener(this);
		listeVehiculesNonDispoButton.addActionListener(this);
		listeVehiculesJamaisButton.addActionListener(this);
		listeLocationsEnCoursButton.addActionListener(this);
		listeLocationsButton.addActionListener(this);
		retourButton.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton button = (JButton)e.getSource();
		if ( button == listeClientsButton) {
			rapportTextArea.setText(controlleur.genererListeClients());
		} else if (button == listeVehiculesButton) {
			rapportTextArea.setText(controlleur.genererListeVehicules());
		} else if (button == listeVehiculesDispButton) {
			rapportTextArea.setText(controlleur.genererListeVehiculesDisponibles());
		} else if (button == listeVehiculesNonDispoButton) {
			rapportTextArea.setText(controlleur.genererListeVehiculesNonDisponibles());		
		} else if (button == listeVehiculesJamaisButton) {
			rapportTextArea.setText(controlleur.genererListeVehiculesJamaisLouees());
		} else if (button == listeLocationsButton) {
			rapportTextArea.setText(controlleur.genererListeLocations());
		} else if (button == listeLocationsEnCoursButton) {
			rapportTextArea.setText(controlleur.genererListeLocationsEnCours());
		} else {
			controlleur.afficherPreposeOutils();
		}
	}
}
