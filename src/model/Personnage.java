package model;

import java.util.List;

import game.Action;

/**
 * Classe abstraite représentant un personnage générique dans le jeu.
 * Contient les attributs de base (nom, force, intelligence, agilité, PV, niveau, etc.),
 * ainsi que les méthodes de gestion des PV, des dégâts et de l’affichage des statistiques.
 *
 * Chaque sous-classe doit implémenter la méthode {@link #getActionsDisponibles()}.
 */
public abstract class Personnage {    
    protected String nom;
    protected int force, intelligence, agilite;
    protected int pv, pvMax, lvl;
    protected boolean onDefenseDistance = false; 
    protected boolean onDefenseMelee = false;

    /**
     * Retourne la liste des actions disponibles pour ce personnage.
     * Doit être implémentée par chaque sous-classe concrète (JoueurPaysan,
     * AdversaireBarbare, etc.).
     *
     * @return {@link java.util.List} de {@link game.Action}.
     */
    abstract public List<Action> getActionsDisponibles();
    
    /**
     * Retourne le nom du personnage.
     *
     * @return Nom du personnage.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la force courante du personnage (avant bonus d’équipement).
     *
     * @return Force de base.
     */
    public int getForce() {
        return force;
    }

    /**
     * Retourne l’intelligence courante du personnage (avant bonus d’équipement).
     *
     * @return Intelligence de base.
     */
    public int getIntelligence() {
        return intelligence;
    }

    /**
     * Retourne l’agilité courante du personnage (avant bonus d’équipement).
     *
     * @return Agilité de base.
     */
    public int getAgilite() {
        return agilite;
    }

    /**
     * Retourne les points de vie actuels.
     *
     * @return PV courants.
     */
    public int getPv() {
        return pv;
    }

    /**
     * Retourne les points de vie maximum.
     *
     * @return PV maximum.
     */
    public int getPvMax() {
        return pvMax;
    }

    /**
     * Retourne le niveau du personnage.
     *
     * @return Niveau courant.
     */
    public int getLvl() {
        return lvl;
    }

    /**
     * Incrémente le niveau du personnage de 1.
     */
    public void addLvl() {
        this.lvl += 1;
    }

    /**
     * Ajoute un montant à la force de base (sans compter l’équipement).
     *
     * @param force Points de force à ajouter.
     */
    public void addForce(int force) {
        this.force += force;
    }
   
    /**
     * Retire un montant à la force de base.
     * Si le résultat devient négatif, la force est ramenée à 0.
     *
     * @param force Points de force à retirer.
     */
    public void removeForce(int force) {
        this.force -= force;
        if (this.force < 0) {
            this.force = 0;
        }
    }

    /**
     * Ajoute un montant à l’intelligence de base.
     *
     * @param intelligence Points d’intelligence à ajouter.
     */
    public void addIntelligence(int intelligence) {
        this.intelligence += intelligence;
    }

    /**
     * Retire un montant à l’intelligence de base.
     * Si le résultat devient négatif, l’intelligence est ramenée à 0.
     *
     * @param intelligence Points d’intelligence à retirer.
     */
    public void removeIntelligence(int force) {
        this.force -= force;
        if (this.force < 0) {
            this.force = 0;
        }
    }

    /**
     * Ajoute un montant à l’agilité de base.
     *
     * @param agilite Points d’agilité à ajouter.
     */
    public void addAgilite(int agilite) {
        this.agilite += agilite;
    } 

    /**
     * Retire un montant à l’agilité de base.
     * Si le résultat devient négatif, l’agilité est ramenée à 0.
     *
     * @param agilite Points d’agilité à retirer.
     */
    public void removeAgilite (int agilite) {
        this.agilite -= agilite;
        if (this.agilite < 0) {
            this.agilite = 0;
        }
    }

    /**
     * Ajoute un montant aux points de vie maximum.
     *
     * @param pvMax Points de vie maximum à ajouter.
     */
    public void addPVMax (int pvMax) {
        this.pvMax += pvMax;
    }

    /**
     * Retire un montant aux points de vie maximum.
     * Si le résultat devient inférieur à 1, le PV max est fixé à 1.
     *
     * @param pvMax Points de vie maximum à retirer.
     */
    public void removePVMax (int pvMax) {
        this.pvMax -= pvMax;
        // PvMax supérieur à 1 
        if (this.pvMax < 1) {
            this.pvMax = 1;
        }
    }

    /**
     * Ajoute un montant aux points de vie actuels.
     * Si le résultat dépasse pvMax, les PV sont ramenés à pvMax.
     *
     * @param pv Points de vie à ajouter.
     */
    public void addPV(int pv) {
        this.pv += pv;
        if (this.pv > pvMax) {
            this.pv = pvMax;
        }
    }
    
    /**
     * Retire un montant aux points de vie actuels.
     * Si le résultat devient inférieur à 0, les PV sont ramenés à 0.
     *
     * @param pv Points de vie à retirer.
     */
    protected void removePV (int pv) {
        this.pv -= pv;
        if (this.pv < 0) {
            this.pv = 0;
        }
    }

    /**
     * Applique des dégâts à distance après prise en compte de la parade éventuelle.
     * Si {@code onDefenseDistance} est true, on retire l’agilité du nombre de dégâts.
     *
     * @param degatsDistance Montant brut de dégâts à distance.
     */
    public void recoitDegatsDistance(int degatsDistance){
        if (onDefenseDistance){
            degatsDistance = degatsDistance - getAgilite();
        }
        if (degatsDistance < 0) {degatsDistance = 0;}
        this.removePV(degatsDistance);
    }

    /**
     * Applique des dégâts en mêlée après prise en compte de la parade éventuelle.
     * Si {@code onDefenseMelee} est true, on retire l’agilité du nombre de dégâts.
     *
     * @param degatsMelee Montant brut de dégâts en mêlée.
     */
    public void recoitDegatsMelee(int degatsMelee){
        if (onDefenseMelee){
            degatsMelee = degatsMelee - getAgilite();
        }
        if (degatsMelee < 0) {degatsMelee = 0;}
        this.removePV(degatsMelee);
    }

    /**
     * Réinitialise les états de parade (distance et mêlée) au début de chaque tour.
     */
    public void resetDefense(){
        this.onDefenseDistance = false;
        this.onDefenseMelee = false;
    }

    /**
     * Affiche dans la console les statistiques du personnage :
     * <pre>
     * Nom: X
     * PV: Y
     * Level: Z
     * Force: A
     * Intelligence: B
     * Agilité: C
     * </pre>
     */
    public void afficherStats() {
        System.out.println("\nNom: " + nom);
        System.out.println("PV: " + pv);
        System.out.println("Level: " + lvl);
        System.out.println("Force: " + getForce());
        System.out.println("Intelligence: " + getIntelligence());
        System.out.println("Agilité: " + getAgilite());
    }
}