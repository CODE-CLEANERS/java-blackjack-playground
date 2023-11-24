package blackjack.player;

public class Gambler extends Player {
    private int batAmount;

    public Gambler(String name) {
        super(name);
    }

    public void setBatAmount(int batAmount) {
        this.batAmount = batAmount;
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
