package blackjack.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    private static final int BLACK_JACK_NUMBER = 21;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateHand() {
        int sum = 0;
        for (Card card : cards) {
            sum = addNumber(card, sum);
        }

        return sum;
    }

    private int addNumber(Card card, int sum) {
        int number = card.getNumber();
        if (card.isAce() && sum + number > BLACK_JACK_NUMBER) {
            return sum + 1;
        }

        return sum + number;
    }

    public boolean isBust() {
        int result = calculateHand();
        return result > BLACK_JACK_NUMBER;
    }

    public boolean isBlackJack() {
        if (cards.size() != 2) {
            return false;
        }

        int result = calculateHand();
        return result == BLACK_JACK_NUMBER;
    }

    public int size() {
        return this.cards.size();
    }

    public Card get(int i) {
        return this.cards.get(i);
    }
}
