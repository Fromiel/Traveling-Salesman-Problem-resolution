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
		int nbVilles = 10, nbIterations = 1000000, nbFourmis = 100;

		int bestLength = -1; // Meilleur solution trouvée

		Problem p = new Problem(nbVilles, 1, 5000, 0.1f, 500, 500);

		// ajoute les villes à la fenêtre
		f.getContentPane().add(new Interface(p.cities, null));

		System.out.println("Problem créé");

		// nombre de fourmis
		for (int i = 0; i < 10; i++) {
			new Thread(new AntSystem(p, nbFourmis, bestLength, nbIterations)).start();
		}
		// AntSystem sys = new AntSystem(p, nbFourmis);

		System.out.println("Système créé");

		// unités de temps
		// sys.run(nbIterations);

		System.out.println("Chemins testés");

		System.out.println("Fin du programme");
	}
}