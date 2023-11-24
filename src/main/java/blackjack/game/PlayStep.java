package blackjack.game;

import blackjack.player.Dealer;
import blackjack.player.Gambler;
import view.GameInputView;
import view.GameOutputView;

import java.util.Optional;

public class PlayStep {

    private final GameStarter gameStarter;

    protected PlayStep(GameStarter gameStarter) {
        this.gameStarter = gameStarter;
    }

    public void proceedCalculate() {
        CalculateStep calculateStep = new CalculateStep(this.gameStarter);
        calculateStep.calculate();
    }

    protected void playGambler() {
        for (Gambler gambler : gameStarter.getGamblers()) {
            GameOutputView.showHand(gambler);
            playGambler(gambler);
            playDealer(0);
        }
    }

    private void playGambler(Gambler gambler) {
        if (gambler.getHand().isBust()) {
            return;
        }

        Optional<Boolean> in = GameInputView.askHit(gambler.getName());
        if (!in.isPresent()) {
            playGambler(gambler);
            return;
        }

        Boolean isHit = in.get();
        if (!isHit) {
            return;
        }

        gambler.addCardToHand(gameStarter.getDeck().draw());
        GameOutputView.showHand(gambler);
        playGambler(gambler);
    }

    private void playDealer(int count) {
        Dealer dealer = gameStarter.getDealer();
        if (dealer.isPlay()) {
            dealer.addCardToHand(gameStarter.getDeck().draw());
            playDealer(++count);
            return;
        }

        GameOutputView.announceDealerPlay(count);
    }
}
