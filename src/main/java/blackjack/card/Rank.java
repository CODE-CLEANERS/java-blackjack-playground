package blackjack.card;

public enum Rank {
    ACE(11, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int number;
    private final String mark;

    Rank(int number, String mark) {
        this.number = number;
        this.mark = mark;
    }

    public int getNumber() {
        return this.number;
    }

    public String getMark() {
        return this.mark;
    }
}
