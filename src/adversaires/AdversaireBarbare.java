package adversaires;

import java.util.List;
import java.util.Random;

import game.Action;
import interfacesPersonnages.Barbare;
import model.Personnage;

/**
 * Classe représentant un adversaire de type Barbare.
 * Hérite de {@link Adversaire} et implémente l’interface {@link Barbare}.
 * Le Barbare peut effectuer des attaques au corps à corps et des parades.
 */
public class AdversaireBarbare extends Adversaire implements Barbare {
    
    /**
     * Constructeur principal.
     *
     * @param nom Nom personnalisé de l’adversaire Barbare.
     * @param lvl Niveau (niveau de jeu) servant à générer aléatoirement ses statistiques.
     *            La somme de la force et de l’agilité est égale à lvl + 3. Les points de vie
     *            maximum sont calculés aléatoirement en fonction de lvl.
     */
    public AdversaireBarbare(String nom, int lvl){
        int totalStats = lvl + 3;
        this.nom = nom;
        this.lvl = lvl;
        Random rand = new Random();
        
        int stat = rand.nextInt(totalStats) + 1;
        int pvMaxRandom = rand.nextInt(lvl*2 + 1) + 20;

        this.force = stat;
        this.intelligence = 0;
        this.agilite = totalStats - stat;
        this.pvMax = pvMaxRandom;
        this.pv = pvMax;
    }

    /**
     * Attaque de type mêlée : inflige des dégâts physiques basés sur la force.
     *
     * @param cible La cible (instance de {@link Personnage}) qui subit les dégâts en mêlée.
     */
    @Override
    public void attaqueMelee(Personnage cible) {
        cible.recoitDegatsMelee(force);
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
     * Présente l’adversaire Barbare en affichant son nom et la mention « le Barbare ».
     */
    @Override
    public void sePresente() {
        System.out.println(String.format("\nJe suis %s le Barbare", nom));  
    }
    
    /**
     * Retourne la liste des actions disponibles pour un Barbare :
     * <ul>
     *     <li>ATTAQUE_MELEE</li>
     *     <li>PARADE_MELEE</li>
     *     <li>PARADE_DISTANCE</li>
     * </ul>
     *
     * @return Liste non modifiable des {@link Action} disponibles.
     */
    @Override
    public List<Action> getActionsDisponibles() {
    return List.of(
        Action.ATTAQUE_MELEE,
        Action.PARADE_MELEE,
        Action.PARADE_DISTANCE
        );
    }

    public static void main(String[] args) {
        AdversaireBarbare adv1 = new AdversaireBarbare("Barbare 1", 0);
        AdversaireBarbare adv2 = new AdversaireBarbare("Barbare 2", 10);

        adv1.afficherStats();
        adv2.afficherStats();
    }
}