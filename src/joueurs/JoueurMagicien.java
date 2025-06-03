package joueurs;

import java.util.List;

import game.Action;
import interfacesPersonnages.Magicien;
import model.Personnage;

/**
 * Classe représentant un joueur de type Magicien.
 * Hérite de {@link Joueur} et implémente l’interface {@link Magicien}.
 * Le joueur Magicien ne peut attaquer qu’à distance et parer (mêlée ou distance).
 * Lors de l’évolution, l’intelligence de base est augmentée de la force précédente, 
 * puis la force est remise à zéro.
 */
public class JoueurMagicien extends Joueur implements Magicien {
    
    /**
     * Constructeur utilisé lors d’une évolution.
     *
     * @param nom          Nom du joueur.
     * @param force        Points de force initiaux (toujours 0 pour un Magicien).
     * @param intelligence Points d’intelligence initiaux (incluant l’intelligence précédente).
     * @param agilite      Points d’agilité initiaux.
     * @param pv           PV actuels du joueur.
     * @param pvMax        PV maximum.
     * @param lvl          Niveau actuel.
     * @param money        Somme d’argent actuelle.
     */
    public JoueurMagicien(
        String nom, int force, int intelligence, int agilite,
        int pv, int pvMax, int lvl, int money) {
            this.nom=nom;
            this.force=0;
            this.intelligence=intelligence + force;
            this.agilite=agilite;
            this.pvMax=pvMax;
            this.pv=pv;
            this.lvl=lvl;
            this.money=money;
    }

    /**
     * Attaque à distance : inflige des dégâts basés sur l’intelligence + 1.
     *
     * @param cible La cible ({@link Personnage}) subissant l’attaque à distance.
     */
    @Override
    public void attaqueDistance(Personnage cible) {
        cible.recoitDegatsDistance(getIntelligence() + 1);
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
     * Retourne la liste des actions disponibles pour un Magicien (joueur) :
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
}
