package user;

import card.Card;
import hand.Asset;
import hand.Hand;

import java.util.Deque;

public abstract class User {
    private final Hand hand = new Hand();
    private final Asset asset = new Asset();
    private final String name;

    protected User(String name) {
        this.name = name;
    }
    public void addHand(Card card){
        this.hand.addCard(card);
    }
    public int getHandValue(){
        return this.hand.getHandValue();
    }
    public void sendMoney(User user, int money){
        this.asset.sendMoney(user.asset, money);
    }

    public Deque<Card> getCardsInHand(){
        return this.hand.getCardsInHand();
    }
    public int getMoney() {
        return this.asset.getMoney();
    }
    public String getName() {
        return this.name;
    }
}