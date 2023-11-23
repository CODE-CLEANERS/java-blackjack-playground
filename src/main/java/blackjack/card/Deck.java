package blackjack.card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        this.cards = getShuffledDeck();
    }

    private List<Card> getShuffledDeck() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }

        return shuffleDeck(cards);
    }

    private List<Card> shuffleDeck(List<Card> cards) {
        Random random = new Random();
        List<Card> shuffledDeck = new LinkedList<>();
        for (int i = cards.size(); i > 0; i--) {
            int pick = random.nextInt(cards.size());
            shuffledDeck.add(cards.get(pick));
            cards.remove(pick);
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
