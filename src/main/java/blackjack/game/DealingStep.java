package blackjack.game;

import blackjack.player.Dealer;
import blackjack.player.Gambler;
import blackjack.player.Player;
import view.GameOutputView;

public class DealingStep {

    private final GameStarter gameStarter;

    protected DealingStep(GameStarter gameStarter) {
        this.gameStarter = gameStarter;
    }

    public PlayStep proceedPlayStep() {
        PlayStep playStep = new PlayStep(this.gameStarter);
        playStep.playGambler();
        return playStep;
    }

    protected void deal() {
        draw(gameStarter.getDealer());
        for (Gambler gambler : gameStarter.getGamblers()) {
            draw(gambler);
        }

        GameOutputView.announceDealResult(gameStarter.getGamblers());
    }

    private void draw(Player player) {
        int repeat = 2;

        if (player instanceof Dealer) {
            repeat = 1;
        }

        for (int i = 0; i < repeat; i++) {
            player.addCardToHand(gameStarter.getDeck().draw());
        }
    }
}
