package game;

import java.util.Random;
import java.util.Scanner;

import joueurs.JoueurPaysant;
import joueurs.Joueur;
import adversaires.Adversaire;
import adversaires.AdversaireBarbare;
import adversaires.AdversaireMagicien;
import adversaires.AdversaireNom;
import adversaires.AdversairePaysant;

public class CreationEntite {
    private final Scanner scanner = Input.getScanner();

    public Joueur creationJoueur(){
        String nom;
        int points = 3;
        int force = 0, intel = 0, agi = 0;

        System.out.println("Quel est votre nom ?");
        nom = scanner.nextLine();

        while (points > 0) {
            System.out.println("\nVous devez placer " + points + " de compétences dans les catégories suivantes :\n"+ 
            "Force / Intelligence / Agilité");
            System.out.print("Points en force : ");
            force += scanner.nextInt();
            System.out.print("Points en intelligence : ");
            intel += scanner.nextInt();
            System.out.print("Points en agilité : ");
            agi += scanner.nextInt();
            
            points = 3 - (intel + agi + force);
            if (points < 0) {
                System.out.println("Vous avez dépassé le total. Recommencez.");
                points = 3; intel = 0; agi = 0; force = 0;
            }
        }
        return new JoueurPaysant(nom, force, intel, agi);
    }

    public Adversaire creationAdversaire(int level) {
        Random rand = new Random();
        int randInt = rand.nextInt(3);

        switch (randInt) {
            case 0 -> {
                return new AdversairePaysant(AdversaireNom.getRandomNom(), level);
            }
        
            case 1 -> {
                return new AdversaireBarbare(AdversaireNom.getRandomNom(), level);
            }
            
            case 2 -> {
                return new AdversaireMagicien(AdversaireNom.getRandomNom(), level);
            }   
            default -> throw new IllegalStateException("Valeur inattendue : " + randInt);
        }
    }
}
