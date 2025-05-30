package game;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import adversaires.Adversaire;
import interfacesPersonnages.Barbare;
import interfacesPersonnages.Magicien;
import interfacesPersonnages.Paysant;
import joueurs.Joueur;
import model.Personnage;

public class Combat {

    private final Joueur joueur;
    private final Adversaire adversaire;
    private final Scanner scanner = Input.getScanner();

    public Combat(Joueur joueur, Adversaire adversaire) {
        this.joueur = joueur;
        this.adversaire = adversaire;
    }

    public boolean lancer() {
        System.out.println("\nDébut du combat entre " + joueur.getNom() + " et " + adversaire.getNom());
        attendreEntreePourContinuer();

        while (joueur.getPv() > 0 && adversaire.getPv() > 0) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.print("\nStats joueur : " );
            joueur.afficherStats();
            System.out.print("\nStats ennemi : " );
            adversaire.afficherStats();

            joueur.resetDefense();
            adversaire.resetDefense();

            System.out.println("\n--- Tour de Combat ---");
            afficherPv();

            List<Action> actionsJoueur = joueur.getActionsDisponibles();
            List<Action> actionsAdversaire = adversaire.getActionsDisponibles();

            Action actionJoueur = demanderActionJoueur(actionsJoueur);

            Action actionAdversaire = actionsAdversaire.get(new Random().nextInt(actionsAdversaire.size()));
            System.out.println(adversaire.getNom() + " a choisi : " + actionAdversaire.name());

            appliquerParade(joueur, actionJoueur);
            appliquerParade(adversaire, actionAdversaire);

            appliquerAttaque(joueur, adversaire, actionJoueur);
            appliquerAttaque(adversaire, joueur, actionAdversaire);

            afficherPv();
            attendreEntreePourContinuer();

            if (joueur.getPv() <= 0) {
                System.out.println("\nVous êtes mort...");
                return false;
            }
            if (adversaire.getPv() <= 0) {
                System.out.println("\nVous avez vaincu " + adversaire.getNom() + " !");
                return true;
            }
        }
        return false;
    }

    private Action demanderActionJoueur(List<Action> actions) {
        System.out.println("\nChoisissez une action :");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println((i + 1) + ". " + actions.get(i));
        }
        int choix;
        do {
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
        } while (choix < 1 || choix > actions.size());

        return actions.get(choix - 1);
    }

    private void appliquerParade(Personnage perso, Action action) {
        switch (action) {
            case PARADE_MELEE -> {
                if (perso instanceof Barbare b) b.paradeMelee();
                else if (perso instanceof Paysant p) p.paradeMelee();
                else if (perso instanceof Magicien m) m.paradeMelee();
            }
            
            case PARADE_DISTANCE -> {
                if (perso instanceof Barbare b) b.paradeDistance();
                else if (perso instanceof Paysant p) p.paradeDistance();
                else if (perso instanceof Magicien m) m.paradeDistance();
            }
            
            default -> {}
        }
    }


    private void appliquerAttaque(Personnage attaquant, Personnage cible, Action action) {
        switch (action) {
            case ATTAQUE_MELEE -> {
                if (attaquant instanceof Barbare b)  b.attaqueMelee(cible);
                else if (attaquant instanceof Paysant p) p.attaqueMelee(cible);
                
            }

            case ATTAQUE_DISTANCE -> {
                if (attaquant instanceof Magicien m) m.attaqueDistance(cible);
                else if (attaquant instanceof Paysant p) p.attaqueDistance(cible);
                
            }
            
            default -> {}
        }
    }


    private void afficherPv() {
        System.out.printf("%s : %d/%d PV |  %s : %d/%d PV\n",
                joueur.getNom(), joueur.getPv(), joueur.getPvMax(),
                adversaire.getNom(), adversaire.getPv(), adversaire.getPvMax());
    }

    private void attendreEntreePourContinuer() {

        if (scanner.hasNextLine()) {
        scanner.nextLine();
    }
        System.out.print("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
}

