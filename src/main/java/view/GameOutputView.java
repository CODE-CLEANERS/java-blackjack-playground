package view;

import blackjack.Dealer;
import blackjack.Player;
import blackjack.card.Card;

import java.util.List;

public class GameOutputView {

    private final static String DEAL_CARD_ANTECEDENT = "딜러에게 1장, ";
    private final static String DEAL_CARD_SUBSEQUENT = "에게 2장의 카드를 나누었습니다.";
    private final static String COMMA = ", ";
    private final static String SHOW_CARD_ANTECEDENT_FORMAT = "%s 카드 : ";
    private final static String SHOW_CARD_SUBSEQUENT_FORMAT = " - 결과: %d";
    private final static String ANNOUNCEMENT_DEALER_PLAY_FORMAT
            = "딜러는 손패의 합이 17이상이 될 때까지 %d장의 카드를 더 받았습니다.";
    private final static String ANNOUNCEMENT_RESULT = "## 최종 수익";
    private final static String NAME_AND_BAT_AMOUNT_PAIR_FORMAT = "%s: %.0f";

    public static void announceDealResult(List<Player> players) {
        Terminal.emptyln();
        StringBuilder sb = new StringBuilder();
        sb.append(DEAL_CARD_ANTECEDENT);
        for (int i = 0; i < players.size(); i++) {
            if (i != 0) {
                sb.append(COMMA);
            }

            sb.append(players.get(i).getName());
        }

        sb.append(DEAL_CARD_SUBSEQUENT);
        Terminal.outln(sb.toString());
    }

    public static void showHand(Player player) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(SHOW_CARD_ANTECEDENT_FORMAT, player.getName()));

        List<Card> hands = player.getHands();
        for (int i = 0; i < hands.size(); i++) {
            if (i != 0) {
                sb.append(COMMA);
            }

            sb.append(hands.get(i).getCardName());
        }

        sb.append(String.format(SHOW_CARD_SUBSEQUENT_FORMAT, player.calculateHands()));
        Terminal.outln(sb.toString());
    }

    public static void announceDealerPlay(int count) {
        Terminal.emptyln();
        Terminal.outln(String.format(ANNOUNCEMENT_DEALER_PLAY_FORMAT, count));
        Terminal.emptyln();
    }

    public static void announceFinalProfit(Dealer dealer, List<Player> players) {
        Terminal.emptyln();
        Terminal.outln(ANNOUNCEMENT_RESULT);
        Terminal.outln(String.format(NAME_AND_BAT_AMOUNT_PAIR_FORMAT, dealer.getName(), dealer.getChip()));
        for (Player player : players) {
            Terminal.outln(String.format(NAME_AND_BAT_AMOUNT_PAIR_FORMAT, player.getName(), player.getChip()));
        }
    }
}
