package simulation;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import static util.XmlParser.parseXml;

public class MenuFenetre extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private static final String MENU_FICHIER_TITRE = "Fichier";
	private static final String MENU_FICHIER_CHARGER = "Charger";
	private static final String MENU_FICHIER_QUITTER = "Quitter";
	private static final String MENU_SIMULATION_TITRE = "Simulation";
	private static final String MENU_SIMULATION_OPTIONS = "Options";
	private static final String MENU_SIMULATION_VITESSE = "Contrôles";
	private static final String MENU_AIDE_TITRE = "Aide";
	private static final String MENU_AIDE_PROPOS = "À propos de...";

	public final JMenu menuSimulation = new JMenu(MENU_SIMULATION_TITRE);

	public MenuFenetre() {
		ajouterMenuFichier();
		ajouterMenuSimulation();
		ajouterMenuAide();
	}

	/**
	 * Créer le menu de Fichier
	 */
	private void ajouterMenuFichier() {
		JMenu menuFichier = new JMenu(MENU_FICHIER_TITRE);
		JMenuItem menuCharger = new JMenuItem(MENU_FICHIER_CHARGER);
		JMenuItem menuQuitter = new JMenuItem(MENU_FICHIER_QUITTER);

		menuCharger.addActionListener((ActionEvent e) -> {
			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			fileChooser.setDialogTitle("Sélectionnez un fichier de configuration");
			fileChooser.setAcceptAllFileFilterUsed(false);
			// Créer un filtre
			FileNameExtensionFilter filtre = new FileNameExtensionFilter(".xml", "xml");
			fileChooser.addChoosableFileFilter(filtre);

			int returnValue = fileChooser.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				Simulation.productionChain = parseXml(selectedFile.getAbsolutePath());

				// Activer le button simulation quand un fichier de config est sélectionné
				menuSimulation.setEnabled(true);
			}
		});
		
		menuQuitter.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		menuFichier.add(menuCharger);
		menuFichier.add(menuQuitter);

		add(menuFichier);
	}

	/**
	 * Créer le menu de Simulation
	 */
	private void ajouterMenuSimulation() {
		JMenuItem menuOptions = new JMenuItem(MENU_SIMULATION_OPTIONS);
		JMenuItem menuVitesse = new JMenuItem(MENU_SIMULATION_VITESSE);
		menuSimulation.add(menuOptions);
		menuSimulation.add(menuVitesse);

		menuOptions.addActionListener((ActionEvent e) -> {
			// Ouvrir la fenétre de sélection
			// TODO - Récupérer la bonne stratégie de vente
			new FenetreOptions();
		});

		menuVitesse.addActionListener((ActionEvent e) -> {
			// Ouvrir la fenétre de sélection
			new FenetreControl();
		});
		menuSimulation.setEnabled(false);
		add(menuSimulation);

	}

	/**
	 * Créer le menu Aide
	 */
	private void ajouterMenuAide() {
		JMenu menuAide = new JMenu(MENU_AIDE_TITRE);
		JMenuItem menuPropos = new JMenuItem(MENU_AIDE_PROPOS);
		menuAide.add(menuPropos);

		menuPropos.addActionListener((ActionEvent e) -> {
			JOptionPane.showMessageDialog(null,
					"<html><p>Application simulant une chaine de production d'avions.</p>" + "<br>"
							+ "<p>Auteur &nbsp;: &nbsp; Adam Mihajlovic</p>"
							+ "<br>"
							+ "<p>Contributeurs &nbsp;:</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Ghizlane El Boussaidi</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Dany Boisvert</p>"
							+ "<p>&copy; &nbsp; 2017 &nbsp; Vincent Mattard</p>"
							+ "<br>"
							+ "<p>&Eacute;cole de technologie sup&eacute;rieure</p></html>"
			);
		});
		add(menuAide);
	}

}
