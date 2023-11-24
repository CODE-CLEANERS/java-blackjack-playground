package blackjack.card;

import java.util.*;

/**
 * 섞기 전의 덱을 캐싱
 */
public class OrderedDeck {
    private static OrderedDeck instance;
    private final List<Card> cards;

    private OrderedDeck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            Set<Card> suitCardSet = makeSuitCards(suit);
            cards.addAll(suitCardSet);
        }
    }

    private Set<Card> makeSuitCards(Suit suit) {
        Set<Card> suitCardSet = new LinkedHashSet<>();

        for (Rank rank : Rank.values()) {
            Card card = new Card(rank, suit);
            suitCardSet.add(card);
        }

        return suitCardSet;
    }

    public static OrderedDeck getInstance() {
        if (instance == null) {
            instance = new OrderedDeck();
        }

        return instance;
    }

    public List<Card> getDeck() {
        return new ArrayList<>(this.cards);
    }
}
