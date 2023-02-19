package simulation;

import javax.swing.*;
import java.awt.*;

public class FenetreVitesse extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String TITRE_FENETRE = "Sélectionnez la vitesse de la simulation";
    private static final Dimension DIMENSION = new Dimension(250, 100);
    private PanneauVitesse panneauVitesse = new PanneauVitesse();

    public FenetreVitesse() {
        add(panneauVitesse);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}