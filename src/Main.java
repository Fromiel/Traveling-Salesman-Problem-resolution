import javax.swing.JFrame;

public class Main {
	static JFrame f;

	public static void main(String[] args) {

		System.out.println("Debut du programme :");

		// crée la fenêtre
		f = new JFrame("Interface graphique");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 600);
		f.setVisible(true);

		// villes, borne sup, borne inf, coeff d'evaporation en %
		int nbVilles = 10, nbIterations = 100000000, nbFourmis = 100, nbThreads = 10;

		int bestLength = -1; // Meilleur solution trouvée

		Problem p = new Problem(nbVilles, 1, 5000, 0.09f, 500, 500);

		// ajoute les villes à la fenêtre
		f.getContentPane().add(new Interface(p.cities, null));

		System.out.println("Problem créé");

		// nombre de fourmis
		for (int i = 0; i < nbThreads; i++) {
			new Thread(new AntSystem(p, nbFourmis, bestLength, nbIterations / nbThreads)).start();
		}

	}
}