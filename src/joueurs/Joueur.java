package joueurs;
import java.util.HashMap;

import model.Item;
import model.Personnage;
import model.TypeItem;

/**
 * Classe abstraite représentant un joueur générique.
 * Hérite de {@link Personnage} et ajoute :
 * <ul>
 *   <li>Un inventaire de type {@code HashMap<String, Item>}</li>
 *   <li>Un équipement (une pièce par {@link TypeItem})</li>
 *   <li>Une gestion de l’argent ({@code money})</li>
 * </ul>
 * Le joueur hérite du calcul dynamique des statistiques via l’override de {@link #getForce()},
 * {@link #getIntelligence()} et {@link #getAgilite()}, qui ajoutent les bonus des objets équipés.
 */
public abstract class Joueur extends Personnage {
    protected HashMap<String, Item> inventaire = new HashMap<String, Item>();
    protected HashMap<TypeItem, Item> equipement = new HashMap<TypeItem, Item>();
    protected int money;

    /**
     * Retourne la force effective du joueur, incluant les bonus des objets équipés.
     *
     * @return Force de base + somme des forces des objets équipés.
     */
    @Override
    public int getForce() {
        return force + equipement.values().stream().mapToInt(Item::getForce).sum();
    }

    /**
     * Retourne l’intelligence effective du joueur, incluant les bonus des objets équipés.
     *
     * @return Intelligence de base + somme des intelligences des objets équipés.
     */
    @Override
    public int getIntelligence() {
        return intelligence + equipement.values().stream().mapToInt(Item::getIntel).sum();
    }

    /**
     * Retourne l’agilité effective du joueur, incluant les bonus des objets équipés.
     *
     * @return Agilité de base + somme des agilité des objets équipés.
     */
    @Override
    public int getAgilite() {
        return agilite + equipement.values().stream().mapToInt(Item::getAgi).sum();
    }

    /**
     * Retourne le montant d’argent actuel du joueur.
     *
     * @return Solde du joueur.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Ajoute une somme d’argent au solde du joueur.
     *
     * @param money Montant à ajouter.
     */
    public void addMoney(int money) {
        this.money += money;
    }

    /**
     * Vérifie si le joueur dispose d’au moins {@code money} pièces d’or.
     *
     * @param money Montant à vérifier.
     * @return true si le joueur a suffisamment d’argent, false sinon.
     */
    public boolean haveMoney(int money) {
        if (money > this.money) {
            return false;
        } 
        return true;
    }

    /**
     * Retire une somme d’argent du solde du joueur s’il en a la capacité.
     *
     * @param money Montant à retirer.
     */
    public void removemoney(int money) {
        if (this.haveMoney(money)) {
            this.money -= money;
        }
    }

    /**
     * Ajoute un {@link Item} à l’inventaire du joueur.
     *
     * @param item L’objet à ajouter.
     */
    public void newItem(Item item) {
        inventaire.put(item.getNom(), item);
    }

    /**
     * Retire un {@link Item} de l’inventaire du joueur.
     *
     * @param item L’objet à retirer.
     */
    public void removeItem(Item item) {
        inventaire.remove(item.getNom());
    }
    
    /**
     * Équipe un {@link Item} donné. S’il y avait déjà un équipement du même type, 
     * il est remplacé et son nom est affiché.
     *
     * @param itemEquip Objet à équiper (sera stocké dans {@code equipement}).
     */
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

    /**
     * Déséquipe un {@link Item}. L’objet est retiré de l’équipement et replacé dans l’inventaire.
     *
     * @param itemEquip Objet à déséquiper.
     */
    public void desequiperItem(Item itemEquip) {
    	equipement.remove(itemEquip.getType());
    	System.out.println("L'item : " + itemEquip.getNom() + "est déséquipé.");
    	newItem(itemEquip);
    }

    /**
     * Gère le gain de niveau du joueur :
     * <ol>
     *   <li>Augmente {@code lvl} de 1</li>
     *   <li>Augmente {@code pvMax} de 5</li>
     *   <li>Restaure 5 PV</li>
     *   <li>Ajoute 5 pièces d’or</li>
     *   <li>Affiche un message à l’écran</li>
     * </ol>
     */
    public void gagneUnNiveau() {
        this.addLvl();
        this.addPVMax(5);
        this.addPV(5);
        this.addMoney(5);
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Vous gagnez 1 niveau, vous recevez 1 Point de compétence, 5 Points de vie et 5 pièces d'or");
    }
}
