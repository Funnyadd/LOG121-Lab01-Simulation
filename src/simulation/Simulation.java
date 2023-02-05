package simulation;

import model.ProductionChain;

public class Simulation {

	public static ProductionChain productionChain = new ProductionChain();
	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();

		// For testing purposes
//		productionChain = parseXml("src\\ressources\\configuration.xml");
	}

}
