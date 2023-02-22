package simulation;

import model.*;
import model.Strategy.DefaultStrategy;
import model.Strategy.FixedStrategy;
import model.Strategy.RandomStrategy;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import static simulation.Simulation.productionChain;

public class PanneauStrategie extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanneauStrategie() {

		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategie1 = new JRadioButton("Stratégie par défault");
		JRadioButton strategie2 = new JRadioButton("Stratégie aléatoire");
		JRadioButton strategie3 = new JRadioButton("Stratégie fixe");

		JButton boutonConfirmer = new JButton("Confirmer");

		boutonConfirmer.addActionListener((ActionEvent e) -> {
			System.out.println(getSelectedButtonText(groupeBoutons));

			for (Building b : productionChain.getBuildingList()) {
				if (b instanceof Entrepot) {
					switch (getSelectedButtonText(groupeBoutons)) {
						case "Stratégie par défault": ((Entrepot) b).setStrategy(new DefaultStrategy()); break;
						case "Stratégie fixe": ((Entrepot) b).setStrategy(new FixedStrategy()); break;
						case "Stratégie aléatoire": ((Entrepot) b).setStrategy(new RandomStrategy()); break;
					}
				}
			}

			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		JButton boutonAnnuler = new JButton("Annuler");

		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		groupeBoutons.add(strategie1);
		groupeBoutons.add(strategie2);
		groupeBoutons.add(strategie3);
		add(strategie1);
		add(strategie2);
		add(strategie3);
		add(boutonConfirmer);
		add(boutonAnnuler);

		preSelectPreviousStrategie(groupeBoutons);
	}

	/**
	 * Retourne le bouton sélectionné dans un groupe de boutons.
	 * @param groupeBoutons
	 * @return
	 */
	public String getSelectedButtonText(ButtonGroup groupeBoutons) {
		for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
			AbstractButton bouton = boutons.nextElement();
			if (bouton.isSelected()) {
				return bouton.getText();
			}
		}

		return null;
	}

	private void preSelectPreviousStrategie(ButtonGroup groupeBoutons) {
		for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
			AbstractButton bouton = boutons.nextElement();

			Entrepot entrepot = new Entrepot();

			for (Building b : productionChain.getBuildingList()) {
				if (b instanceof Entrepot) {
					entrepot = (Entrepot) b;
				}
			}

			if (bouton.getText().equals("Stratégie " + entrepot.getStrategy().getIdentifier())) {
				bouton.setSelected(true);
			}
		}
	}

}
