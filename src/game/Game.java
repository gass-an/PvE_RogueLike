package game;

import java.util.List;
import java.util.Scanner;

import adversaires.Adversaire;
import adversaires.AdversaireNom;
import joueurs.Joueur;
import model.Item;
import model.Shop;

/**
 * Classe principale du jeu « PvE RogueLike ».
 * Gère la boucle de partie :
 * <ol>
 *   <li>Création du joueur</li>
 *   <li>Enchaînement des combats contre un maximum de 10 adversaires</li>
 *   <li>Évolution du joueur</li>
 *   <li>Accès au shop (tous les 2 combats)</li>
 *   <li>Fin de partie lorsque le joueur atteint le niveau 10 ou meurt</li>
 * </ol>
 */
public class Game {
    private Joueur joueur;
    private Adversaire adversaire;
    private Combat combat;
    private EvolutionJoueur evolution;
    private CreationEntite creation = new CreationEntite();
    private boolean joueurVainqueur;
    private int combatCounter = 0;
    
    /**
     * Démarre la boucle principale du jeu.
     * Initialise le joueur, puis enchaîne :
     * combats / évolutions / shop / fin de partie.
     */
    public void start() {
        AdversaireNom.resetNoms();
        
        joueur = creation.creationJoueur();
        joueur.afficherStats();
        evolution = new EvolutionJoueur(joueur);

        while (joueur.getPv() > 0 || joueur.getLvl() < 10) {
            adversaire = creation.creationAdversaire(joueur.getLvl());
            adversaire.sePresente();
            adversaire.afficherStats();
            
            combat = new Combat(joueur, adversaire);
            joueurVainqueur = combat.lancer();
            
            if (joueurVainqueur) {
                if (joueur.getLvl() == 9) {
                    System.out.println(
                        "Félicitation vous avez atteint le niveau 10, fin du jeu !\n" + 
                        "Merci d'avoir jouer !\n" +
                        "\nPvE RogueLike creator's : Adrien et Anthony"
                        );
                    return;
                }

                joueur.gagneUnNiveau();
                evolution.gagnerPointCompetence();
                
                if (evolution.evolutionEstDisponible()) {
                    joueur = evolution.evolution();
                }
            }
            else {
                System.out.println("Game Over");
                return;
            };

            combatCounter++;

            if (combatCounter % 2 == 0) {
                Shop shop = new Shop();
                shop.genererItem();
                shop.afficherShop();
                gestionAchat(shop);
            }
        }   
    } 

    /**
     * Gère l’achat d’un item dans le {@link Shop}.
     * Affiche l’argent du joueur, lit un entier correspondant
     * au numéro de l’item (ou 0 pour ignorer). Achète et équipe le
     * cas échéant si le joueur a assez d’argent.
     *
     * @param shop Instance de {@link Shop} contenant la liste des items disponibles.
     */
    private void gestionAchat(Shop shop) {
        Scanner scanner = Input.getScanner();
        System.out.println("Votre argent : " + joueur.getMoney());
        System.out.println("Entrez le numéro de l'item à acheter (ou '0' pour ignorer) :");
        int choix = scanner.nextInt();

        if (!List.of(1, 2, 3).contains(choix)) return;

        Item item = shop.getItemByNumber(choix);
        if (item == null) {
            System.out.println("Item introuvable.");
            return;
        }

        if (joueur.haveMoney(item.getPrix())) {
            joueur.removemoney(item.getPrix());
            joueur.newItem(item);
            joueur.equiperItem(item);
        } else {
            System.out.println("Vous n'avez pas assez d'argent !");
        }
    }

}