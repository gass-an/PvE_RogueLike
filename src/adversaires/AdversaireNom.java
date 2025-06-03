package adversaires;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe utilitaire fournissant une liste pré-remplie de noms d’adversaires.
 * Permet d’obtenir un nom aléatoire et non répété dans une partie via {@link #getRandomNom()}.
 * Ajoute également la possibilité de réinitialiser la liste via {@link #resetNoms()}.
 */
public class AdversaireNom {

    /** Liste fixe des noms disponibles pour un adversaire. */
    private static final List<String> nomsDisponibles = new ArrayList<>(List.of(
        "Gorlag", "Thraz", "Velka", "Morwin", "Drakzor", "Lira", "Xandor", "Nerok", "Zyra", "Karn", "Zayn"
    ));

    /** Liste dynamique des noms encore non utilisés. */
    private static final List<String> nomsRestants = new ArrayList<>(nomsDisponibles);

    /**
     * Réinitialise la liste des noms restants pour un nouveau cycle de partie.
     */
    public static void resetNoms() {
        nomsRestants.clear();
        nomsRestants.addAll(nomsDisponibles);
    }

    /**
     * Retourne un nom aléatoire parmi les {@code nomsRestants} et le retire de la liste
     * pour éviter les doublons dans la même partie.
     *
     * @return Chaîne de caractères correspondant au nom choisi.
     * @throws IllegalStateException si la liste des noms restants est vide.
     */
    public static String getRandomNom() {
        if (nomsRestants.isEmpty()) {
            throw new IllegalStateException("Plus de noms d'adversaires disponibles !");
        }
        Collections.shuffle(nomsRestants);
        return nomsRestants.remove(0);
    }
}