package model;

import java.util.HashMap;
import java.util.Random;

import joueurs.Joueur;
import model.Item;

public class Shop{
	private HashMap<String, Item> shop = new HashMap<String, Item>();
	private Random random = new Random();
	private Personnage personnage;
	
	public TypeItem getRandomType() {
		TypeItem[] values = TypeItem.values();
		return values[random.nextInt(values.length)];
	}

	public void genererItem() {
		int nbItem = 3;
		for(int i=0; i<nbItem; i++) {
			TypeItem type = getRandomType();
			String nom = "" + (i+1);
			int prix = 5;
			int force = 1;
			int agilite = 1;
			int intelligence = 1;
			int niv = Joueur.instance.getLvl();
      
			Item item = new Item(type, nom, prix, force, agilite, intelligence, niv);
			shop.put(nom, item);
		}
	}

	public void afficherShop() {
		System.out.println("-----------------Bienvenue dans le shop !-----------------");
		for(Item item : shop.values()) {
			item.afficherItem();
		}
	}

}