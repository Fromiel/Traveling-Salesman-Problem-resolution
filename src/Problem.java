
public class Problem {

    public Problem(int nbCities, float borneMin, float borneMax, float evaporation)
    {
        this.nbCities = nbCities;
        this.borneMin = borneMin;
        this.borneMax = borneMax;
        this.evaporation = evaporation;
        this.distances = new int[nbCities][nbCities];
        this.pheromones = new float[nbCities][nbCities];
        for (int i = 0; i < nbCities; i++) {
            for(int j = 0; j < nbCities; j++)
            {
                distances[i][j] = 0;
                pheromones[i][j] = borneMin;
            }
        }

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
    }

    public void setPheromones(int i, int j, int pathLength)
    {
        /*float newValue = pheromones[i][j] + (1 / (float) pathLength);
        if(newValue > borneMax)
            newValue = borneMax;
        if(newValue < borneMin)
            newValue = borneMin;

        pheromones[i][j] = newValue;*/

        float ph = 100.f * optimalLength / (pathLength + 1 - optimalLength);

        pheromones[i][j] += ph;

        if( pheromones[i][j] < borneMin) pheromones[i][j] = borneMin;
        if (pheromones[i][j] > borneMax) pheromones[i][j] = borneMax;

        pheromones[j][i] = pheromones[i][j];
    }

    public void evaporate()
    {
        for (int i=0; i<nbCities; i++)
            for (int j=0; j<i; j++){
                pheromones[i][j] = pheromones[i][j] * (100-evaporation) / 100;
                if ((int)pheromones[i][j] < borneMin)
                    pheromones[i][j] = borneMin;
                pheromones[j][i] = pheromones[i][j];
            }
    }

    public int nbCities;
    private float borneMax, borneMin;
    private float evaporation;

    public int optimalLength;

    // arcs
    public int distances[][];

    // pheromones
    public float pheromones[][];
}
