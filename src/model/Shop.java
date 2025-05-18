package model;

import java.util.HashMap;

import model.Item;

public class Shop{
	private HashMap<String, Item> shop = new HashMap<String, Item>();
	
	public void genererItem() {
		int nbItem = 3;
		for(int i=0; i<nbItem; i++) {
			String type = "";
			String nom = "" + (i+1);
			int prix = 5;
			int force = 1;
			int agilite = 1;
			int intelligence = 1;
			int niv = 1;
			
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
