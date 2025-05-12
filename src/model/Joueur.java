package model;
import java.util.HashMap;

public abstract class Joueur extends Personnage {
    protected HashMap<TypeItem, Item> inventaire = new HashMap<TypeItem, Item>();
    protected HashMap<TypeItem, Item> equipement = new HashMap<TypeItem, Item>();
    protected int money;
    
    public int getMoney() {
        return money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public boolean haveMoney(int money) {
        if (money > this.money) {
            return false;
        } 
        return true;
    }

    public void removemoney(int money) {
        if (this.haveMoney(money)) {
            this.money -= money;
        }
    }

    public void newItem(Item item) {
        inventaire.put(item.getType(), item);
    }

    public void removeItem(Item item) {
        inventaire.remove(item.getType());
    }
    
    public void equiper(Item itemEquip) {
    	Item ancienItem = equipement.put(itemEquip.getType(), itemEquip);
    	
    	if(ancienItem != null) {
    		System.out.println("Nouvel équipement : " + itemEquip.getNom());
    	}
    	
    	else {
    		System.out.println("Pièce équipée : " + itemEquip.getNom());
    	}
    }
}
