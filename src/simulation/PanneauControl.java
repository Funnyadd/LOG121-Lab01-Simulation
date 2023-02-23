package simulation;

import model.Building;
import model.Entrepot;
import model.ProductionChain;
import model.Strategy.DefaultStrategy;
import model.Strategy.Strategy;
import util.XmlParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static simulation.Simulation.fenetre;
import static simulation.Simulation.productionChain;

public class PanneauControl extends JPanel {

    private static final long serialVersionUID = 1L;
    private final String PAUSE_TEXT = "Pause";
    private final String PLAY_TEXT = "Jouer";

    public PanneauControl() {
        JButton playPauseButton = new JButton(productionChain.isOn() ?  PAUSE_TEXT : PLAY_TEXT);
        JButton restartButton = new JButton("Redémarrer");
        JButton resetButton = new JButton("Arrêter");
        JButton cancelButton = new JButton("Canceller");
        JButton resetOptionsButton = new JButton("Réinitialiser les options");

        playPauseButton.setPreferredSize(new Dimension(245, 30));
        restartButton.setPreferredSize(new Dimension(120, 30));
        resetButton.setPreferredSize(new Dimension(120, 30));
        cancelButton.setPreferredSize(new Dimension(120, 30));
        resetOptionsButton.setPreferredSize(new Dimension(245, 30));

        playPauseButton.addActionListener((ActionEvent e) -> {
            boolean checkIfOn = !playPauseButton.getText().equals(PAUSE_TEXT);
            playPauseButton.setText(checkIfOn ? PAUSE_TEXT : PLAY_TEXT);
            productionChain.setIsOn(checkIfOn);
        });

        restartButton.addActionListener((ActionEvent e) -> {
            Strategy optionStrategie = productionChain.getEntrepot().getStrategy();
            double speedOption = productionChain.getSpeedMultiplier();
            boolean optimisationOption = productionChain.isOptimisation();

            productionChain = XmlParser.parseXml(productionChain.getFilePath());

            productionChain.getEntrepot().setStrategy(optionStrategie);
            productionChain.setSpeedMultiplier(speedOption);
            productionChain.setOptimisation(optimisationOption);
        });

        resetButton.addActionListener((ActionEvent e) -> {
            productionChain = new ProductionChain();
            fenetre.menuFenetre.menuSimulation.setEnabled(false);
            // Fermer la fenêtre du composant
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        resetOptionsButton.addActionListener((ActionEvent e) -> {
            productionChain.getEntrepot().setStrategy(new DefaultStrategy());
            productionChain.setSpeedMultiplier(1);
            productionChain.setOptimisation(true);
        });

        cancelButton.addActionListener((ActionEvent e) -> {
            // Fermer la fenêtre du composant
            SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
        });

        add(playPauseButton);
        add(restartButton);
        add(resetButton);
        add(resetOptionsButton);
        add(new JLabel("                                             "));
        add(cancelButton);
    }
}
