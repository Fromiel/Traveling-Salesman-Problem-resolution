import java.util.Vector;

public class Ant {

    private long currentArcSize;
    private long currentArcPos;
    private int currentOrigin;
    private int currentDestination;

    private Problem problem;

    private void findNextSearchDestination() throws AntException {
        switch(state) {
            // si la fourmi vient d'être créée
            case Nothing:
            {
                visitedCities.add(0);
                for (int tmp : citiesStillToVisit) {
                    if (tmp == 0) {
                        citiesStillToVisit.removeElement(tmp);
                        break;
                    }
                }

                int dest = getNearCity(0);
                state = StateEnum.SearchingPath;
                currentOrigin = 0;
                currentDestination = dest;
                currentArcPos = 0;
                currentArcSize = problem.distances[0][currentDestination];

                break;
            }
            // si la fourmi cherche son chemin dans le graphe
            case SearchingPath:
            {
                // on a atteint currentDestination
                //tmpVisitedLength += problem.distances[currentOrigin][currentDestination];
                visitedCities.add(currentDestination);

                for (int tmp : citiesStillToVisit) {
                    if (tmp == currentDestination){
                        citiesStillToVisit.removeElement(tmp);
                        break;
                    }
                }

                if (citiesStillToVisit.size() == 0){
                    // plus rien à visiter, le chemin est complet
                    // on revient vers le nid
                    tmpVisitedLength += problem.distances[currentDestination][0];

                    state = StateEnum.Returning;
                    currentOrigin =  visitedCities.size()-1;
                    currentDestination = visitedCities.size()-2;
                    currentArcSize = problem.distances[visitedCities.get(currentOrigin)][visitedCities.get(currentDestination)];
                    currentArcPos = currentArcSize;
                    return;
                }

                int dest = getNearCity(currentDestination);
                currentOrigin = currentDestination;
                currentDestination = dest;
                currentArcSize = problem.distances[currentOrigin][currentDestination];
                currentArcPos = 0;
                break;
            }
            // si la fourmi revient au nid
            case Returning:
            {
                if (currentDestination == 0){
                    // retourné au nid avec succès
                    problem.setPheromones(visitedCities.get(currentOrigin), visitedCities.get(currentDestination), tmpVisitedLength);

                    // sauver le résultat, changer de fourmi
                    AntException e = new AntException();
                    e.ant = this;
                    e.state = AntException.antExceptionEnum.ToRegister;
                    throw e;
                }

                // trouver la ville précédemment visitée et la passer en destination
                // mettre des phéromones sur l'arc parcouru
                problem.setPheromones(visitedCities.get(currentOrigin), visitedCities.get(currentDestination), tmpVisitedLength);
                currentOrigin = currentDestination;
                currentDestination = currentOrigin-1;
                currentArcSize = problem.distances[visitedCities.get(currentOrigin)][visitedCities.get(currentDestination)];
                currentArcPos = currentArcSize;

                break;
            }
        }
    }

    private int getNearCity(int from) throws AntException {
        float pheromoneSize = 0;
        for (int i = 0; i < citiesStillToVisit.size(); i++){
        if (citiesStillToVisit.get(i) == from)
            continue;
        pheromoneSize  += problem.pheromones[from][citiesStillToVisit.get(i)];
        }


        float found = (float)((int)(Math.random() * pheromoneSize*1000)/1000.0)  ;
        float tmpPheromones = 0;
        int ii = 0;
        while (ii < citiesStillToVisit.size()){
        if (citiesStillToVisit.get(ii) == from){
            ii++;
            continue;
        }

        tmpPheromones  += problem.pheromones[currentDestination][citiesStillToVisit.get(ii)];

        if (tmpPheromones > found)
            break;

        ii ++;
    }
        if (ii == citiesStillToVisit.size()){
            // aucune solution acceptable, détruire la fourmi courante
            AntException e = new AntException();
            e.ant = this;
            e.state = AntException.antExceptionEnum.ToDelete;
            throw e;
        }

        return citiesStillToVisit.get(ii);
    }

    public enum StateEnum { SearchingPath, Returning, Nothing}

    public StateEnum state;

    public Vector<Integer> visitedCities;
    public Vector<Integer> citiesStillToVisit;

    public int tmpVisitedLength;


    public Ant(Problem problem)
    {
        tmpVisitedLength = 0;
        currentArcPos = -1;
        currentDestination = 0;
        currentOrigin = 0;
        state = StateEnum.Nothing;
        this.problem = problem;
        visitedCities = new Vector<>();
        citiesStillToVisit = new Vector<>();

        for (int i=0; i < problem.nbCities; i++)
            citiesStillToVisit.add(i);
    }


    public void frame() throws AntException {
        switch(state){
            case SearchingPath:
                tmpVisitedLength ++;
            case Returning:
                currentArcPos++;
                if (currentArcPos >= currentArcSize)
                    findNextSearchDestination();
                break;
            case Nothing:
                findNextSearchDestination();
                break;
        }
    }

}
