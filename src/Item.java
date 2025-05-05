package src;

class Item {
	 private String nom;
	 private int prix;
	 private int force, agilite, intelligence;
	 private String type;
	 private int niv;
	 
	 public Item(String nom, int prix, int force, int agilite, int intelligence, String type, int niv) {
		 this.nom = nom;
		 this.prix = prix;
		 this.force = force;
		 this.agilite = agilite;
		 this.intelligence = intelligence;
		 this.type = type;
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
	 
	 public void equiper() {
		 
	 }
	 
	 public void desequiper() {
		 
	 }
	 
	 /*
	 public Item acheter() {
		 
	 }
	 
	 public int vendre() {
	 }
	 */
}