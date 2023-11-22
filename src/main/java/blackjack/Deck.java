package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = getShuffledDeck();
    }

    private List<Card> getShuffledDeck() {
        Random random = new Random();
        List<Card> noShuffled = Arrays.asList(Card.values());
        List<Card> shuffled = new ArrayList<>();
        for (int i = noShuffled.size(); i > 0; i--) {
            int pick = random.nextInt(noShuffled.size());
            shuffled.add(noShuffled.get(pick));
            noShuffled.remove(pick);
        }

        return shuffled;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 남은 카드가 없습니다.");
        }

        Card top = cards.get(cards.size() - 1);
        cards.remove(cards.size() - 1);
        return top;
    }
}
