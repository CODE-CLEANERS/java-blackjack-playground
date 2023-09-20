package nextstep.gamer;

public class Player extends GameUser {
    public Player(String name) {
        super(name);
    }

    @Override
    public CardSumStatus statusCheck() {
        return CardSumStatus.getNumberStatusForPlayer(this.getCardSum());
    }
}
