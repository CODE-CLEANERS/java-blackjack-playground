package card;

import java.util.*;

public class Deck {
    private static final Deque<Card> cards = new ArrayDeque<>();
    static {
        Arrays.stream(Shape.values()).forEach(
                shape -> Arrays.stream(Number.values())
                        .forEach(number -> cards.add(new Card(shape, number)))
        );
    }
    public void shuffle() {
        List<Card> cardList = new ArrayList<>(cards);
        Collections.shuffle(cardList);
        cards.clear();
        cards.addAll(cardList);
    }

    public Card getCard(){
        return cards.pop();
    }
}