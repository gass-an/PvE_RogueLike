package Game;

import adversaires.Adversaire;
import adversaires.AdversaireNom;
import joueurs.Joueur;

public class Game {
    private Joueur joueur;
    private Adversaire adversaire;
    private Combat combat;
    private CreationEntite creation = new CreationEntite();
    
    public void start() {
        AdversaireNom.resetNoms();
        
        joueur = creation.creationJoueur();
        joueur.afficherStats();
        adversaire = creation.creationAdversaire(joueur.getLvl());
        adversaire.sePresente();
        adversaire.afficherStats();

        combat = new Combat(joueur, adversaire);
        combat.lancer();
    } 
}
