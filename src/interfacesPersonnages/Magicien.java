package src.interfacesPersonnages;

import src.Personnage;

public interface Magicien {
    public void attaqueDistance(Personnage cible);
    public void paradeDistance(int degatsDistance);
    public void paradeMelee(int degatsMelee);
}
