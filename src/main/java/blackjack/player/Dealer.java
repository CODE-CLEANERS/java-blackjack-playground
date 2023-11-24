package blackjack.player;

public class Dealer extends Player {

    public Dealer() {
        super("딜러");
        this.chip = 0;
    }

    public boolean isPlay() {
        int result = this.hands.calculateHand();
        return result <= 16;
    }

    public void addChip(double chip) {
        this.chip -= chip;
    }
}
