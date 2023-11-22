package blackjack;

public class Dealer extends Player {

    public Dealer() {
        super("딜러");
    }

    public boolean isPlay() {
        int result = calculateHands();
        return result <= 16;
    }
}
