
//Classe représentant
public class Problem {

	public int nbCities; //Nombre de villes
	public int nbIterations; //Nombre d'iterations

	public City[] cities; //Villes qu'on veut parcourir de maniere optimale

	public int optimalLength; //Borne inferieure de la taille minimale qu'on peut trouver pour un chemin

	// arcs
	public int[][] distances; //Matrice representant les distances entre les villes
	public Pheromone[][] pheromones; //Matrice représentant les pheromones entre les villes (pheromones[i][j] = pheromones[j][i] represente les pheromones entre la ville cities[i] et cities[j])

	// Constructeur de problem pour creer des villes de coordonnees aleatoires
	// nbCities : nombre de villes du probleme
	// borneMin : minimum de pheromones entre deux villes
	// borneMax : maximum de pheromones entre deux villes
	// evaporation : taux d'evaporation des pheromones entre les villes
	// windowSizeX : coordonnee maximale des villes en x
	// windowSizeY : coordonnee maximale des villes en y
	public Problem(int nbCities, float borneMin, float borneMax, float evaporation, int windowSizeX, int windowSizeY) {
		this.nbCities = nbCities;
		this.distances = new int[nbCities][nbCities];
		this.pheromones = new Pheromone[nbCities][nbCities];
		this.cities = new City[nbCities];
		this.nbIterations = 1000;

		// set les attributs statics de la classe Pheromone
		Pheromone.borneMax = borneMax;
		Pheromone.borneMin = borneMin;
		Pheromone.evaporation = evaporation;

		//On cree les villes
		for (int i = 0; i < nbCities; i++) {
			cities[i] = new City(windowSizeX, windowSizeY);
		}

		//On initialise les pheromones et les distances
		for (int i = 0; i < nbCities; i++) {
			distances[i][i] = 0;
			for (int j = i; j < nbCities; j++) {
				pheromones[i][j] = new Pheromone(cities[i], cities[j]);
				pheromones[j][i] = pheromones[i][j];
				distances[i][j] = cities[i].getDistance(cities[j]);
				distances[j][i] = distances[i][j];
			}
		}

		optimalLength = nbCities; // Ici optimal length servira juste pour la methode setPheromone

	}

	// Methode calculant le taux de pheromone entre deux villes en fonction de la
	// longueur du chemin
	public void setPheromones(int i, int j, int pathLength) {

		float ph = 100.f * optimalLength / (pathLength + 1.0f - optimalLength);

		pheromones[i][j].updatePheromone(ph);

	}

	// Methode reduisant la quantite de pheromones sur tous les chemins
	public void evaporate() {
		for (int i = 0; i < nbCities; i++)
			for (int j = 0; j < i; j++) {
				pheromones[i][j].evaporate();
			}

		/*
		 * if (nbIterations == 1000) { Main.f.getContentPane().add(new Interface(cities,
		 * pheromones)); Main.f.repaint(); nbIterations = 0; }
		 * 
		 * nbIterations++;
		 */
	}

}
