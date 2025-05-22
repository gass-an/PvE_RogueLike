package joueurs;

import interfacesPersonnages.Paysant;
import model.Personnage;

public class JoueurPaysant extends Joueur implements Paysant {
    
    // Paramètres de base pour un nouveau Joueur.
    public JoueurPaysant(String nom, int force, int intelligence, int agilite) {
        this.nom = nom;
        this.force = force;
        this.intelligence = intelligence;
        this.agilite = agilite;
        this.pvMax = 30;
        this.pv = pvMax;
        this.lvl = 0;
        this.money = 20;
    }
    
    public JoueurPaysant(
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

    @Override
    public void attaqueMelee(Personnage cible) {
        cible.recoitDegatsMelee(force);
    }

    @Override
    public void attaqueDistance(Personnage cible) {
        cible.recoitDegatsDistance(intelligence); 
    }

    @Override
    public void paradeMelee() {
        this.onDefenseMelee = true; 
    }

    @Override
    public void paradeDistance() {
        this.onDefenseDistance = true; 
    }
}
