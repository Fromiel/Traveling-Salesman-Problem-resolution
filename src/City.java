//Classe représentant une ville
public class City {
    public int x; //Coordonnee x de la ville
    public int y; //Coordonnee y de la ville

    //Constructeur qui met des coordonnees aleatoire entre 0 et maxX-1 et 0 et maxY - 1 pour x et y respectivement
    public City(int maxX, int maxY)
    {
        x = (int)(Math.random() * maxX);
        y = (int)(Math.random() * maxY);
    }



    //Calcule la distance entre cette ville et une autre ville
    public int getDistance(City other)
    {
        return (int) Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }





}
