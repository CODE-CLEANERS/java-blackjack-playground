package console;

import card.Card;
import user.User;

import java.util.Arrays;
import java.util.Deque;

public class OutputView {
    public void printNames(String[] usernames){
        String names = getNames(usernames);
        System.out.println(names);
    }

    private String getNames(String[] usernames) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(usernames).forEach(s -> sb.append(s).append(","));
        return sb.substring(0, sb.length() - 1);
    }

    public void distributedTwoCards(String[] usernames){
        System.out.println("딜러와 " + getNames(usernames) + "에게 2장의 나누었습니다.");
    }

    public void printUsersCardInfo(User user){
        String name = user.getName();
        StringBuilder sb = new StringBuilder();
        Deque<Card> cardsInHand = user.getCardsInHand();
        cardsInHand.forEach(card -> sb.append(card.getCardInfo()).append(", "));
        String cardInfo = sb.substring(0, sb.length() - 2);
        System.out.println(name + "카드: " + cardInfo);
    }
}
