package adversaires;

import java.util.List;
import java.util.Random;

import game.Action;
import interfacesPersonnages.Magicien;
import model.Personnage;

/**
 * Classe représentant un adversaire de type Magicien.
 * Hérite de {@link Adversaire} et implémente l’interface {@link Magicien}.
 * Le Magicien peut attaquer à distance et parer.
 */
public class AdversaireMagicien extends Adversaire implements Magicien{
    
    /**
     * Constructeur principal.
     *
     * @param nom Nom personnalisé du Magicien.
     * @param lvl Niveau (niveau de jeu) influençant la génération des compétences.
     *            L’intelligence varie aléatoirement entre 1 et lvl+3. L’agilité est la valeur
     *            restante. La force est fixée à 0. Les PV max sont tirés aléatoirement.
     */
    public AdversaireMagicien(String nom, int lvl){
        int totalStats = lvl + 3;
        this.nom = nom;
        this.lvl = lvl;
        Random rand = new Random();
        
        int stat = rand.nextInt(totalStats) + 1;
        int pvMaxRandom = rand.nextInt(lvl*2 + 1) + 20;

        this.force = 0;
        this.intelligence = stat;
        this.agilite = totalStats - stat;
        this.pvMax = pvMaxRandom;
        this.pv = pvMax;
    }

    /**
     * Attaque à distance : inflige des dégâts basés sur l’intelligence.
     *
     * @param cible La cible (instance de {@link Personnage}) subissant l’attaque à distance.
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
     * Présente l’adversaire Magicien en affichant son nom et la mention « le Magicien ».
     */
    @Override
    public void sePresente() {
        System.out.println(String.format("\nJe suis %s le Magicien", nom));  
    }

    /**
     * Retourne la liste des actions disponibles pour un Magicien :
     * <ul>
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
        Action.ATTAQUE_DISTANCE,
        Action.PARADE_MELEE,
        Action.PARADE_DISTANCE
        );
    }

    public static void main(String[] args) {
        AdversaireMagicien adv1 = new AdversaireMagicien("Mage 1", 0);
        AdversaireMagicien adv2 = new AdversaireMagicien("Mage 2", 10);

        adv1.afficherStats();
        adv2.afficherStats();
    }
}