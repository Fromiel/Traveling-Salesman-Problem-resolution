import java.util.Vector;

public class AntSystem
{

    private int nbAnts;
    private Vector<Ant> ants;
    private Problem problem;

    private int bestLength; //Meilleur solution trouvée
    private Vector<Integer> bestSolution; //Chemin de la meilleur solution trouvée

    private int currentIteration;

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
            }
        }
    }

    public int pathCount; //Nombre de chemins trouvés

    //Constructeur qui initialise AntSystem avec les données du problème et le nombre de fourmis
    public AntSystem(Problem problem, int nbAnts)
    {
        this.problem = problem;
        this.nbAnts = nbAnts;
        ants = new Vector<>(nbAnts);
        for(int i = 0; i < nbAnts; i++)
            ants.add(new Ant(problem));

        bestLength = -1;
        pathCount = 0;
        currentIteration = 0;
    }

    // Méthode appelée pour effectuer la recherche du plus court chemin en fonction du nombre d'itérations passé en argument
    // n : nombre d'itération
    public void run(int n)
    {
        for (currentIteration=0; currentIteration<n; currentIteration++){

            // process each ant
            for (Ant ant : ants)
            {
                try{
                    ant.frame();
                }
                catch(AntException e){
                if (e.state == AntException.antExceptionEnum.ToRegister)
                    notifySolution(e.ant.tmpVisitedLength, e.ant.visitedCities);

                //Si on a trovue le chemin optimal
                if(bestLength <= problem.optimalLength) {
                    System.out.println("Solution optimale trouvée en "+currentIteration+" iterations");
                    System.out.println(bestLength);
                    System.out.println(bestSolution);
                    return;
                }

				ants.set(ants.indexOf(ant), new Ant(problem));
                }
            }


            // evaporate the pheromones
            if (currentIteration % 20 == 0)
                problem.evaporate();

            //debug
            if (currentIteration%100000==0 && currentIteration != 0)
            {
                System.out.println(bestLength);
                System.out.println(bestSolution);
            }
        }
    }
}
