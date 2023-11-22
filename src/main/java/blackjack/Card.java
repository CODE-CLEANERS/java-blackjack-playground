package blackjack;

public enum Card {
    CLOVER_ACE(11),
    CLOVER_1(1),
    CLOVER_2(2),
    CLOVER_3(3),
    CLOVER_4(4),
    CLOVER_5(5),
    CLOVER_6(6),
    CLOVER_7(7),
    CLOVER_8(8),
    CLOVER_9(9),
    CLOVER_JACK(10),
    CLOVER_QUEEN(10),
    CLOVER_KING(10),
    DIAMOND_ACE(11),
    DIAMOND_1(1),
    DIAMOND_2(2),
    DIAMOND_3(3),
    DIAMOND_4(4),
    DIAMOND_5(5),
    DIAMOND_6(6),
    DIAMOND_7(7),
    DIAMOND_8(8),
    DIAMOND_9(9),
    DIAMOND_JACK(10),
    DIAMOND_QUEEN(10),
    DIAMOND_KING(10),
    HEART_ACE(11),
    HEART_1(1),
    HEART_2(2),
    HEART_3(3),
    HEART_4(4),
    HEART_5(5),
    HEART_6(6),
    HEART_7(7),
    HEART_8(8),
    HEART_9(9),
    HEART_JACK(10),
    HEART_QUEEN(10),
    HEART_KING(10),
    SPADE_ACE(11),
    SPADE_1(1),
    SPADE_2(2),
    SPADE_3(3),
    SPADE_4(4),
    SPADE_5(5),
    SPADE_6(6),
    SPADE_7(7),
    SPADE_8(8),
    SPADE_9(9),
    SPADE_JACK(10),
    SPADE_QUEEN(10),
    SPADE_KING(10);

    private final int number;

    Card (int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }
}