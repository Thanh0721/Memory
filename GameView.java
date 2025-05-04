import javax.swing.*;
import java.awt.*;

public class GameView {
    private final JFrame frame;
    private final JLabel statusLabel;
    private final GameEngine engine;

    public GameView(GameEngine engine) {
        this.engine = engine;
        engine.setView(this);

        frame = new JFrame("Memory Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Create the game board
        JPanel board = new JPanel(new GridLayout(4, 4, 5, 5));
        board.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        board.setBackground(new Color(50, 50, 50));

        // Add cards to board
        for (Card card : engine.getCards()) {
            board.add(card);
        }

        // Status label
        statusLabel = new JLabel("Matches: 0 | Attempts: 0");
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Add components to frame
        frame.add(board, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
    }

    public void show() {
        frame.setVisible(true);
    }

    public void updateUI() {
        frame.repaint();
    }

    public void updateStatus(int matches, int attempts) {
        statusLabel.setText("Matches: " + matches + " | Attempts: " + attempts);
    }

    public void showGameOver(int attempts) {
        JOptionPane.showMessageDialog(frame,
                "Congratulations! You won in " + attempts + " attempts.",
                "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}