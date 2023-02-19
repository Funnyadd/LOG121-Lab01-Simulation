package simulation;

import model.Building;
import model.Entrepot;
import model.FixedStrategy;
import model.RandomStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import static simulation.Simulation.productionChain;

public class PanneauVitesse extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanneauVitesse() {
        ButtonGroup groupeBoutons = new ButtonGroup();
        JRadioButton multiplier1 = new JRadioButton("x1");
        JRadioButton multiplier2 = new JRadioButton("x2");
        JRadioButton multiplier3 = new JRadioButton("x3");
        JRadioButton multiplier4 = new JRadioButton("x4");
        JRadioButton multiplier5 = new JRadioButton("x5");

        JButton boutonConfirmer = new JButton("Confirmer");

        boutonConfirmer.addActionListener((ActionEvent e) -> {
            System.out.println(getSelectedButtonText(groupeBoutons));

            switch (getSelectedButtonText(groupeBoutons)) {
                case "x1": productionChain.setSpeedMultiplier(1); break;
                case "x2": productionChain.setSpeedMultiplier(2); break;
                case "x3": productionChain.setSpeedMultiplier(3); break;
                case "x4": productionChain.setSpeedMultiplier(4); break;
                case "x5": productionChain.setSpeedMultiplier(5); break;
            }

            // Fermer la fenêtre du composant
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        JButton boutonAnnuler = new JButton("Annuler");

        boutonAnnuler.addActionListener((ActionEvent e) -> {
            // Fermer la fenêtre du composant
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        groupeBoutons.add(multiplier1);
        groupeBoutons.add(multiplier2);
        groupeBoutons.add(multiplier3);
        groupeBoutons.add(multiplier4);
        groupeBoutons.add(multiplier5);
        add(multiplier1);
        add(multiplier2);
        add(multiplier3);
        add(multiplier4);
        add(multiplier5);
        add(boutonConfirmer);
        add(boutonAnnuler);

        preSelectPreviousSpeed(groupeBoutons);
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

    private void preSelectPreviousSpeed(ButtonGroup groupeBoutons) {
        for (Enumeration<AbstractButton> boutons = groupeBoutons.getElements(); boutons.hasMoreElements();) {
            AbstractButton bouton = boutons.nextElement();
            if(bouton.getText().equals("x" + productionChain.getSpeedMultiplier())) {
                bouton.setSelected(true);
            }
        }
    }
}
