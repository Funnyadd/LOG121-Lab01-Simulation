package simulation;

import model.ProductionChain;

import static util.XmlParser.parseXml;

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

		// Temporary
		productionChain = parseXml("src\\ressources\\configuration.xml");
	}

}
