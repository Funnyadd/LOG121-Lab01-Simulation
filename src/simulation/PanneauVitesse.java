package simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Hashtable;

import static simulation.Simulation.productionChain;

public class PanneauVitesse extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanneauVitesse() {
        final int MIN_SPEED = 1;
        final int MAX_SPEED = 9;
        final int INIT_SPEED = 1;

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, INIT_SPEED);
        speedSlider.setMajorTickSpacing(2);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("x1") );
        labelTable.put(5, new JLabel("x5") );
        labelTable.put(9, new JLabel("x9") );
        speedSlider.setLabelTable(labelTable);

        JButton boutonConfirmer = new JButton("Confirmer");

        boutonConfirmer.addActionListener((ActionEvent e) -> {
            // Prendre la valeur du slider
            productionChain.setSpeedMultiplier(speedSlider.getValue());

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
