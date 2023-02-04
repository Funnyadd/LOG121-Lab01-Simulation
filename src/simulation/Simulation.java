package simulation;

import static util.XmlParser.parseXml;

public class Simulation {

	/**
	 * Cette classe représente l'application dans son ensemble.
	 */
	public static void main(String[] args) {
		Environnement environnement = new Environnement();
		FenetrePrincipale fenetre = new FenetrePrincipale();

		environnement.addPropertyChangeListener(fenetre);
		environnement.execute();

		// Temporary
		parseXml("C:\\Users\\mihaj\\Desktop\\configuration.xml");
	}

}
