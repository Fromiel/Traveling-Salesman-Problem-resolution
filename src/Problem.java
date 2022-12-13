
public class Problem {

    //Constructeur du probleme qui va initialiser les distances entre les villes et hardcoder une solution optimale, et initialiser les pheromones
   /* public Problem(int nbCities, float borneMin, float borneMax, float evaporation)
    {
        this.nbCities = nbCities;
        this.borneMin = borneMin;
        this.borneMax = borneMax;
        this.evaporation = evaporation;
        this.distances = new int[nbCities][nbCities];
        this.pheromones = new Pheromone[nbCities][nbCities];
        for (int i = 0; i < nbCities; i++) {
            for(int j = 0; j < nbCities; j++)
            {
                distances[i][j] = 0;
                //pheromones[i][j] = borneMin;
            }
        }



        //creer des distances aleatoires entre les ville
        for (int i = 0; i < nbCities; i++) {
            distances[i][i] = 0;
            for(int j = i + 1; j < nbCities; j++)
            {
                distances[i][j] = (int) (Math.random() * 100) + 1;
                distances[j][i] = distances[i][j];
            }
        }

        // solution optimale
        for (int i=0; i < nbCities; i++)
            distances[i][(i+1)%nbCities] = distances[(i+1)%nbCities][i] = 1;

        optimalLength = nbCities;
    }*/


    //Constructeur de problem pour creer des villes de coordonnees aleatoires
    public Problem(int nbCities, float borneMin, float borneMax, float evaporation, int windowSizeX, int windowSizeY)
    {
        this.nbCities = nbCities;
        this.borneMin = borneMin;
        this.borneMax = borneMax;
        this.evaporation = evaporation;
        this.distances = new int[nbCities][nbCities];
        this.pheromones = new Pheromone[nbCities][nbCities];
        this.cities = new City[nbCities];

        //set les attributs statics de la classe Pheromone
        Pheromone.borneMax = borneMax;
        Pheromone.borneMin = borneMin;
        Pheromone.evaporation = evaporation;

        for (int i = 0; i < nbCities; i++) {
            cities[i] = new City(windowSizeX, windowSizeY);
        }

        for (int i = 0; i < nbCities; i++) {
            distances[i][i] = 0;
            for(int j = i; j < nbCities; j++)
            {
                pheromones[i][j] = new Pheromone(cities[i], cities[j]);
                pheromones[j][i] = pheromones[i][j];
                distances[i][j] = cities[i].getDistance(cities[j]);
                distances[j][i] = distances[i][j];
            }
        }

        optimalLength = nbCities; //Ici optimal length servira juste pour la methode setPheromone

    }

    //Methode calculant le taux de pheromone entre deux villes en fonction de la longueur du chemin
    public void setPheromones(int i, int j, int pathLength)
    {

        float ph = 100.f * optimalLength / (pathLength + 1.0f - optimalLength);

        pheromones[i][j].updatePheromone(ph);

    }

    //Methode reduisant la quantite de pheromones sur tous les chemins
    public void evaporate()
    {
        for (int i=0; i < nbCities; i++)
            for (int j=0; j<i; j++)
            {
                pheromones[i][j].evaporate();
            }
    }

    public int nbCities;

    public City[] cities;
    private float borneMax, borneMin;
    private float evaporation;

    public int optimalLength;


    // arcs
    public int distances[][];

    // pheromones
    public Pheromone pheromones[][];
}
