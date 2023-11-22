package blackjack;

import util.Terminal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {

    private final Map<Player, Integer> playerAndBattingMap;
    private final Dealer dealer;
    private final Deck deck;

    public Game() {
        playerAndBattingMap = new HashMap<>();
        dealer = new Dealer();
        this.deck = new Deck();
    }

    public void addPlayer() {
        while (true) {
            String in;
            try {
                in = Terminal.in("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
                Terminal.outln();
            } catch (IOException e) {
                Terminal.out("입력이 바르지 않습니다.");
                continue;
            }

            String[] names = in.split(",");
            for (String name : names) {
                Player player = new Player(name);
                playerAndBattingMap.put(player, 0);
            }
            return;
        }
    }

    public void proceedBattingStep() {
        playerAndBattingMap.forEach((player, batAmount) -> proceedBattingStep(player));
    }

    private void proceedBattingStep(Player player) {
        while (true) {
            int batAmount;

            try {
                String in = Terminal.in(String.format("%s의 배팅 금액은?", player.getName()));
                Terminal.outln();
                batAmount = Integer.parseInt(in);
            } catch (IOException e) {
                Terminal.out("입력이 바르지 않습니다.");
                continue;
            }
            catch (IllegalArgumentException e) {
                Terminal.out(e.getMessage());
                continue;
            }

            playerAndBattingMap.replace(player, batAmount);
            return;
        }
    }

    public void deal() {
        drawAndAddHand(dealer);
        playerAndBattingMap.forEach((player, batAmount) -> drawAndAddHand(player));

        StringBuilder sb = new StringBuilder();
        sb.append("딜러에게 1장, ");
        boolean isFirst = true;
        for (Player player : playerAndBattingMap.keySet()) {
            if (!isFirst) {
                sb.append(", ");
            }

            sb.append(player.getName());
            isFirst = false;
        }

        sb.append("에게 2장의 카드를 나누었습니다.");

        Terminal.out(sb.toString());
        showHand(dealer);
        Terminal.outln();
    }

    private void drawAndAddHand(Player player) {
        player.addHand(deck.draw());

        if (player instanceof Dealer) {
            return;
        }

        player.addHand(deck.draw());
    }

    public void proceedPlayerPlay() {
        playerAndBattingMap.forEach((player, batAmount) -> askStayOrHit(player));
    }

    private void askStayOrHit(Player player) {
        showHand(player);

        while (!player.isBust()) {
            String input;

            try {
                input = Terminal.in(String.format("%s는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)", player.getName()));
                Terminal.outln();
            } catch (IOException e) {
                Terminal.out("입력이 바르지 않습니다.");
                continue;
            }

            if (input.trim().equalsIgnoreCase("y")) {
                player.addHand(deck.draw());
                showHand(player);
                continue;
            }

            if (input.trim().equalsIgnoreCase("n")) {
                return;
            }

            Terminal.out("y 또는 n 중에서 입력해주세요.");
        }
    }

    public void proceedDealerPlay() {
        while (dealer.isPlay()) {
            dealer.addHand(deck.draw());
            Terminal.out("딜러는 손패의 합이 16이하라 한장의 카드를 더 받았습니다.");
            Terminal.outln();
        }
    }

    public void calculateResult() {
        showHand(dealer);
        playerAndBattingMap.forEach((player, batAmount) -> showHand(player));

        Terminal.out("## 최종 수익");
        for (Map.Entry<Player, Integer> entry : playerAndBattingMap.entrySet()) {
            Player player = entry.getKey();
            int batAmount = entry.getValue();
            double profit = calculateProfit(player, batAmount);

            player.addChip(profit);
            dealer.addChip(-profit);
        }

        printFinalProfit(dealer);
        playerAndBattingMap.forEach((player, batAmount) -> printFinalProfit(player));
    }

    public void showHand(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s 카드 : ", player.getName()));

        List<Card> hands = player.getHands();
        for (int i = 0; i < hands.size(); i++) {
            if ( i != 0 ) {
                sb.append(", ");
            }

            sb.append(hands.get(i).getName());
        }

        sb.append(String.format(" - 결과: %d", player.calculateHands()));
        Terminal.out(sb.toString());
    }

    private double calculateProfit(Player player, int batAmount) {
        if (player.isBust()) {
            return -batAmount;
        }

        if (player.isBlackJack()) {
            if (dealer.isBlackJack()) {
                return 0;
            }

            return batAmount * 1.5;
        }

        if (dealer.isBust()) {
            return batAmount;
        }

        int playerNumber = player.calculateHands();
        int dealerNumber = dealer.calculateHands();
        if (playerNumber > dealerNumber) {
            return batAmount;
        }

        if (playerNumber < dealerNumber) {
            return -batAmount;
        }

        return 0;
    }

    private void printFinalProfit(Player player) {
        String message = String.format("%s: %.0f", player.getName(), player.getChip());
        Terminal.out(message);
    }
}
