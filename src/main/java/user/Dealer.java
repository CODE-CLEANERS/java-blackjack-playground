package user;

public class Dealer extends User{
    protected Dealer(String name) {
        super(name);
    }

    public boolean shouldDrawCard() {
        return getHandValue() <= 16;
    }
}