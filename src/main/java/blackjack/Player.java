package blackjack;

import blackjack.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {
    protected final String name;
    protected final List<Card> hands;
    protected double chip;
    private int batAmount;

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

    public void setBatAmount(int batAmount) {
        this.batAmount = batAmount;
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
        if (hand.isAce() && sum + number > 21) {
            return sum + 1;
        }

        return sum + number;
    }

    public boolean isBust() {
        int result = calculateHands();
        return result > 21;
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isBlackJack() {
        if (hands.size() != 2) {
            return false;
        }

        int result = calculateHands();
        return result == 21;
    }

    public void gain() {
        this.chip += batAmount;
    }

    public void gainWithBlackJack() {
        this.chip += batAmount * 1.5;
    }

    public void loss() {
        this.chip -= batAmount;
    }
}
