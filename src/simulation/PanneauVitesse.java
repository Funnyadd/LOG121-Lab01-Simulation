package simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

import static simulation.Simulation.productionChain;

public class PanneauVitesse extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanneauVitesse() {
        final int MIN_SPEED = 0;
        final int MAX_SPEED = 10;
        final int INIT_SPEED = 1;

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, INIT_SPEED);
        speedSlider.setMajorTickSpacing(MAX_SPEED / 2);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        JButton boutonConfirmer = new JButton("Confirmer");

        boutonConfirmer.addActionListener((ActionEvent e) -> {
            // Prendre la valeur du slider
            if (speedSlider.getValue() > 0) {
                productionChain.setSpeedMultiplier(speedSlider.getValue());
            } else {
                productionChain.setSpeedMultiplier(0.1);
            }

            // Fermer la fenêtre du composant
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        JButton boutonAnnuler = new JButton("Annuler");

        boutonAnnuler.addActionListener((ActionEvent e) -> {
            // Fermer la fenêtre du composant
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        add(speedSlider);
        add(boutonConfirmer);
        add(boutonAnnuler);
    }
}
