package game;

import java.util.Scanner;

/**
 * Classe utilitaire pour fournir un {@link Scanner} unique sur {@code System.in}.
 * Permet de partager une seule instance de {@link Scanner} entre toutes les classes
 * qui en auront besoin, évitant ainsi les problèmes de flux bloqués.
 */
public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Retourne l’instance unique de {@link Scanner} sur {@code System.in}.
     *
     * @return Scanner partagé.
     */
    public static Scanner getScanner() {
        return scanner;
    }
}