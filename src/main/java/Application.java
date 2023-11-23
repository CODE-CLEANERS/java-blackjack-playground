import blackjack.GameHandler;

public class Application {
    public static void main(String[] args) {
        GameHandler gameHandler = new GameHandler();
        gameHandler.handleAddPlayer();
        gameHandler.handleBatting();
        gameHandler.handleDeal();
        gameHandler.handlePlayerPlay();
        gameHandler.proceedDealerPlay();
        gameHandler.handleCalculateStep();
    }
}
