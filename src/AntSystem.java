import java.util.Vector;

//Classe utilisée sur un thread pour faire fonctionner l'algorithme de colonies fourmis
public class AntSystem implements Runnable
{
    private final Vector<Ant> ants; //Liste des fourmis
    private final Problem problem; //Problem
    private final int nbitter; //Nombre d'iterations
    public static volatile int bestLength; //Meilleur solution trouvée
    private Vector<Integer> bestSolution; //Chemin de la meilleur solution trouvée

    private MeasureTime m; //Mesure le temps du programme
    private int currentIteration; //L'iteration actuelle

    //Méthode appelée lorsqu'une solution est trouvée, si elle est meilleur que la meilleur solution précédente, on remplace la meilleur solution par cette solution
    private void notifySolution(int value, Vector<Integer> cities)
    {
        if (bestLength < 0){
            bestLength  = value;
            bestSolution = cities;
        }else{
            pathCount ++;

            if (value < bestLength){
                bestLength = value;
                bestSolution = cities;
                System.out.println(bestLength);
                System.out.println(bestSolution);
            }
        }
    }

    public int pathCount; //Nombre de chemins trouvés

    //Constructeur de AntSystem, initialise le problem, la liste de forumis, le nombre d'itérations et la mesure du temps
    public AntSystem(Problem problem, int nbAnts,int bestLength,int nbitter, MeasureTime m)
    {
        this.m = m;
        this.problem = problem;
        ants = new Vector<>(nbAnts);
        for(int i = 0; i < nbAnts; i++)
            ants.add(new Ant(problem));
        this.nbitter = nbitter;
        AntSystem.bestLength = bestLength;
        pathCount = 0;
        currentIteration = 0;
    }

    // Méthode appelée pour effectuer la recherche du plus court chemin en fonction du nombre d'itérations passé en argument
    // n : nombre d'itération
    public void run()
    {
        for (currentIteration=0; currentIteration<nbitter; currentIteration++){

            // process each ant
            for (Ant ant : ants)
            {
                try{
                    ant.frame();
                }
                catch(AntException e){
                if (e.state == AntException.antExceptionEnum.ToRegister)
                    notifySolution(e.ant.tmpVisitedLength, e.ant.visitedCities);

                //Si on a trouve le chemin optimal
                if(bestLength <= problem.optimalLength) {
                    System.out.println("Solution optimale trouvée en "+currentIteration+" iterations");
                    System.out.println(bestLength);
                    System.out.println(bestSolution);
                    m.displayTotalTime();
                    return;
                }

				ants.set(ants.indexOf(ant), new Ant(problem));
                }
            }


            // Evapore les pheromones
            if (currentIteration % 20 == 0)
                problem.evaporate();

        }
        m.displayTotalTime();
    }
    

}
