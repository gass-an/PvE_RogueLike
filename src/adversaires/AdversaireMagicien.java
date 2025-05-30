package adversaires;

import java.util.List;
import java.util.Random;

import game.Action;
import interfacesPersonnages.Magicien;
import model.Personnage;

public class AdversaireMagicien extends Adversaire implements Magicien{
    public AdversaireMagicien(String nom, int lvl){
        int totalStats = lvl + 3;
        this.nom = nom;
        this.lvl = lvl;
        Random rand = new Random();
        
        int stat = rand.nextInt(totalStats) + 1;
        int pvMaxRandom = rand.nextInt(lvl*2 + 1) + 20;

        this.force = 0;
        this.intelligence = stat;
        this.agilite = totalStats - stat;
        this.pvMax = pvMaxRandom;
        this.pv = pvMax;
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
    public void sePresente() {
        System.out.println(String.format("\nJe suis %s le Magicien", nom));  
    }

    public static void main(String[] args) {
        AdversaireMagicien adv1 = new AdversaireMagicien("Mage 1", 0);
        AdversaireMagicien adv2 = new AdversaireMagicien("Mage 2", 10);

        adv1.afficherStats();
        adv2.afficherStats();
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
