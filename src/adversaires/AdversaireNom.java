package adversaires;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdversaireNom {
    private static final List<String> nomsDisponibles = new ArrayList<>(List.of(
        "Gorlag", "Thraz", "Velka", "Morwin", "Drakzor", "Lira", "Xandor", "Nerok", "Zyra", "Karn", "Zayn"
    ));

    private static final List<String> nomsRestants = new ArrayList<>(nomsDisponibles);

    public static void resetNoms() {
        nomsRestants.clear();
        nomsRestants.addAll(nomsDisponibles);
    }

    public static String getRandomNom() {
        if (nomsRestants.isEmpty()) {
            throw new IllegalStateException("Plus de noms d'adversaires disponibles !");
        }
        Collections.shuffle(nomsRestants);
        return nomsRestants.remove(0);
    }
}
