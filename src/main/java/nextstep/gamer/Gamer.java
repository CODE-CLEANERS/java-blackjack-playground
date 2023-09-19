package nextstep.gamer;

import nextstep.card.Card;
import nextstep.card.CardNumber;

import java.util.*;

public abstract class Gamer {
    private final List<Card> cardsInHand;
    private final String name;
    private Integer valance = 100000;
    private Integer cardSum = 0;
    private static final Integer ADDABLE_NUMBER_FOR_ACE = 10;
    private static final Integer NUMBER_CONSTRAINT = 21;

    protected Gamer(String name) {
        this.name = name;
        this.cardsInHand = new ArrayList<>();
    }

    public List<Card> getCardsInHand(){
        return this.cardsInHand;
    }

    public String getName(){
        return this.name;
    }

    public Integer getValance() {
        return valance;
    }

    public List<Card> getCardsInHand() {
        return cardsInHand;
    }

    public void offerStake(Integer thisGameCost) {
        valance -= thisGameCost;
    }

    public void pickCard(Card card) {
        cardSum += card.getNumericValue();
        cardsInHand.add(card);
    }

    public void pickCard(List<Card> cards) { // For Logic
        cardsInHand.addAll(cards);
        cardSum += cards.stream().map(Card::getNumericValue).reduce(0, Integer::sum);
    }

    public Integer getCardSum(){
        if (isAceConvertable() && cardSum + ADDABLE_NUMBER_FOR_ACE <= NUMBER_CONSTRAINT) {
            return cardSum + ADDABLE_NUMBER_FOR_ACE;
        }
        return cardSum;
    }

    public boolean isSumOverMax() {
        return this.getCardSum() > NUMBER_CONSTRAINT;
    }

    private boolean containsAce(){ // For Test
        return this.cardsInHand.stream().map(Card::getNumericValue).anyMatch(integer -> integer == CardNumber.ACE.getNumericValue());
    }

    private boolean isAceConvertable(){
        if (containsAce()){
            return Math.abs(this.cardSum + ADDABLE_NUMBER_FOR_ACE - NUMBER_CONSTRAINT) < Math.abs(cardSum - NUMBER_CONSTRAINT);
        }
        return false;
    }

    abstract CardSumStatus statusCheck();
}