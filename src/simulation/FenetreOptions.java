package simulation;

import java.awt.Dimension;

import javax.swing.JFrame;

public class FenetreOptions extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String TITRE_FENETRE = "Modifiez les options de la simulation";
	private static final Dimension DIMENSION = new Dimension(250, 365);

	public FenetreOptions() {
		add(new PanneauOptions());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(TITRE_FENETRE);
		setSize(DIMENSION);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
}
