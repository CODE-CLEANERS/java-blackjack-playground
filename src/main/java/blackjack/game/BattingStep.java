package blackjack.game;

import blackjack.player.Gambler;
import view.GameInputView;

import java.util.Optional;

public class BattingStep {

    private final GameStarter gameStarter;

    protected BattingStep(GameStarter gameStarter) {
        this.gameStarter = gameStarter;
    }

    public DealingStep proceedDealingStep() {
        DealingStep dealingStep = new DealingStep(this.gameStarter);
        dealingStep.deal();
        return dealingStep;
    }

    protected void bat() {
        for (Gambler gambler : gameStarter.getGamblers()) {
            bat(gambler);
        }
    }

    private void bat(Gambler gambler) {
        Optional<Integer> in = GameInputView.askBatAmount(gambler.getName());
        if (!in.isPresent()) {
            bat(gambler);
            return;
        }

        Integer batAmount = in.get();
        gambler.setBatAmount(batAmount);
    }
}
