package simulation;

import javax.swing.SwingWorker;

import static simulation.Simulation.productionChain;

public class Environnement extends SwingWorker<Object, String> {
	private boolean active = true;
	private static final int DELAY = 10;

	@Override
	protected Object doInBackground() throws Exception {
		while(active) {
			Thread.sleep(DELAY);
			// C'est ici que vous aurez à faire la gestion de la notion de tour.
			if (productionChain.isOn()) {
				firePropertyChange("REPAINT", null, "Refreshing");
			}
		}
		return null;
	}

}