package joueurs;
import java.util.HashMap;

import model.Item;
import model.Personnage;
import model.TypeItem;

public abstract class Joueur extends Personnage {
    protected HashMap<String, Item> inventaire = new HashMap<String, Item>();
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
        inventaire.put(item.getNom(), item);
    }

    public void removeItem(Item item) {
        inventaire.remove(item.getNom());
    }
    
    public void equiperItem(Item itemEquip) {
    	Item ancienItem = equipement.get(itemEquip.getType());
        equipement.put(itemEquip.getType(), itemEquip);
    	
    	if(ancienItem != null) {
            System.out.println("Nouvel équipement : " + itemEquip.getNom());
    	}
    	
    	else {
    		System.out.println("Pièce équipée : " + itemEquip.getNom());
    	}
    }

    public void desequiperItem(Item itemEquip) {
    	equipement.remove(itemEquip.getType());
    	System.out.println("L'item : " + itemEquip.getNom() + "est déséquipé.");
    	newItem(itemEquip);
    }
}
