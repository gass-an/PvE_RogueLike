package adversaires;

import java.util.Random;

import interfacesPersonnages.Barbare;
import model.Personnage;

public class AdversaireBarbare extends Personnage implements Barbare {
    public AdversaireBarbare(String nom, int lvl){
        int totalStats = lvl + 3;
        this.nom = nom;
        this.lvl = lvl;
        Random rand = new Random();
        
        int stat = rand.nextInt(totalStats) + 1;
        int pvMaxRandom = rand.nextInt(lvl*2 + 1) + 20;

        this.force = stat;
        this.intelligence = 0;
        this.agilite = totalStats - stat;
        this.pvMax = pvMaxRandom;
        this.pv = pvMax;
    }

    @Override
    public void attaqueMelee(Personnage cible) {
        cible.recoitDegatsMelee(force);
    }

    @Override
    public void paradeMelee() {
        this.onDefenseMelee = true; 
    }

    @Override
    public void paradeDistance() {
        this.onDefenseDistance = true; 
    }

    public static void main(String[] args) {
        AdversaireBarbare adv1 = new AdversaireBarbare("Barbare 1", 0);
        AdversaireBarbare adv2 = new AdversaireBarbare("Barbare 2", 10);

        adv1.afficherStats();
        adv2.afficherStats();
    }
}
