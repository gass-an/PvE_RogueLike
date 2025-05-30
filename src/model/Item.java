package model;
public class Item {
	private TypeItem type;
	private String nom;
	private int prix, number;
	private int force, agilite, intelligence;
	 
	public Item(int number, TypeItem typeItem, String nom, int prix, int force, int agilite, int intelligence) {
		this.number = number;
		this.type = typeItem;
		this.nom = nom;
		this.prix = prix;
		this.force = force;
		this.agilite = agilite;
		this.intelligence = intelligence;
	}

	public int getNumber() {
		return number;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getPrix() {
		return prix;
	}
	
	public int getForce() {
		return force;
	}
	
	public int getAgi() {
		return agilite;
	}
	
	public int getIntel() {
		return intelligence;
	}
	
	public TypeItem getType() {
		return type;
	}
	
	
	public void afficherItem() {
		System.out.println(number  +" | " + nom  +  " | Type : " + type + " | Force : " + force + " | Agilite : " + agilite + " | Intelligence : " + intelligence);
	}
}