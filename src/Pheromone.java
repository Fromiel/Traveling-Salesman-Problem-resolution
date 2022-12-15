//Classe representant des pheromones entre deux villes
public class Pheromone {
    static public float borneMin; //Minimum de pheromones entre deux villes
    static public float borneMax; //Maximum de pheromones entre deux villes

    static public float evaporation; //Taux d'evaporation des pheromones

    //Villes reliés par ce pheromone
    public City ville1;
    public City ville2;

    public int nbPassage; //Nombre de fourmis passées par ce chemin de pheromone
    public float quantitePheromone; //Quantite de pheromones sur ce chemin


    //Constructeur de pheromone qui va initialiser les deux villes reliées et mettre la quantite de pheromone au minimum
    Pheromone(City ville1, City ville2)
    {
        this.ville1 = ville1;
        this.ville2 = ville2;
        quantitePheromone = borneMin;
    }


    //Met a jour la quantite de pheromone en ajoutant la valeur passée en argument (la quantite de pheromone reste en borneMin et borneMax)
    //On incremente aussi le nombre de passage
    //value : quantite de pheromones à ajouter
    public void updatePheromone(float value)
    {
        quantitePheromone += value;
        if(quantitePheromone < borneMin) quantitePheromone = borneMin;
        if (quantitePheromone > borneMax) quantitePheromone = borneMax;

        nbPassage++;
    }

    //On effectue l'evaporation des pheromones (on enleve evaporate% de pheromones)
    public void evaporate()
    {
        quantitePheromone = quantitePheromone * (100-evaporation) / 100;
        if ((int)quantitePheromone < borneMin)
            quantitePheromone = borneMin;
    }




}
