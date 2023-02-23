package simulation;

import model.ProductionChain;

public class Simulation {

	public static ProductionChain productionChain = new ProductionChain();
	public static FenetrePrincipale fenetre = new FenetrePrincipale();

	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static void main(String[] args) {
		Environnement environnement = new Environnement();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();
	}

}
