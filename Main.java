public class Main {
    public static void main(String[] args) {
        // Create game with 8 pairs (16 cards)
        GameEngine engine = new GameEngine(8);
        GameView view = new GameView(engine);
        view.show();
    }
}