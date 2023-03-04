package simulation;

import javax.swing.SwingWorker;

import static simulation.Simulation.productionChain;

public class Environnement extends SwingWorker<Object, String> {
	private static final int DELAY = 10;	// delai par défault: 100

	@Override
	protected Object doInBackground() throws Exception {
		while(productionChain.isOn()) {
			Thread.sleep(DELAY);
			// C'est ici que vous aurez à faire la gestion de la notion de tour.
			firePropertyChange("REPAINT", null, "Refreshing");
		}
		return null;
	}

}