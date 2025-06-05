package game;

/**
 * Classe de lancement du jeu. Contient la méthode {@code main} qui instancie {@link Game}
 * et appelle la méthode {@link Game#start()} pour démarrer la boucle de partie.
 */
public class Start {
    
    /**
     * Point d’entrée de l’application.
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}