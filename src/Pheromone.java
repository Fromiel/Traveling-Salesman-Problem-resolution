public class Pheromone {
    static public float borneMin;
    static public float borneMax;

    static public float evaporation;
    public City ville1;
    public City ville2;
    public int nbPassage;
    public float quantitePheromone;


    Pheromone(City ville1, City ville2)
    {
        this.ville1 = ville1;
        this.ville2 = ville2;
        quantitePheromone = borneMin;
    }


    public void updatePheromone(float value)
    {
        quantitePheromone += value;
        if(quantitePheromone < borneMin) quantitePheromone = borneMin;
        if (quantitePheromone > borneMax) quantitePheromone = borneMax;

        nbPassage++;
    }

    public void evaporate()
    {
        quantitePheromone = quantitePheromone * (100-evaporation) / 100;
        if ((int)quantitePheromone < borneMin)
            quantitePheromone = borneMin;
    }




}
