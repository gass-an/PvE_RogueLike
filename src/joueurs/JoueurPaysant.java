package joueurs;

import java.util.HashMap;

import Joueur;
import Personnage;
import src.interfacesPersonnages.Paysant;
import src.Item;

public class JoueurPaysant extends Joueur implements Paysant {
    private String nom;
    private int force, intelligence, agilite;
    private int pv, pvMax, lvl, money;
    private HashMap<String, Item> inventaire = new HashMap<String, Item>();
    
    // Paramètres de base pour un nouveau Joueur.
    public JoueurPaysant(String nom) {
        this.nom = nom;
        this.force = 1;
        this.intelligence = 1;
        this.agilite = 1;
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

        //faire le lien entre les attaques d'un perso et les défences ou non de l'autre.

        @Override
        public void attaqueMelee(Personnage cible) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void attaqueDistance(Personnage cible) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void paradeMelee(int degatsMelee) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void paradeDistance(int degatsDistance) {
            // TODO Auto-generated method stub
            
        }
}
