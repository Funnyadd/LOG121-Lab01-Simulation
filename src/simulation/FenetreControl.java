package simulation;

import javax.swing.*;
import java.awt.*;

public class FenetreControl extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String TITRE_FENETRE = "Contrôlez la simulation";
    private static final Dimension DIMENSION = new Dimension(270, 205);

    public FenetreControl() {
        add(new PanneauControl());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle(TITRE_FENETRE);
        setSize(DIMENSION);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
