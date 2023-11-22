package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected final String name;
    protected final List<Card> hands;
    protected double chip;

    public Player(String name) {
        this.name = name;
        this.hands = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getHands() {
        return this.hands;
    }


    public double getChip() {
        return chip;
    }

    public void addHand(Card card) {
        hands.add(card);
    }

    public int calculateHands() {
        int sum = 0;
        for (Card hand : hands) {
            sum = addNumber(hand, sum);
        }

        return sum;
    }

    private int addNumber(Card hand, int sum) {
        int number = hand.getNumber();
        if (isAce(hand) && sum + number > 21) {
            return sum + 1;
        }

        return sum + number;
    }

    private boolean isAce(Card card) {
        return card == Card.CLOVER_ACE
                || card == Card.HEART_ACE
                || card == Card.DIAMOND_ACE
                || card == Card.SPADE_ACE;
    }

    public boolean isBust() {
        int result = calculateHands();
        return result > 21;
    }

    public boolean isBlackJack() {
        if (hands.size() != 2) {
            return false;
        }

        int result = calculateHands();
        return result == 21;
    }

    public void addChip(double chip) {
        this.chip += chip;
    }
}
