package joueurs;

import java.util.List;

import game.Action;
import interfacesPersonnages.Paysan;
import model.Personnage;

/**
 * Classe représentant un joueur de type Paysan.
 * Hérite de {@link Joueur} et implémente l’interface {@link Paysan}.
 * Le joueur Paysan a :
 * <ul>
 *   <li>Des PV max de base égal à 30</li>
 *   <li>Un niveau de départ à 0</li>
 *   <li>Un montant d’argent de départ égal à 20</li>
 * </ul>
 * Il peut attaquer à la fois en mêlée et à distance, et parer.
 */
public class JoueurPaysan extends Joueur implements Paysan {
    
    /**
     * Constructeur principal.
     *
     * @param nom          Nom du joueur.
     * @param force        Points de force initiaux.
     * @param intelligence Points d’intelligence initiaux.
     * @param agilite      Points d’agilité initiaux.
     */
    public JoueurPaysan(String nom, int force, int intelligence, int agilite) {
        this.nom = nom;
        this.force = force;
        this.intelligence = intelligence;
        this.agilite = agilite;
        this.pvMax = 30;
        this.pv = pvMax;
        this.lvl = 0;
        this.money = 20;
    }
    
    /**
     * Constructeur complet (utilisé pour transférer d'état lors d’évolution).
     *
     * @param nom          Nom du joueur.
     * @param force        Points de force (après évolution/bonus).
     * @param intelligence Points d’intelligence (après évolution/bonus).
     * @param agilite      Points d’agilité (après évolution/bonus).
     * @param pv           PV actuels.
     * @param pvMax        PV maximum.
     * @param lvl          Niveau actuel.
     * @param money        Somme d’argent actuelle.
     */
    public JoueurPaysan(
        String nom, int force, int intelligence, int agilite,
        int pv, int pvMax, int lvl, int money) {
            this.nom=nom;
            this.force=force;
            this.intelligence=intelligence;
            this.agilite=agilite;
            this.pvMax=pvMax;
            this.pv=pv;
            this.lvl=lvl;
            this.money=money;
    }

    /**
     * Attaque en mêlée : inflige des dégâts physiques basés sur la force.
     *
     * @param cible La cible ({@link Personnage}) subissant l’attaque en mêlée.
     */
    @Override
    public void attaqueMelee(Personnage cible) {
        cible.recoitDegatsMelee(getForce());
    }

    /**
     * Attaque à distance : inflige des dégâts basés sur l’intelligence.
     *
     * @param cible La cible ({@link Personnage}) subissant l’attaque à distance.
     */
    @Override
    public void attaqueDistance(Personnage cible) {
        cible.recoitDegatsDistance(getIntelligence()); 
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
     * Retourne la liste des actions disponibles pour un Paysan (joueur) :
     * <ul>
     *   <li>ATTAQUE_MELEE</li>
     *   <li>ATTAQUE_DISTANCE</li>
     *   <li>PARADE_MELEE</li>
     *   <li>PARADE_DISTANCE</li>
     * </ul>
     *
     * @return Liste non modifiable des {@link game.Action} disponibles.
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
}