package blackjack.player;

import blackjack.card.Card;
import blackjack.card.Hand;

public abstract class Player {
    protected final String name;
    protected final Hand hands;
    protected double chip;

    public Player(String name) {
        this.name = name;
        this.hands = new Hand();
    }

    public String getName() {
        return this.name;
    }

    public Hand getHand() {
        return this.hands;
    }

    public double getChip() {
        return chip;
    }

    public void addCardToHand(Card card) {
        hands.addCard(card);
    }
}
