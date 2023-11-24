package blackjack.game;

import blackjack.player.Dealer;
import blackjack.player.Gambler;
import view.GameOutputView;

public class CalculateStep {
    private final GameStarter gameStarter;

    public CalculateStep(GameStarter gameStarter) {
        this.gameStarter = gameStarter;
    }

    public void calculate() {
        Dealer dealer = gameStarter.getDealer();

        for (Gambler gambler : gameStarter.getGamblers()) {
            doCalculateChain(gambler);
            dealer.addChip(-gambler.getChip());
        }

        GameOutputView.announceFinalProfit(dealer, gameStarter.getGamblers());
    }

    private void doCalculateChain(Gambler gambler) {
        calculateGamblerIsBust(gambler);
    }

    private void calculateGamblerIsBust(Gambler gambler) {
        if (gambler.getHand().isBust()) {
            gambler.loss();
            return;
        }

        calculateGamblerIsBlackJack(gambler);
    }

    private void calculateGamblerIsBlackJack(Gambler gambler) {
        if (gambler.getHand().isBlackJack()) {
            calculateDealerIsBlackJack(gambler);
            return;
        }

        calculateDealerIsBust(gambler);
    }

    private void calculateDealerIsBlackJack(Gambler gambler) {
        if (gameStarter.getDealer().getHand().isBlackJack()) {
            return;
        }

        gambler.gainWithBlackJack();
    }

    private void calculateDealerIsBust(Gambler gambler) {
        if (gameStarter.getDealer().getHand().isBlackJack()) {
            gambler.gain();
            return;
        }

        compareDealerAndGamblerNumber(gambler);
    }

    private void compareDealerAndGamblerNumber(Gambler gambler) {
        int playerNumber = gambler.getHand().calculateHand();
        int dealerNumber = gameStarter.getDealer().getHand().calculateHand();
        if (playerNumber > dealerNumber) {
            gambler.gain();
            return;
        }

        if (playerNumber < dealerNumber) {
            gambler.loss();
        }
    }
}
