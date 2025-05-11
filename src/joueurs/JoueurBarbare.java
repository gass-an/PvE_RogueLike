package src.joueurs;

import java.util.HashMap;

import src.Joueur;
import src.Personnage;
import src.interfacesPersonnages.Barbare;
import src.Item;

public class JoueurBarbare extends Joueur implements Barbare {
    protected int money;
    protected HashMap<String, Item> inventaire = new HashMap<String, Item>();
    
    
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

        //faire le lien entre les attaques d'un perso et les défences ou non de l'autre.

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
