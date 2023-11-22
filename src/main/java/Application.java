import blackjack.Game;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer();
        game.proceedBattingStep();
        game.deal();
        game.proceedPlayerPlay();
        game.proceedDealerPlay();
        game.calculateResult();
    }
}
