package nextstep.gamer;

public enum CardSumStatus {
    UNDER, BLACK_JACK, EXCEEDED,
    DEALER_MUST_PICK, DEALER_MUST_STAY;

    private static final Integer BLACKJACK_COUNT = 21;
    private static final Integer DEALER_STANDARD = 16;

    public static CardSumStatus getNumberStatusForPlayer(int cardSum){
        if (cardSum > BLACKJACK_COUNT) return EXCEEDED;
        if (cardSum < BLACKJACK_COUNT) return  UNDER;
        return BLACK_JACK;
    }

    public static CardSumStatus getNumberStatusForDealer(int cardSum){
        if (getNumberStatusForPlayer(cardSum) == BLACK_JACK) return BLACK_JACK;
        if (cardSum <= DEALER_STANDARD) return DEALER_MUST_PICK;
        return  DEALER_MUST_STAY;
    }
}
