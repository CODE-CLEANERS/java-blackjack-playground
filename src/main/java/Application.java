import blackjack.game.GameStarter;

public class Application {
    public static void main(String[] args) {
        GameStarter gameStarter = new GameStarter();
        gameStarter.start()
                .proceedBattingStep()
                .proceedDealingStep()
                .proceedPlayStep()
                .proceedCalculate();
    }
}
