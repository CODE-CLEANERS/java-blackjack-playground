package blackjack.card;

import java.util.*;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        OrderedDeck orderedDeck = OrderedDeck.getInstance();
        this.cards = shuffle(orderedDeck.getDeck());
    }

    private List<Card> shuffle(List<Card> cards) {
        Random random = new Random();
        List<Card> shuffledDeck = new LinkedList<>();
        for (int i = cards.size(); i > 0; i--) {
            int pick = random.nextInt(cards.size());
            Card picked = cards.remove(pick);
            shuffledDeck.add(picked);
        }

        return shuffledDeck;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 남은 카드가 없습니다.");
        }

        Card top = cards.get(0);
        cards.remove(0);
        return top;
    }
}
