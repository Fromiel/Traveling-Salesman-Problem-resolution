

public class Main {
    public static void main(String[] args) {

        System.out.println("Debut du programme :");

        // villes, borne sup, borne inf, coeff d'evaporation en %
        int nbVilles = 11, nbIterations = 1000000, nbFourmis = 100;

        Problem p = new Problem(nbVilles, 1, 5000, 0.1f);

        System.out.println("Problem créé");

        // nombre de fourmis
        AntSystem sys = new AntSystem(p, nbFourmis);

        System.out.println("Système créé");

        // unités de temps
        sys.run(nbIterations);

        System.out.println("Chemins testés");

        System.out.println("Fin du programme");
    }
}