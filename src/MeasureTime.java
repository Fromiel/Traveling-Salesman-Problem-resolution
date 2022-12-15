//Classe permettant de mesurer et d'afficher le temps d'execution du programme lors de la fin de l'execution de tous les threads
public class MeasureTime {

    public long start; //Temps de début (en nanosecondes)
    public long end; //Temps de fin (en nanosecondes)

    public int currentThread = 1; //Thread actuel

    public int totalThreads; //Nombre de threads total

    //Constructeur qui prend en argument le nombre de threads pour initialiser nbThreads et qui va aussi initialiser le temps de debut du programme
    public MeasureTime(int nbThread)
    {
        this.totalThreads = nbThread;
        this.start = System.nanoTime();
    }


    //Si currentThread est inferieur à totalThreads, on incremente currentThread, sinon on affiche le temps d'execution entre start et le temps actuel
    public void displayTotalTime()
    {
        if(currentThread != totalThreads)
        {
            currentThread++;
            return;
        }
        this.end = System.nanoTime();
        System.out.println("Durée d'exécution : " + (end - start) / 1000000);
    }

}
