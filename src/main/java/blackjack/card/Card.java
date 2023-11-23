package blackjack.card;

public class Card {
    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getCardName() {
        return this.rank.getMark() + this.suit.getName();
    }

    public boolean isAce() {
        return this.rank == Rank.ACE;
    }

    public int getNumber() {
        return this.rank.getNumber();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result * rank.hashCode();
        result = 37 * result * suit.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Card compare = (Card) obj;

        return this.suit == compare.suit
                && this.rank == compare.rank;
    }
}
