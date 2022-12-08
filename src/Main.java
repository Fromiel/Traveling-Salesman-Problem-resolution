

public class Main {
    public static void main(String[] args) {
        // villes, borne sup, borne inf, coeff d'�vaporation en %
        System.out.println("Debut du programme :");

        int nbVilles = 100, nbIterations = 100000, nbFourmis = 1000;

        Problem p = new Problem(nbVilles, 5000, 1, 0.1f);

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