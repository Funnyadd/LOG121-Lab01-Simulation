package simulation;

import model.Building;
import model.Chemin;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		for (Chemin c : Simulation.productionChain.getCheminList()) {
			int x1 = 0, x2 = 0, y1 = 0, y2 = 0;

			for (Building b : Simulation.productionChain.getBuildings()) {
				if(b.getId() == c.getFrom()) {
					x1 = b.getCoordinates().x;
					y1 = b.getCoordinates().y;
				} else if (b.getId() == c.getTo()) {
					x2 = b.getCoordinates().x;
					y2 = b.getCoordinates().y;
				}
			}
			g.drawLine(x1, y1, x2, y2);
		}

		for (Building b : Simulation.productionChain.getBuildings()) {
			BufferedImage image;

			try {
				image = ImageIO.read(new File(b.getIcon().get(0).getPath()));
			} catch (IOException e) {
				System.out.println("Error while reading one of the images");
				throw new RuntimeException(e);
			}
			g.drawImage(image, b.getCoordinates().x - 15, b.getCoordinates().y - 15, this);
		}

// On ajoute à la position le delta x et y de la vitesse
//		position.translate(vitesse.x, vitesse.y);
//		g.fillRect(position.x, position.y, taille, taille);
	}

}