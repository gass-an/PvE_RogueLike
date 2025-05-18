package joueurs;

import interfacesPersonnages.Barbare;
import model.Personnage;

public class JoueurBarbare extends Joueur implements Barbare {

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

    @Override
    public void attaqueMelee(Personnage cible) {
        cible.recoitDegatsDistance(force);            
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
