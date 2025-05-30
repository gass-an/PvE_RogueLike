package model;

import java.util.List;

import game.Action;

public abstract class Personnage {    
    protected String nom;
    protected int force, intelligence, agilite;
    protected int pv, pvMax, lvl;
    protected boolean onDefenseDistance = false; 
    protected boolean onDefenseMelee = false;

    abstract public List<Action> getActionsDisponibles();
    
    public String getNom() {
        return nom;
    }

    public int getForce() {
        return force;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgilite() {
        return agilite;
    }

    public int getPv() {
        return pv;
    }

    public int getPvMax() {
        return pvMax;
    }

    public int getLvl() {
        return lvl;
    }

    public void addLvl() {
        this.lvl += 1;
    }

    public void addForce(int force) {
        this.force += force;
    }
   
    public void removeForce(int force) {
        this.force -= force;
        if (this.force < 0) {
            this.force = 0;
        }
    }

    public void addIntelligence(int intelligence) {
        this.intelligence += intelligence;
    }

    public void removeIntelligence(int force) {
        this.force -= force;
        if (this.force < 0) {
            this.force = 0;
        }
    }

    public void addAgilite(int agilite) {
        this.agilite += agilite;
    } 

    public void removeAgilite (int agilite) {
        this.agilite -= agilite;
        if (this.agilite < 0) {
            this.agilite = 0;
        }
    }

    public void addPVMax (int pvMax) {
        this.pvMax += pvMax;
    }

    public void removePVMax (int pvMax) {
        this.pvMax -= pvMax;
        // PvMax supérieur à 1 
        if (this.pvMax < 1) {
            this.pvMax = 1;
        }
    }

    public void addPV(int pv) {
        this.pv += pv;
        if (this.pv > pvMax) {
            this.pv = pvMax;
        }
    }
    
    protected void removePV (int pv) {
        this.pv -= pv;
        if (this.pv < 0) {
            this.pv = 0;
        }
    }

    public void recoitDegatsDistance(int degatsDistance){
        if (onDefenseDistance){
            degatsDistance = degatsDistance - getAgilite();
        }
        if (degatsDistance < 0) {degatsDistance = 0;}
        this.removePV(degatsDistance);
    }

    public void recoitDegatsMelee(int degatsMelee){
        if (onDefenseMelee){
            degatsMelee = degatsMelee - getAgilite();
        }
        if (degatsMelee < 0) {degatsMelee = 0;}
        this.removePV(degatsMelee);
    }

    // à utiliser au début de chaque tour.
    public void resetDefense(){
        this.onDefenseDistance = false;
        this.onDefenseMelee = false;
    }

    public void afficherStats() {
        System.out.println("\nNom: " + nom);
        System.out.println("PV: " + pv);
        System.out.println("Level: " + lvl);
        System.out.println("Force: " + getForce());
        System.out.println("Intelligence: " + getIntelligence());
        System.out.println("Agilité: " + getAgilite());
    }
    
}