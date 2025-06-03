package interfacesPersonnages;

import model.Personnage;

/**
 * Interface définissant le comportement d’un personnage de type Barbare.
 * Un Barbare peut attaquer en mêlée, parer en distance et parer en mêlée.
 */
public interface Barbare {
    
    /**
     * Effectue une attaque en mêlée sur la cible.
     *
     * @param cible {@link Personnage} recevant les dégâts en mêlée.
     */
    public void attaqueMelee(Personnage cible);
    
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
