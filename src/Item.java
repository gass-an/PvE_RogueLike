//package src;

public class Item {
	private TypeItem type;
	private String nom;
	private int prix;
	private int force, agilite, intelligence;
	private int niv;
	 
	public Item(TypeItem type, String nom, int prix, int force, int agilite, int intelligence, int niv) {
		this.type = type;
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
	
	public TypeItem getType() {
		return type;
	}
	
	public int getNiv() {
		return niv;
	}
	
	/*
	public Item acheter() {
		
	}
	
	public int vendre() {
	}
	*/
}