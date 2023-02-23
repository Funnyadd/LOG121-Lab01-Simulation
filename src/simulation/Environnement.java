package simulation;

import javax.swing.SwingWorker;

import static simulation.Simulation.productionChain;

public class Environnement extends SwingWorker<Object, String> {
	private boolean actif = true;
	private static final int DELAI = 10;	// default delay: 100

	@Override
	protected Object doInBackground() throws Exception {
		while(actif) {
			Thread.sleep(DELAI);
			// C'est ici que vous aurez à faire la gestion de la notion de tour.
			if (productionChain.isOn())  {
				firePropertyChange("REPAINT", null, "Refreshing");
			}
		}
		return null;
	}

}