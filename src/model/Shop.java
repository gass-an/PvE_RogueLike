package model;

import java.util.HashMap;
import java.util.Random;

public class Shop{
	private HashMap<Integer, Item> shop = new HashMap<Integer, Item>();
	private Random random = new Random();
	
	public TypeItem getRandomType() {
		TypeItem[] values = TypeItem.values();
		return values[random.nextInt(values.length)];
	}

	public void genererItem() {
		int nbItem = 3;
		Random rand = new Random();
		int nbStats = 3;
		for(int i=0; i<nbItem; i++) {
        
			int stat1 = rand.nextInt(nbStats + 1);
			int stat2 = rand.nextInt(nbStats + 1);
			int randomPrix = rand.nextInt(10) + 5;

			int min = Math.min(stat1, stat2);
			int max = Math.max(stat1, stat2);


			TypeItem type = getRandomType();
			String nom = type.toString().toLowerCase() + " " + (i+1);
			int prix = randomPrix;
			int force = min;
			int agilite = max - min;
			int intelligence = nbStats - max;
			int number = i+1;

			Item item = new Item(number, type, nom, prix, force, agilite, intelligence);
			shop.put(number, item);
		}
	}

	public void afficherShop() {
		System.out.println("-----------------Bienvenue dans le shop !-----------------");
		for(Item item : shop.values()) {
			item.afficherItem();
		}
	}

	public Item getItemByNumber(int number) {
    	return shop.get(number);
	}

	public HashMap<Integer, Item> getShop() {
		return shop;
	}
}