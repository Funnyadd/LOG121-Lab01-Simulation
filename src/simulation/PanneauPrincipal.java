package simulation;

import model.Building;
import model.Chemin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import static java.awt.Font.BOLD;
import static simulation.Simulation.productionChain;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (productionChain.getBuildingList().isEmpty() && productionChain.getCheminList().isEmpty()) {
			final String titre = "Bienvenue dans ce simulateur d'usine d'avion!";
			final String ligne1 = "Pour débuter la simulation, veuillez entrer un ficher de configuration";
			final String ligne2 = "en cliquant sur le button 'charger' en haut à gauche de la fenêtre.";

			g.setFont(new Font("font", BOLD, 18));
			g.drawString(titre, getXPosition(titre), getYPostion(60));
			g.drawString(ligne1, getXPosition(ligne1), getYPostion(20));
			g.drawString(ligne2, getXPosition(ligne2), getYPostion(0));
		}
		else {
			for (Chemin c : productionChain.getCheminList()) {
				int x1 = 0, x2 = 0, y1 = 0, y2 = 0;

				for (Building b : productionChain.getBuildingList()) {
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

			for (Building b : productionChain.getBuildingList()) {
				BufferedImage image;

				try {
					image = ImageIO.read(new File(b.getIcon().get(0).getPath()));
				} catch (IOException e) {
					System.out.println("Error while reading one of the images");
					throw new RuntimeException(e);
				}
				g.drawImage(image, b.getCoordinates().x - 15, b.getCoordinates().y - 15, this);
			}
		}

// On ajoute à la position le delta x et y de la vitesse
//		position.translate(vitesse.x, vitesse.y);
//		g.fillRect(position.x, position.y, taille, taille);
	}

	private int getYPostion(int offset) {
		return FenetrePrincipale.DIMENSION.height / 2 - 40 - offset;
	}

	private int getXPosition(String text) {
		return (FenetrePrincipale.DIMENSION.width / 2) - (int) (text.length() * 4.35);
	}
}