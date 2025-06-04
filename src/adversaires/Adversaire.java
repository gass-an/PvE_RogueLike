package adversaires;

import model.Personnage;

/**
 * Classe abstraite représentant un adversaire dans le jeu.
 * Un adversaire hérite des attributs et comportements de {@link Personnage}.
 * Les classes concrètes doivent implémenter la méthode {@link #sePresente()}.
 */
public abstract class Adversaire extends Personnage{
    /**
     * Affiche une présentation textuelle de l’adversaire (son nom et son rôle).
     * Doit être implémenté par chaque sous-classe concrète (Barbare, Magicien, Paysan).
     */
    public abstract void sePresente();
}