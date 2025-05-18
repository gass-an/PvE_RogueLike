package adversaires;

import java.util.Random;

import interfacesPersonnages.Magicien;
import model.Personnage;

public class AdversaireMagicien extends Personnage implements Magicien {
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

    public void afficherStats() {
        System.out.println("\nNom: " + nom);
        System.out.println("PV: " + pv);
        System.out.println("Level: " + lvl);
        System.out.println("Force: " + force);
        System.out.println("Intelligence: " + intelligence);
        System.out.println("Agilité: " + agilite);
    }

    public static void main(String[] args) {
        AdversaireMagicien adv1 = new AdversaireMagicien("Mage 1", 0);
        AdversaireMagicien adv2 = new AdversaireMagicien("Mage 2", 10);

        adv1.afficherStats();
        adv2.afficherStats();
    }
}
