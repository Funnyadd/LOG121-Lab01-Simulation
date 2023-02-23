package simulation;

import model.*;
import model.Strategy.DefaultStrategy;
import model.Strategy.FixedStrategy;
import model.Strategy.RandomStrategy;
import util.JSwitchBox;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.*;

import static simulation.Simulation.productionChain;

public class PanneauOptions extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanneauOptions() {
		ButtonGroup boutonsStrategie = strategieSection();
		JSlider sliderVitesse = vitesseSection();
		JSwitchBox toggleOptimisation = optimisationSection();

		JButton boutonConfirmer = new JButton("Confirmer");
		JButton boutonAnnuler = new JButton("Annuler");

		System.out.println("\nConfigurations:");
		boutonConfirmer.addActionListener((ActionEvent e) -> {

			// Logique de confirmation pour la strategie
			System.out.println("Stratégie: " + getSelectedButtonText(boutonsStrategie));
			Entrepot entrepot = productionChain.getEntrepot();

			switch (getSelectedButtonText(boutonsStrategie)) {
				case "Par défault": entrepot.setStrategy(new DefaultStrategy()); break;
				case "Fixe": entrepot.setStrategy(new FixedStrategy()); break;
				case "Aléatoire": entrepot.setStrategy(new RandomStrategy()); break;
			}

			// Logique de confirmation pour la vitesse
			System.out.println("Vitesse: x" + sliderVitesse.getValue());
			productionChain.setSpeedMultiplier(sliderVitesse.getValue());

			// Logique de confirmation pour l'optimisation
			System.out.println("Optimisation: " + (toggleOptimisation.isSelected() ? "Activé" : "Désactivé"));
			productionChain.setOptimisation(toggleOptimisation.isSelected());

			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		boutonAnnuler.addActionListener((ActionEvent e) -> {
			// Fermer la fenêtre du composant
			SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
		});

		add(boutonConfirmer);
		add(boutonAnnuler);
	}

	private ButtonGroup strategieSection() {
		JLabel title = new JLabel("      Stratégies de vente      ");

		ButtonGroup groupeBoutons = new ButtonGroup();
		JRadioButton strategie1 = new JRadioButton("Par défault");
		JRadioButton strategie2 = new JRadioButton("Fixe");
		JRadioButton strategie3 = new JRadioButton("Aléatoire");

		add(title);

		groupeBoutons.add(strategie1);
		groupeBoutons.add(strategie2);
		groupeBoutons.add(strategie3);
		add(strategie1);
		add(new JLabel("        (Ne vend jamais)"));
		add(strategie2);
		add(new JLabel(" (Vend quand presque plein)"));
		add(strategie3);
		add(new JLabel("   (Vend aléatoirement)"));
		add(new JLabel("                                             "));

		preSelectPreviousStrategie(groupeBoutons);
		return groupeBoutons;
	}

	private JSlider vitesseSection() {
		JLabel title = new JLabel("Vitesse de la simulation");

		final int MIN_SPEED = 1;
		final int MAX_SPEED = 9;

		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, (int) productionChain.getSpeedMultiplier());
		speedSlider.setMajorTickSpacing(2);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);

		Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
		labelTable.put(1, new JLabel("x1"));
		labelTable.put(5, new JLabel("x5"));
		labelTable.put(9, new JLabel("x9"));
		speedSlider.setLabelTable(labelTable);

		add(title);
		add(speedSlider);
		add(new JLabel("                                             "));

		return speedSlider;
	}

	public JSwitchBox optimisationSection() {
		JLabel title = new JLabel("       Optimisation de la simulation       ");

		JSwitchBox toggle = new JSwitchBox("Activé", "Désactivé");
		toggle.setSelected(productionChain.isOptimisation());

		add(title);
		add(toggle);
		add(new JLabel("                                             "));

		return toggle;
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

			Entrepot entrepot = productionChain.getEntrepot();

			if (bouton.getText().equals(entrepot.getStrategy().getIdentifier())) {
				bouton.setSelected(true);
			}
		}
	}

}
