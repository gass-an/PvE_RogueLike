package model;

/**
 * Classe représentant un objet (Item) dans le jeu.
 * Chaque {@link Item} possède :
 * <ul>
 *   <li>Un {@link TypeItem} (HELMT, ARMOR, BOOT, WEAPON)</li>
 *   <li>Un nom unique</li>
 *   <li>Un prix d’achat</li>
 *   <li>Des bonus de statistiques (force, agilité, intelligence)</li>
 *   <li>Un numéro (niv)</li>
 * </ul>
 */
public class Item {
	private TypeItem type;
	private String nom;
	private int prix, number;
	private int force, agilite, intelligence;
	 
	/**
     * Constructeur principal.
     *
     * @param number       Numéro de l'item.
     * @param type         Type de l’item.
     * @param nom          Nom unique.
     * @param prix         Coût d’achat en pièces d’or.
     * @param force        Bonus de force apporté.
     * @param agilite      Bonus d’agilité apporté.
     * @param intelligence Bonus d’intelligence apporté.
     */
	public Item(int number, TypeItem typeItem, String nom, int prix, int force, int agilite, int intelligence) {
		this.number = number;
		this.type = typeItem;
		this.nom = nom;
		this.prix = prix;
		this.force = force;
		this.agilite = agilite;
		this.intelligence = intelligence;
	}

	/**
     * Retourne le numéro de l’item.
     *
     * @return Numéro de l’item.
     */
	public int getNumber() {
		return number;
	}
	
	/**
     * Retourne le nom de l’item.
     *
     * @return Nom de l’item.
     */
	public String getNom() {
		return nom;
	}
	
	/**
     * Retourne le prix d’achat de l’item.
     *
     * @return Prix en pièces d’or.
     */
	public int getPrix() {
		return prix;
	}
	
	/**
     * Retourne le bonus de force de l’item.
     *
     * @return Valeur de la force.
     */
	public int getForce() {
		return force;
	}
	
	/**
     * Retourne le bonus d’agilité de l’item.
     *
     * @return Valeur de l’agilité.
     */
	public int getAgi() {
		return agilite;
	}
	
	/**
     * Retourne le bonus d’intelligence de l’item.
     *
     * @return Valeur de l’intelligence.
     */
	public int getIntel() {
		return intelligence;
	}
	
	/**
     * Retourne le {@link TypeItem} (catégorie) de cet item.
     *
     * @return Type de l’item.
     */
	public TypeItem getType() {
		return type;
	}
	
	/**
     * Affiche les caractéristiques de l’item au format :
     * <pre>
     * Numéro | Nom | Type : X | Force : Y | Agilité : Z | Intelligence : W | Prix : N
     * </pre>
     */
	public void afficherItem() {
		System.out.println(
			number  + " | " + nom  +  
			" | Type : " + type + 
			" | Force : " + force + 
			" | Agilite : " + agilite + 
			" | Intelligence : " + intelligence + 
			" | Prix : " + prix);
	}
}