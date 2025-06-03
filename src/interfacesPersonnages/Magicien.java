package interfacesPersonnages;

import model.Personnage;

/**
 * Interface définissant le comportement d’un personnage de type Magicien.
 * Un Magicien peut attaquer à distance, attaquer en mêlée, parer en distance et parer en mêlée.
 */
public interface Magicien {
    
    /**
     * Effectue une attaque à distance sur la cible.
     *
     * @param cible {@link Personnage} recevant les dégâts à distance.
     */
    public void attaqueDistance(Personnage cible);
    
    /**
     * Active la parade contre une attaque en distance.
     * Si activée, les dégâts reçus à distance seront réduits par l’agilité.
     */
    public void paradeDistance();
    
    /**
     * Active la parade contre une attaque en mêlée.
     * Si activée, les dégâts reçus en mêlée seront réduits par l’agilité.
     */
    public void paradeMelee();
}
