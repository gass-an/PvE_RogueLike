package adversaires;

import java.util.List;
import java.util.Random;

import game.Action;
import interfacesPersonnages.Paysan;
import model.Personnage;

/**
 * Classe représentant un adversaire de type Paysan.
 * Hérite de {@link Adversaire} et implémente l’interface {@link Paysan}.
 * Le Paysan peut attaquer à la fois en mêlée et à distance, et parer.
 */
public class AdversairePaysan extends Adversaire implements Paysan {
    
    /**
     * Constructeur principal.
     *
     * @param nom Nom personnalisé du Paysan.
     * @param lvl Niveau (niveau de jeu) influençant la génération des compétences.
     *            Les trois statistiques (force, intelligence, agilité) sont réparties
     *            aléatoirement de sorte que leur somme soit égale à lvl + 3. Les PV max sont
     *            tirés aléatoirement en fonction de lvl.
     */
    public AdversairePaysan(String nom, int lvl){
        int totalStats = lvl + 3;
        this.nom = nom;
        this.lvl = lvl;
        Random rand = new Random();
        
        int stat1 = rand.nextInt(totalStats + 1);
        int stat2 = rand.nextInt(totalStats + 1);
        int pvMaxRandom = rand.nextInt(lvl*2 + 1 ) + 20;
        
        int min = Math.min(stat1, stat2);
        int max = Math.max(stat1, stat2);

        this.force = min;
        this.intelligence = max - min;
        this.agilite = totalStats - max;
        this.pvMax = pvMaxRandom;
        this.pv = pvMax;
    }


    /**
     * Attaque au corps à corps : inflige des dégâts basés sur la force.
     *
     * @param cible La cible (instance de {@link Personnage}) subissant les dégâts en mêlée.
     */
    @Override
    public void attaqueMelee(Personnage cible) {
        cible.recoitDegatsMelee(force);
    }

    /**
     * Attaque à distance : inflige des dégâts basés sur l’intelligence.
     *
     * @param cible La cible (instance de {@link Personnage}) subissant les dégâts à distance.
     */
    @Override
    public void attaqueDistance(Personnage cible) {
        cible.recoitDegatsDistance(intelligence); 
    }

    /**
     * Définit l’état de défense contre une attaque en mêlée.
     * Si true, les dégâts reçus en mêlée seront réduits par l’agilité.
     */
    @Override
    public void paradeMelee() {
        this.onDefenseMelee = true; 
    }

    /**
     * Définit l’état de défense contre une attaque à distance.
     * Si true, les dégâts reçus à distance seront réduits par l’agilité.
     */
    @Override
    public void paradeDistance() {
        this.onDefenseDistance = true; 
    }

    /**
     * Présente l’adversaire Paysan en affichant son nom et la mention « le Paysan ».
     */
    @Override
    public void sePresente() {
        System.out.println(String.format("\nJe suis %s le Paysan", nom));  
    }

    /**
     * Retourne la liste des actions disponibles pour un Paysan :
     * <ul>
     *   <li>ATTAQUE_MELEE</li>
     *   <li>ATTAQUE_DISTANCE</li>
     *   <li>PARADE_MELEE</li>
     *   <li>PARADE_DISTANCE</li>
     * </ul>
     *
     * @return Liste non modifiable des {@link Action} disponibles.
     */
    @Override
    public List<Action> getActionsDisponibles() {
    return List.of(
        Action.ATTAQUE_MELEE,
        Action.ATTAQUE_DISTANCE,
        Action.PARADE_MELEE,
        Action.PARADE_DISTANCE
        );
    }

    public static void main(String[] args) {
        AdversairePaysan adv1 = new AdversairePaysan("Test1", 0);
        AdversairePaysan adv2 = new AdversairePaysan("Test2", 10);

        adv1.afficherStats();
        adv2.afficherStats();
    }
}