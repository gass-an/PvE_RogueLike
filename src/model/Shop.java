package model;

import java.util.HashMap;
import java.util.Random;

/**
 * Classe représentant la boutique (shop) du jeu.
 * Permet de générer aléatoirement un ensemble de 3 {@link Item} et de les proposer à l’achat.
 * Les items sont stockés dans une {@code HashMap<String, Item>} indexée par leur nom.
 */
public class Shop{
	private HashMap<Integer, Item> shop = new HashMap<Integer, Item>();
	private Random random = new Random();
	
	/**
     * Choisit aléatoirement un {@link TypeItem}, parmi toutes les valeurs disponibles.
     *
     * @return Un TypeItem tiré aléatoirement.
     */
	public TypeItem getRandomType() {
		TypeItem[] values = TypeItem.values();
		return values[random.nextInt(values.length)];
	}

	/**
     * Génère 3 items de manière aléatoire :
     * <ul>
     *   <li>Type aléatoire (via getRandomType)</li>
     *   <li>Nom sous forme de chaîne « typeItem i » où i ∈ {1,2,3}</li>
     *   <li>Prix aléatoire entre 5 et 14</li>
	 *   <li>La valeure de chaque bonus (force, agilité, intelligence) est aléatoire</li>
     *   <li>La somme des bonus est égale à 3</li>
	 *   <li>Fixe le numéro de l'item à « i » où i ∈ {1,2,3}</li>
     * </ul>
     * Les objets créés sont insérés dans la {@code HashMap} interne.
     */
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

	/**
     * Affiche dans la console tous les {@link Item} présents dans le shop, en utilisant
     * la méthode {@link Item#afficherItem()} pour chaque objet.
     */
	public void afficherShop() {
		System.out.println("-----------------Bienvenue dans le shop !-----------------");
		for(Item item : shop.values()) {
			item.afficherItem();
		}
	}

	/**
     * Récupère un {@link Item} à partir de son numéro (1, 2 ou 3).
     * Les clés internes sont les chaînes "1", "2" et "3".
     *
     * @param number Numéro de l’item (1-based).
     * @return L’instance {@link Item} correspondante, ou null si introuvable.
     */
	public Item getItemByNumber(int number) {
    	return shop.get(number);
	}

	/**
     * Retourne l'instance de {@link Shop} actuelle.
     *
     * 
     * @return L’instance {@link Shop} utilisée.
     */
	public HashMap<Integer, Item> getShop() {
		return shop;
	}
}