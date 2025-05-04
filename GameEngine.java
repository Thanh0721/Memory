import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameEngine {
    private final int PAIRS;
    private final List<Card> cards;
    private Card firstCard = null;
    private Card secondCard = null;
    private int matchesFound = 0;
    private int attempts = 0;
    private GameView view;

    public GameEngine(int pairs) {
        this.PAIRS = pairs;
        this.cards = new ArrayList<>();
        initializeCards();
    }

    public void setView(GameView view) {
        this.view = view;
    }

    private void initializeCards() {
        List<Integer> cardValues = new ArrayList<>();
        for (int i = 1; i <= PAIRS; i++) {
            cardValues.add(i);
            cardValues.add(i);
        }
        Collections.shuffle(cardValues);

        for (int value : cardValues) {
            Card card = new Card(value);
            card.addActionListener(new CardClickListener());
            cards.add(card);
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    private class CardClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (view == null) return;

            Card clickedCard = (Card) e.getSource();

            // Ignore if card is already matched or is the same card clicked twice
            if (clickedCard.isMatched() || clickedCard.isRevealed()) {
                return;
            }

            // Flip the card
            clickedCard.revealCard();
            view.updateUI();

            // If this is the first card selected
            if (firstCard == null) {
                firstCard = clickedCard;
                return;
            }

            // Second card selected
            secondCard = clickedCard;
            attempts++;
            view.updateStatus(matchesFound, attempts);

            // Check for match
            if (firstCard.getValue() == secondCard.getValue()) {
                firstCard.setMatched(true);
                secondCard.setMatched(true);
                firstCard = null;
                secondCard = null;
                matchesFound++;
                view.updateStatus(matchesFound, attempts);

                // Check if game is over
                if (matchesFound == PAIRS) {
                    view.showGameOver(attempts);
                }
            } else {
                // No match - flip cards back after delay
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        firstCard.hideCard();
                        secondCard.hideCard();
                        firstCard = null;
                        secondCard = null;
                        view.updateUI();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }
}