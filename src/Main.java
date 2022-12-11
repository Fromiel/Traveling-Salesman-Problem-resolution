

public class Main {
    public static void main(String[] args) {

        System.out.println("Debut du programme :");

        // villes, borne sup, borne inf, coeff d'evaporation en %
        int nbVilles = 10, nbIterations = 100000000, nbFourmis = 10;

        Problem p = new Problem(nbVilles, 1, 5000, 0.01f, 500, 500);

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