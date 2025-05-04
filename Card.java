import javax.swing.*;
import java.awt.*;

public class Card extends JButton {
    private final int value;
    private boolean matched = false;
    private boolean revealed = false;

    public Card(int value) {
        this.value = value;
        setPreferredSize(new Dimension(80, 80));
        setBackground(Color.BLUE);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 24));
        setFocusPainted(false);
        hideCard();
    }

    public int getValue() {
        return value;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
        if (matched) {
            setBackground(Color.GREEN);
        }
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void revealCard() {
        setText(String.valueOf(value));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        revealed = true;
    }

    public void hideCard() {
        if (!matched) {
            setText("");
            setBackground(Color.BLUE);
            revealed = false;
        }
    }
}