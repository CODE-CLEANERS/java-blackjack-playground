package blackjack;

public class Dealer extends Player {

    public Dealer() {
        super("딜러");
        this.chip = 0;
    }

    public boolean isPlay() {
        int result = calculateHands();
        return result <= 16;
    }

    public void addChip(double chip) {
        this.chip -= chip;
    }
}
