package game;

import java.util.Scanner;

import joueurs.Joueur;
import joueurs.JoueurBarbare;
import joueurs.JoueurMagicien;

public class EvolutionJoueur {
    private final Scanner scanner = Input.getScanner();
    private final int levelForEvolution = 6;
    private final Joueur joueur;
    private boolean estPaysant = true;
    
    public EvolutionJoueur(Joueur joueur){
        this.joueur=joueur;
    }

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

    public boolean evolutionEstDisponible(){
        return (
            estPaysant && (
                joueur.getForce() >= levelForEvolution || 
                joueur.getIntelligence() >= levelForEvolution
                )
            );
    }

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
