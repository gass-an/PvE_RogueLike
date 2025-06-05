package game;

import java.util.Scanner;

import joueurs.Joueur;
import joueurs.JoueurBarbare;
import joueurs.JoueurMagicien;

/**
 * Classe gérant l’évolution du joueur en fonction de ses points de compétence.
 * Le joueur peut évoluer en Barbare (si force ≥ levelForEvolution) ou
 * en Magicien (si intelligence ≥ levelForEvolution).
 * <p>
 * Lorsqu’un seuil est atteint, l’utilisateur est invité à valider son évolution.
 * </p>
 */
public class EvolutionJoueur {
    private final Scanner scanner = Input.getScanner();
    private final int levelForEvolution = 6;
    private final Joueur joueur;
    private boolean estPaysant = true;
    
    /**
     * Constructeur principal.
     *
     * @param joueur Joueur à faire évoluer.
     */
    public EvolutionJoueur(Joueur joueur){
        this.joueur=joueur;
    }

    /**
     * Attribue 1 point de compétence au joueur.
     * L’utilisateur choisit entre : force, intelligence ou agilité.
     */
    public void gagnerPointCompetence() {
        int points = 1;

        System.out.println("\nVous avez gagner " + points + " point de compétence à placer dans une des catégories suivantes :\n"+ 
        "1. Force\n2. Intelligence\n3. Agilité");
        
        int choix = scanner.nextInt();

        switch (choix) {
            case 1 -> joueur.addForce(points);
            case 2 -> joueur.addIntelligence(points);
            case 3 -> joueur.addAgilite(points);
        }
    }

    /**
     * Indique si le joueur est encore Paysant et s’il a la possibilité d’évoluer
     * (force ≥ levelForEvolution ou intelligence ≥ levelForEvolution).
     *
     * @return true si une évolution est possible, false sinon.
     */
    public boolean evolutionEstDisponible(){
        return (
            estPaysant && (
                joueur.getForce() >= levelForEvolution || 
                joueur.getIntelligence() >= levelForEvolution
                )
            );
    }

    /**
     * Réalise l’évolution du joueur si ses compétences satisfont les seuils.
     * <ul>
     *   <li>Si force ≥ levelForEvolution et intelligence ≥ levelForEvolution,
     *       propose le choix entre Barbare ou Magicien.</li>
     *   <li>Sinon, propose l’évolution possible (Barbare ou Magicien).</li>
     *   <li>Sinon, ne modifie rien et renvoie le joueur actuel.</li>
     * </ul>
     *
     * @return Nouvelle instance de {@link JoueurBarbare} ou {@link JoueurMagicien} si évolution,
     *         ou l’instance initiale si pas d’évolution.
     */
    public Joueur evolution() {
        if (joueur.getForce() >= levelForEvolution && 
            joueur.getIntelligence() >= levelForEvolution){
                System.out.print("Vous pouvez évoluer en Barbare ou en Magicien :\n" + 
                "Tapez 1 pour évoluer en Barbare\n"+
                "Tapez 2 pour évoluer en Magicien\n"+
                "Tapez autre chose pour ne pas évoluer\n");
                int choix = scanner.nextInt();

                switch (choix) {
                    case 1 -> {
                        System.out.println("Vous êtes désormais un barbare !");
                        estPaysant = false;
                        return devientBarbare(joueur);
                        }
                
                    case 2 -> {
                        System.out.println("Vous êtes désormais un magicien !");
                        estPaysant = false;
                        return devientMagicien(joueur);
                        }
                        
                    default -> {
                        System.out.println("Vous n'évoluez pas sur ce tour");
                        return joueur;
                    }
                }
        }
        if (joueur.getForce() >= levelForEvolution){
            System.out.print("Vous pouvez évoluer en Barbare :\n" + 
            "Tapez 1 pour évoluer\n"+
            "Tapez autre chose pour ne pas évoluer\n");
            int choix = scanner.nextInt();
            switch (choix) {
                case 1 -> {
                    System.out.println("Vous êtes désormais un barbare !");
                    estPaysant = false;
                    return devientBarbare(joueur);
                    }
                default -> {
                    System.out.println("Vous n'évoluez pas sur ce tour");
                    return joueur;
                }
            }
        }
        if (joueur.getIntelligence() >= levelForEvolution){
            System.out.print("Vous pouvez évoluer en Magicien :\n" + 
            "Tapez 1 pour évoluer\n"+
            "Tapez autre chose pour ne pas évoluer\n");
            int choix = scanner.nextInt();
            switch (choix) {
                case 1 -> {
                    System.out.println("Vous êtes désormais un barbare !");
                    estPaysant = false;
                    return devientMagicien(joueur);
                    }
                default ->{ 
                    System.out.println("Vous n'évoluez pas sur ce tour");
                    return joueur;
                }
            }
        }
        return joueur;
    }
    
    /**
     * Fabrique une nouvelle instance {@link JoueurBarbare} en conservant les attributs
     * du joueur actuel : nom, statistiques, PV actuels, PV max, niveau et argent.
     *
     * @param joueur Ancienne instance de {@link Joueur}.
     * @return Nouvelle instance de {@link JoueurBarbare} ayant repris les mêmes valeurs.
     */
    private Joueur devientBarbare(Joueur joueur) {
        return new JoueurBarbare(
            joueur.getNom(),
            joueur.getForce(), 
            joueur.getIntelligence(), 
            joueur.getAgilite(), 
            joueur.getPv(), 
            joueur.getPvMax(), 
            joueur.getLvl(), 
            joueur.getMoney()
            );
    }

    /**
     * Fabrique une nouvelle instance {@link JoueurMagicien} en conservant les attributs
     * du joueur actuel : nom, statistiques, PV actuels, PV max, niveau et argent.
     *
     * @param joueur Ancienne instance de {@link Joueur}.
     * @return Nouvelle instance de {@link JoueurMagicien} ayant repris les mêmes valeurs.
     */
    private Joueur devientMagicien(Joueur joueur) {
        return new JoueurMagicien(joueur.getNom(),
            joueur.getForce(), 
            joueur.getIntelligence(), 
            joueur.getAgilite(), 
            joueur.getPv(), 
            joueur.getPvMax(), 
            joueur.getLvl(), 
            joueur.getMoney()
            );
    }
}