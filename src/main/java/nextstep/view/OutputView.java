package nextstep.view;

import nextstep.card.Card;
import nextstep.card.CardNumber;
import nextstep.card.CardShape;
import nextstep.gamer.Gamer;

import java.util.List;

public class OutputView {
    public void printGetPlayersName(){
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printBettingAmountByPlayerName(String playerName){
        System.out.println(playerName + "의 배팅 금액은?");
    }

    public void printInitialTwoCardsAreAttributed(List<String> playerNames){
        StringBuilder sb = new StringBuilder();
        playerNames.forEach(name -> sb.append(name).append(", "));
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        } // 마지막 추가된 " , " 제거
        System.out.println("딜러와" + sb.toString() + "딜러와 pobi, jason에게 2장의 나누었습니다.");
    }

    public void printCardNumbersByPlayerNames(List<Gamer> players) {
        players.forEach(this::printCardAndNumber);
    }

    public void printCardAndNumber(Gamer gamer){
        StringBuilder sb = new StringBuilder();
        List<Card> deck = gamer.getDeck();
        sb.append(gamer).append(": ");
        deck.forEach(card -> {
            CardShape cardShape = card.getCardShape();
            CardNumber cardNumber = card.getCardNumber();
            sb.append(cardNumber).append(cardShape).append(", ");
        });
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        } // 마지막 추가된 " , " 제거
        System.out.println(sb.toString());
    }

    public void printWantMoreCard(String username){
        System.out.println(username + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printDealerCardStatus(int dealerCardSum){
        if (dealerCardSum <= 16){
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
        if (dealerCardSum > 16){
            System.out.println("딜러는 카드를 더 받지 않았습니다.");
        }
    }
}
