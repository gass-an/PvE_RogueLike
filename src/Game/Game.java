package game;

import java.util.List;
import java.util.Scanner;

import adversaires.Adversaire;
import adversaires.AdversaireNom;
import joueurs.Joueur;
import model.Item;
import model.Shop;

public class Game {
    private Joueur joueur;
    private Adversaire adversaire;
    private Combat combat;
    private EvolutionJoueur evolution;
    private CreationEntite creation = new CreationEntite();
    private boolean joueurVainqueur;
    private int combatCounter = 0;
    
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
