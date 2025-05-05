package src;

public abstract class Personnage {
    private String nom;
    private int force, intelligence, agilite;
    private int pv, pvMax, lvl;

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

    public void addForce(int force) {
        this.force += force;
    }

    public void addIntelligence(int intelligence) {
        this.intelligence += intelligence;
    }

    public void addAgilite(int agilite) {
        this.agilite += agilite;
    } 

    public void addPV(int pv) {
        this.pv += pv;
        if (this.pv > pvMax) {
            this.pv = pvMax;
        }
    }

    public void addPVMax (int pvMax) {
        this.pvMax += pvMax;
    }

    public void addLvl() {
        this.lvl += 1;
    }

    public void removeForce(int force) {
        this.force -= force;
        if (this.force < 0) {
            this.force = 0;
        }
    }

    public void removeIntelligence(int force) {
        this.force -= force;
        if (this.force < 0) {
            this.force = 0;
        }
    }

    public void removeAgilite (int agilite) {
        this.agilite -= agilite;
        if (this.agilite < 0) {
            this.agilite = 0;
        }
    }

    public void removePV (int pv) {
        this.pv -= pv;
        if (this.pv < 0) {
            this.pv = 0;
        }
    }

    public void removePVMax (int pvMax) {
        this.pvMax -= pvMax;
        // PvMax supérieur à 1 
        if (this.pvMax < 1) {
            this.pvMax = 1;
        }
    }
}