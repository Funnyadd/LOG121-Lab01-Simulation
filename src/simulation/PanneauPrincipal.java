package simulation;

import model.Building;
import model.Chemin;
import model.MachineComponent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import static java.awt.Font.BOLD;
import static simulation.Simulation.productionChain;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	private static int matiereCounter = 0;

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
				if (b.getType().equals("usine-matiere")) {

					// Ajout d'une matiere
					if (matiereCounter >= b.getProductionInterval()) matiereCounter = 0;
					if (matiereCounter == 0) {
						b.getOutput().addMachineComponents(new MachineComponent("metal", b.getCoordinates()));
					}

					var machineComponentList = b.getOutput().getMachineComponents();
					var machineComponentsToRemove = new LinkedList<Integer>();

					for (int i = 0; i < machineComponentList.size(); i++) {
						MachineComponent m = machineComponentList.get(i);

						// Draw components
						g.drawImage(m.getImage(), m.getPosition().x - 15, m.getPosition().y - 15, this);

						int xMultiplier = 1;
						int yMultiplier = 1;

						Chemin chemin = productionChain.getCheminByIdFrom(b.getId());
						Building building = productionChain.getBuildingById(chemin.getTo());

						if (building.getCoordinates().x - b.getCoordinates().x < 0) {
							xMultiplier = -1;
						}
						else if (building.getCoordinates().x - b.getCoordinates().x == 0) {
							xMultiplier = 0;
						}

						if (building.getCoordinates().y - b.getCoordinates().y < 0) {
							yMultiplier = -1;
						}
						else if (building.getCoordinates().y - b.getCoordinates().y == 0) {
							yMultiplier = 0;
						}

						// Chnage position of component
						m.getPosition().translate(m.getSpeed() * xMultiplier, m.getSpeed() * yMultiplier);

						// Check if component arrived at destination
						if (m.getPosition().equals(productionChain.getBuildingById(chemin.getTo()).getCoordinates())) {
							machineComponentsToRemove.add(i);
						}
					}

					// Remove machineComponents that arrived at the next usine
					for (int i : machineComponentsToRemove) {
						machineComponentList.remove(i);
					}
					machineComponentsToRemove = new LinkedList<Integer>();

				}
			}
			matiereCounter++;

			for (Building b : productionChain.getBuildingList()) {
				int imageInt = 0;
				g.drawImage(b.getIcon().get(imageInt).getImage(), b.getCoordinates().x - 15, b.getCoordinates().y - 15, this);
			}
		}
	}

	private int getYPostion(int offset) {
		return FenetrePrincipale.DIMENSION.height / 2 - 40 - offset;
	}

	private int getXPosition(String text) {
		return (FenetrePrincipale.DIMENSION.width / 2) - (int) (text.length() * 4.35);
	}
}