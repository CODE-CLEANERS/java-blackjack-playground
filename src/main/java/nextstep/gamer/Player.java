package nextstep.gamer;

public class Player extends Gamer {
    public Player(String name) {
        super(name);
    }

    public void foo(){
        Integer cardSum = getCardSum();
        CardSumStatus cardSumStatus = CardSumStatus.getNumberStatusForPlayer(getCardSum());
    }
    @Override
    public CardSumStatus statusCheck() {
        return CardSumStatus.getNumberStatusForPlayer(this.getCardSum());
    }
}
