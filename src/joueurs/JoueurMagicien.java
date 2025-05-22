package joueurs;

import java.util.List;

import interfacesPersonnages.Magicien;
import model.Personnage;
import Game.Action;

public class JoueurMagicien extends Joueur implements Magicien {
    
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

    @Override
    public List<Action> getActionsDisponibles() {
    return List.of(
        Action.ATTAQUE_DISTANCE,
        Action.PARADE_MELEE,
        Action.PARADE_DISTANCE
        );
    }
}
