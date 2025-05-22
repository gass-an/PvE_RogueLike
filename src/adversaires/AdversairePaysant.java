package adversaires;

import java.util.List;
import java.util.Random;

import interfacesPersonnages.Paysant;
import model.Personnage;
import Game.Action;

public class AdversairePaysant extends Adversaire implements Paysant {
    
    public AdversairePaysant(String nom, int lvl){
        int totalStats = lvl + 3;
        this.nom = nom;
        this.lvl = lvl;
        Random rand = new Random();
        
        int stat1 = rand.nextInt(totalStats + 1);
        int stat2 = rand.nextInt(totalStats + 1);
        int pvMaxRandom = rand.nextInt(lvl*2 + 1 ) + 20;
        
        int min = Math.min(stat1, stat2);
        int max = Math.max(stat1, stat2);

        this.force = min;
        this.intelligence = max - min;
        this.agilite = totalStats - max;
        this.pvMax = pvMaxRandom;
        this.pv = pvMax;
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

    @Override
    public void sePresente() {
        System.out.println(String.format("\nJe suis %s le Paysant", nom));  
    }

    public static void main(String[] args) {
        AdversairePaysant adv1 = new AdversairePaysant("Test1", 0);
        AdversairePaysant adv2 = new AdversairePaysant("Test2", 10);

        adv1.afficherStats();
        adv2.afficherStats();
    }

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
