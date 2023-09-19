package nextstep.card;

import java.util.*;

public class Cards {

    private final Stack<Card> cards;

    public Cards() {
       this.cards = createCardDeck();
    }

    public Stack<Card> createCardDeck() {
        Stack<Card> cards = new Stack<>();
        for (CardShape cardShape : CardShape.values()) {
            createNumbersByShape(cards, cardShape);
        }
        return cards;
    }

    private void createNumbersByShape(Stack<Card> cards, CardShape cardShape) {
        for (CardNumber cardNumber : CardNumber.values()) {
            cards.add(new Card(cardShape, cardNumber));
        }
    }

    public void shuffleCards(){
        Collections.shuffle(this.cards);
    }

    public Card popCard() {
        return this.cards.pop();
    }

    public int size(){
        return this.cards.size();
    }
}
