package src.joueurs;

import java.util.HashMap;

import src.Joueur;
import src.Personnage;
import src.interfacesPersonnages.Magicien;
import src.Item;

public class JoueurMagicien extends Joueur implements Magicien {
    protected int money;
    protected HashMap<String, Item> inventaire = new HashMap<String, Item>();
    
    
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

        //faire le lien entre les attaques d'un perso et les défences ou non de l'autre.

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
