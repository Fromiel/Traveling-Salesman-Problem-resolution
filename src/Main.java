import javax.swing.JFrame;

public class Main {
	static JFrame f;

	public static void main(String[] args) {

		// crée la fenêtre
		/*
		 * f = new JFrame("Interface graphique");
		 * f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); f.setSize(600, 600);
		 * f.setVisible(true);
		 */

		// villes, borne sup, borne inf, coeff d'evaporation en %
		int nbVilles = 10, nbIterations = 10000000, nbFourmis = 100, nbThreads = 20;

		//Classe permettant de mesurer et d'afficher le temps d'execution du programme lors de la fin de l'execution de tous les threads
		MeasureTime m = new MeasureTime(nbThreads);

		int bestLength = -1; // Meilleur solution trouvée

		Problem p = new Problem(nbVilles, 1, 5000, 0.09f, 500, 500);

		// ajoute les villes à la fenêtre
		// f.getContentPane().add(new Interface(p.cities, null));

		// Cree les threads
		for (int i = 0; i < nbThreads; i++) {
			new Thread(new AntSystem(p, nbFourmis, bestLength, nbIterations / nbThreads, m)).start();
		}

	}
}