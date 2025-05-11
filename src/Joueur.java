package src;

import java.util.HashMap;

public abstract class Joueur extends Personnage {
    private HashMap<String, Item> inventaire = new HashMap<String, Item>();
    private int money;
    
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
}
