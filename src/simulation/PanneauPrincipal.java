package simulation;

import model.*;

import java.awt.*;
import java.util.LinkedList;

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
				switch(b.getType()) {
					case "usine-matiere":
						// Increment Interval counter whenthe component is drawn on the screen
						b.incrementIntervalCounter();

						// Ajout d'une matiere
						if (b.getIntervalCounter() >= b.getProductionInterval()) {
							b.resetIntervalCounter();
							b.getOutput().addMachineComponents(new MachineComponent("metal", b.getCoordinates()));
						}

						drawMachineComponents(b, g);
						break;
					case "entrepot":
						break;
					default:
						createMachineComponents(b);
						drawMachineComponents(b, g);
						break;
				}
			}

			for (Building b : productionChain.getBuildingList()) {
				g.drawImage(b.getIcon().get(getImageBasedOnCompletion(b)).getImage(), b.getCoordinates().x - 15, b.getCoordinates().y - 15, this);
			}
		}
	}

	private int getYPostion(int offset) {
		return FenetrePrincipale.DIMENSION.height / 2 - 40 - offset;
	}

	private int getXPosition(String text) {
		return (FenetrePrincipale.DIMENSION.width / 2) - (int) (text.length() * 4.35);
	}
	
	private int getVectorMultiplier(int to, int from) {
		if (to - from < 0) return -1;
		else if (to - from == 0) return 0;
		return 1;
	}

	private void createMachineComponents(Building b) {
		var usine = (Usine) b;
		// Increment Interval counter when the component is drawn on the screen
		if (usine.getIntervalCounter() > 0) {
			usine.incrementIntervalCounter();
		}

		if(usine.getInputList().stream().allMatch(i -> i.getQuantity() >= i.getMaxQuantity())) {
			for (UsineInput input : usine.getInputList()) {
				input.resetQuantity();
				usine.incrementIntervalCounter();
			}
		}

		// Add component
		if (usine.getIntervalCounter() >= usine.getProductionInterval()) {
			usine.resetIntervalCounter();
			usine.getOutput().addMachineComponents(new MachineComponent(b.getOutput().getType(), usine.getCoordinates()));
		}
	}

	private void drawMachineComponents(Building b, Graphics g) {
		var machineComponentList = b.getOutput().getMachineComponents();
		var machineComponentsToRemove = new LinkedList<Integer>();

		for (int i = 0; i < machineComponentList.size(); i++) {
			MachineComponent m = machineComponentList.get(i);

			Chemin chemin = productionChain.getCheminByIdFrom(b.getId());
			Building building = productionChain.getBuildingById(chemin.getTo());

			if (m.getDestinationId() == -1) {
				m.setDestinationId(building.getId());
			}

			int xMultiplier = getVectorMultiplier(building.getCoordinates().x, b.getCoordinates().x);
			int yMultiplier = getVectorMultiplier(building.getCoordinates().y, b.getCoordinates().y);

			// Draw components
			g.drawImage(m.getImage(), m.getPosition().x - 15, m.getPosition().y - 15, this);

			// Change position of component for next redraw
			m.getPosition().translate(m.getSpeed() * xMultiplier, m.getSpeed() * yMultiplier);

			// Check if component arrived at destination
			if (xMultiplier < 0 && m.getPosition().x <= building.getCoordinates().x) {
				if (yMultiplier < 0) {
					if (m.getPosition().y <= building.getCoordinates().y) {
						machineComponentsToRemove.add(i);
					}
				}
				else {
					if (m.getPosition().y >= building.getCoordinates().y) {
						machineComponentsToRemove.add(i);
					}
				}
			}
			else if (m.getPosition().x >= building.getCoordinates().x) {
				if (yMultiplier < 0) {
					if (m.getPosition().y <= building.getCoordinates().y) {
						machineComponentsToRemove.add(i);
					}
				}
				else {
					if (m.getPosition().y >= building.getCoordinates().y) {
						machineComponentsToRemove.add(i);
					}
				}
			}
		}

		// Remove machineComponents that arrived at the next usine
		for (int i : machineComponentsToRemove) {
			Building nextBuilding = productionChain.getBuildingById(machineComponentList.get(i).getDestinationId());
			if (nextBuilding instanceof Entrepot) {
				((Entrepot) nextBuilding).getInput().addCapacity();
			}
			else {
				for (UsineInput usineInput : ((Usine) nextBuilding).getInputList()) {
					if (usineInput.getComponent().getType().equals(machineComponentList.get(i).getType())) {
						usineInput.addQuantity();
					}
				}
			}
			machineComponentList.remove(i);
		}
	}

	private int getImageBasedOnCompletion(Building b) {
		if (b instanceof Entrepot) {
			EntrepotInput input = ((Entrepot) b).getInput();

			for (int i = 3; i > 0; i--) {
				if (input.getCapacity() >= (int) (input.getMaxCapacity() * i * 0.333)) {
					return i;
				}
			}
		}
		else {
			for (int i = 3; i > 0; i--) {
				if (b.getIntervalCounter() >= b.getProductionInterval() * i * 0.3) {
					return i;
				}
			}
		}
		return 0;
	}
}