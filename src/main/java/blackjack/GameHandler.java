package blackjack;

import blackjack.card.Deck;
import view.GameInputView;
import view.GameOutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameHandler {

    private final List<Player> players;
    private final Dealer dealer;
    private final Deck deck;

    public GameHandler() {
        players = new ArrayList<>();
        dealer = new Dealer();
        this.deck = new Deck();
    }

    public void handleAddPlayer() {
        while (true) {
            Optional<String> in = GameInputView.askPlayerName();
            if (!in.isPresent()) {
                continue;
            }

            String[] names = in.get().split(",");
            for (String name : names) {
                Player player = new Player(name);
                players.add(player);
            }
            return;
        }
    }

    public void handleBatting() {
        for (Player player : players) {
            bat(player);
        }
    }

    private void bat(Player player) {
        while (true) {
            Optional<Integer> in = GameInputView.askBatAmount(player.getName());
            if (!in.isPresent()) {
                continue;
            }

            Integer batAmount = in.get();
            player.setBatAmount(batAmount);
            return;
        }
    }

    public void handleDeal() {
        draw(dealer);
        for (Player player : players) {
            draw(player);
        }

        GameOutputView.announceDealResult(players);
    }

    private void draw(Player player) {
        int repeat = 2;

        if (player instanceof Dealer) {
            repeat = 1;
        }

        for (int i = 0; i < repeat; i++) {
            player.addHand(deck.draw());
        }
    }

    public void handlePlayerPlay() {
        for (Player player : players) {
            play(player);
        }
    }

    private void play(Player player) {
        GameOutputView.showHand(player);
        while (player.isNotBust()) {
            Optional<Boolean> in = GameInputView.askHit(player.getName());

            if (!in.isPresent()) {
                continue;
            }

            Boolean isHit = in.get();
            if (!isHit) {
                return;
            }

            player.addHand(deck.draw());
            GameOutputView.showHand(player);
        }
    }

    public void proceedDealerPlay() {
        int count = 0;
        while (dealer.isPlay()) {
            dealer.addHand(deck.draw());
            count++;
        }
        GameOutputView.announceDealerPlay(count);
    }

    public void handleCalculateStep() {
        GameOutputView.showHand(dealer);
        for (Player player : players) {
            GameOutputView.showHand(player);
            calculate(player);
            dealer.addChip(player.getChip());
        }

        GameOutputView.announceFinalProfit(dealer, players);
    }

    private void calculate(Player player) {
        if (player.isBust()) {
            player.loss();
            return;
        }

        if (player.isBlackJack()) {
            if (dealer.isBlackJack()) {
                return;
            }

            player.gainWithBlackJack();
            return;
        }

        if (dealer.isBust()) {
            player.gain();
            return;
        }

        int playerNumber = player.calculateHands();
        int dealerNumber = dealer.calculateHands();
        if (playerNumber > dealerNumber) {
            player.gain();
            return;
        }

        if (playerNumber < dealerNumber) {
            player.loss();
        }
    }
}
