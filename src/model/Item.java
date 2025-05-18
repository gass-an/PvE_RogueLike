package model;
public class Item {
	private String type;
	private String nom;
	private int prix;
	private int force, agilite, intelligence;
	private int niv;
	 
	public Item(String typeItem, String nom, int prix, int force, int agilite, int intelligence, int niv) {
		this.type = typeItem;
		this.nom = nom;
		this.prix = prix;
		this.force = force;
		this.agilite = agilite;
		this.intelligence = intelligence;
		this.niv = niv;
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
	
	public String getType() {
		return type;
	}
	
	public int getNiv() {
		return niv;
	}
}
