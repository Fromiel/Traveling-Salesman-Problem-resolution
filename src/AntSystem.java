import java.util.Vector;

public class AntSystem
{

    private int nbAnts;
    private Vector<Ant> ants;
    private Problem problem;

    private int bestLength;
    private Vector<Integer> bestSolution;

    private int currentIteration;

    private void notifySolution(int value, Vector<Integer> cities)
    {
        if (bestLength == -1){
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

    public int pathCount;

    public AntSystem(Problem problem, int nbAnts)
    {
        this.problem = problem;
        this.nbAnts = nbAnts;
        ants = new Vector<>(nbAnts);
        for(int i = 0; i < nbAnts; i++)
            ants.add(new Ant(problem));

        bestLength = 999999;
        pathCount = 0;
        currentIteration = 0;
    }

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

                if(bestLength <= problem.optimalLength)
                    return;

				ants.set(ants.indexOf(ant), new Ant(problem));
                }
            }


            // evaporate the pheromones
            if (currentIteration % 20 == 0)
                problem.evaporate();

            //debug
            if (currentIteration%1000==0)
                System.out.println(bestLength);
        }
    }
}
