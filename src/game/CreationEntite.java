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

/**
 * Classe responsable de la création des entités (joueur et adversaire).
 * Elle interagit avec l’utilisateur pour générer un {@link JoueurPaysant} au début de la partie,
 * puis génère aléatoirement un {@link Adversaire} (paysan, barbare ou magicien) selon le niveau du joueur.
 */
public class CreationEntite {
    private final Scanner scanner = Input.getScanner();

    /**
     * Crée un joueur de type Paysant en demandant :
     * <ul>
     *   <li>Son nom</li>
     *   <li>La répartition de 3 points de compétences (force, intelligence, agilité)</li>
     * </ul>
     *
     * @return Nouvelle instance de {@link JoueurPaysant} initialisée avec les valeurs saisies.
     */
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

    /**
     * Génère un adversaire aléatoire en fonction du niveau {@code level}.
     * Choisit au hasard entre {@link AdversairePaysant}, {@link AdversaireBarbare} et {@link AdversaireMagicien}.
     *
     * @param level Niveau du joueur pour calibrer les statistiques de l’adversaire.
     * @return Nouvelle instance d’une sous-classe de {@link Adversaire}.
     * @throws IllegalStateException si la valeur aléatoire obtenue n’est pas dans [0, 2].
     */
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
