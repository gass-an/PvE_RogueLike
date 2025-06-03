package joueurs;

import java.util.List;

import game.Action;
import interfacesPersonnages.Barbare;
import model.Personnage;

/**
 * Classe représentant un joueur de type Barbare.
 * Hérite de {@link Joueur} et implémente l’interface {@link Barbare}.
 * Le joueur Barbare ne peut attaquer qu’en mêlée et parer (mêlée ou distance).
 * Lors de l’évolution, la force de base est augmentée de l’intelligence précédente, 
 * puis l’intelligence est remise à zéro.
 */
public class JoueurBarbare extends Joueur implements Barbare {

    /**
     * Constructeur utilisé lors d’une évolution.
     *
     * @param nom          Nom du joueur.
     * @param force        Points de force (incluant l’intelligence auparavant).
     * @param intelligence Points d’intelligence initiaux (toujours 0 après conversion).
     * @param agilite      Points d’agilité initiaux.
     * @param pv           PV actuels du joueur.
     * @param pvMax        PV maximum.
     * @param lvl          Niveau actuel.
     * @param money        Somme d’argent actuelle.
     */
    public JoueurBarbare(
        String nom, int force, int intelligence, int agilite,
        int pv, int pvMax, int lvl, int money) {
            this.nom=nom;
            this.force=force + intelligence;
            this.intelligence=0;
            this.agilite=agilite;
            this.pvMax=pvMax;
            this.pv=pv;
            this.lvl=lvl;
            this.money=money;
        }

    /**
     * Attaque au corps à corps : inflige des dégâts basés sur la force + 1.
     *
     * @param cible La cible ({@link Personnage}) subissant l’attaque en mêlée.
     */
    @Override
    public void attaqueMelee(Personnage cible) {
        cible.recoitDegatsDistance(getForce() + 1);            
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
     * Retourne la liste des actions disponibles pour un Barbare (joueur) :
     * <ul>
     *   <li>ATTAQUE_MELEE</li>
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
        Action.PARADE_MELEE,
        Action.PARADE_DISTANCE
        );
    }
}
