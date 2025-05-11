package src.interfacesPersonnages;

import src.Personnage;

public interface Paysant {
    public void attaqueDistance(Personnage cible);
    public void attaqueMelee(Personnage cible);
    public void paradeDistance();
    public void paradeMelee(); 
}
