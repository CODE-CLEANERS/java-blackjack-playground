package hand;

import card.Card;
import card.Number;

import java.util.ArrayDeque;
import java.util.Deque;

public class Hand {
    private final Deque<Card> cardsInHand = new ArrayDeque<>();

    public void addCard(Card card){
        cardsInHand.add(card);
    }
    public int getAceQuantity(){
        return (int) cardsInHand.stream()
                .filter(card -> card.getNumber().equals(Number.ACE))
                .count();
    }
    public int calculateValueWithoutACE(){
        return this.cardsInHand.stream()
                .map(Card::getNumber)
                .mapToInt(Number::getValue)
                .sum();
    }
    public int getHandValue() {
        int valueWithoutACE = calculateValueWithoutACE();
        int aceQuantity = getAceQuantity();

        if (aceQuantity > 0 && isACEAddable(valueWithoutACE)) {
            return getAceContainedValue(valueWithoutACE, aceQuantity);
        }
        return valueWithoutACE;
    }
    private int getAceContainedValue(int valueWithoutACE, int aceQuantity) {
        while (aceQuantity > 0 && isACEAddable(valueWithoutACE)) {
            valueWithoutACE += 10;
            aceQuantity--;
        }
        return valueWithoutACE;
    }
    private boolean isACEAddable(int valueWithoutACE) {
        return valueWithoutACE + 10 <= 21;
    }
    public Deque<Card> getCardsInHand() {
        return cardsInHand;
    }
}