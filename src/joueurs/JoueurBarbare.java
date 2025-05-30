package joueurs;

import java.util.List;

import game.Action;
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
        cible.recoitDegatsDistance(getForce() + 1);            
    }

    @Override
    public void paradeMelee() {
        this.onDefenseMelee = true;   
    }

    @Override
    public void paradeDistance() {
        this.onDefenseDistance = true;   
    }

    @Override
    public List<Action> getActionsDisponibles() {
    return List.of(
        Action.ATTAQUE_MELEE,
        Action.PARADE_MELEE,
        Action.PARADE_DISTANCE
        );
    }
}
