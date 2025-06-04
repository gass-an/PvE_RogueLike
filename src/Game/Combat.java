package game;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import adversaires.Adversaire;
import interfacesPersonnages.Barbare;
import interfacesPersonnages.Magicien;
import interfacesPersonnages.Paysan;
import joueurs.Joueur;
import model.Personnage;

/**
 * Classe gérant le déroulé d’un combat tour par tour entre un {@link Joueur} et un {@link Adversaire}.
 * <p>
 * Le combat se termine quand l’un des deux tombe à 0 PV.
 * Lors de chaque tour, le joueur choisit une action, l’adversaire choisit une action aléatoirement.
 * Les parades sont appliquées en premier, puis les attaques. Une méthode d’attente sur appui sur Entrée
 * est utilisée pour rythmer les tours.
 * </p>
 */
public class Combat {

    private final Joueur joueur;
    private final Adversaire adversaire;
    private final Scanner scanner = Input.getScanner();

    public Combat(Joueur joueur, Adversaire adversaire) {
        this.joueur = joueur;
        this.adversaire = adversaire;
    }

    /**
     * Lance la boucle de combat jusqu’à ce que le joueur ou l’adversaire atteigne 0 PV.
     * Les tours se déroulent de la manière suivante :
     * <ol>
     *   <li>Affichage des statistiques et PV restants de chaque combattant</li>
     *   <li>Réinitialisation des états de parade (adapté par {@link Personnage#resetDefense()})</li>
     *   <li>Le joueur choisit son {@link Action}</li>
     *   <li>L’adversaire choisit aléatoirement un {@link Action}</li>
     *   <li>On applique d’abord les parades (mêlée et distance) pour chaque combattant</li>
     *   <li>On applique ensuite les attaques (mêlée ou distance) pour chaque combattant</li>
     *   <li>On affiche les PV restants et on demande à l’utilisateur d’appuyer sur Entrée pour continuer</li>
     *   <li>On vérifie la condition de fin : si un combattant a ≤ 0 PV, le combat se termine</li>
     * </ol>
     *
     * @return true si le {@code joueur} a vaincu l’adversaire, false si le {@code joueur} est mort.
     */
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

    /**
     * Affiche le menu déroulant des actions disponibles pour le joueur et lit
     * le choix (1 à size). Répète la demande tant qu’une saisie invalide est détectée.
     *
     * @param actions Liste des {@link Action} possibles pour le joueur.
     * @return L’action sélectionnée par l’utilisateur.
     */
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

    /**
     * Applique la parade correspondante selon l’{@code action} sur l’objet {@code perso}.
     * Utilise {@code instanceof} pour dispatcher vers la bonne implémentation d’interface :
     * <ul>
     *   <li>Si {@code perso} est {@link Barbare}, appelle {@link Barbare#paradeMelee()} ou {@link Barbare#paradeDistance()}</li>
     *   <li>Si {@code perso} est {@link Paysan}, appelle {@link Paysan#paradeMelee()} ou {@link Paysan#paradeDistance()}</li>
     *   <li>Si {@code perso} est {@link Magicien}, appelle {@link Magicien#paradeMelee()} ou {@link Magicien#paradeDistance()}</li>
     * </ul>
     *
     * @param perso  Instance de {@link Personnage} (juger par runtime pour l’implémentation correcte).
     * @param action {@link Action} choisie (devant être une parade).
     */
    private void appliquerParade(Personnage perso, Action action) {
        switch (action) {
            case PARADE_MELEE -> {
                if (perso instanceof Barbare b) b.paradeMelee();
                else if (perso instanceof Paysan p) p.paradeMelee();
                else if (perso instanceof Magicien m) m.paradeMelee();
            }
            
            case PARADE_DISTANCE -> {
                if (perso instanceof Barbare b) b.paradeDistance();
                else if (perso instanceof Paysan p) p.paradeDistance();
                else if (perso instanceof Magicien m) m.paradeDistance();
            }
            
            default -> {}
        }
    }


    /**
     * Applique l’attaque correspondante selon l’{@code action} entre l’attaquant et la cible.
     * Utilise {@code instanceof} pour dispatcher l’appel à la bonne méthode de l’interface :
     * <ul>
     *   <li>Si {@code action} est ATTAQUE_MELEE et {@code attaquant} est {@link Barbare}, appelle {@link Barbare#attaqueMelee(Personnage)}</li>
     *   <li>Si {@code action} est ATTAQUE_MELEE et {@code attaquant} est {@link Paysan}, appelle {@link Paysan#attaqueMelee(Personnage)}</li>
     *   <li>Si {@code action} est ATTAQUE_DISTANCE et {@code attaquant} est {@link Magicien}, appelle {@link Magicien#attaqueDistance(Personnage)}</li>
     *   <li>Si {@code action} est ATTAQUE_DISTANCE et {@code attaquant} est {@link Paysan}, appelle {@link Paysan#attaqueDistance(Personnage)}</li>
     * </ul>
     *
     * @param attaquant Instance de {@link Personnage} réalisant l’attaque.
     * @param cible     Instance de {@link Personnage} subissant l’attaque.
     * @param action    {@link Action} choisie (devant être une attaque).
     */
    private void appliquerAttaque(Personnage attaquant, Personnage cible, Action action) {
        switch (action) {
            case ATTAQUE_MELEE -> {
                if (attaquant instanceof Barbare b)  b.attaqueMelee(cible);
                else if (attaquant instanceof Paysan p) p.attaqueMelee(cible);
                
            }

            case ATTAQUE_DISTANCE -> {
                if (attaquant instanceof Magicien m) m.attaqueDistance(cible);
                else if (attaquant instanceof Paysan p) p.attaqueDistance(cible);
                
            }
            
            default -> {}
        }
    }


    /**
     * Affiche les points de vie actuels du joueur et de l’adversaire au format :
     * <pre>
     * {@code
     * JoueurNom : X/Y PV | AdversaireNom : A/B PV
     * }
     * </pre>
     */
    private void afficherPv() {
        System.out.printf("%s : %d/%d PV |  %s : %d/%d PV\n",
                joueur.getNom(), joueur.getPv(), joueur.getPvMax(),
                adversaire.getNom(), adversaire.getPv(), adversaire.getPvMax());
    }

    /**
     * Attend que l’utilisateur appuie sur Entrée pour passer au tour suivant.
     * Prend en compte un retour à la ligne résiduel si la saisie précédente était un entier.
     */
    private void attendreEntreePourContinuer() {

        if (scanner.hasNextLine()) {
        scanner.nextLine();
    }
        System.out.print("\nAppuyez sur Entrée pour continuer...");
        scanner.nextLine();
    }
}