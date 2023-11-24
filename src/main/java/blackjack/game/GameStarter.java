package blackjack.game;

import blackjack.card.Deck;
import blackjack.player.Dealer;
import blackjack.player.Gambler;

import java.util.List;

public class GameStarter {

    private List<Gambler> gamblers;
    private final Dealer dealer;
    private final Deck deck;

    public GameStarter() {
        dealer = new Dealer();
        this.deck = new Deck();
    }

    public PlayerRegistrationStep start() {
        PlayerRegistrationStep playerRegistrationStep = new PlayerRegistrationStep(this);
        gamblers = playerRegistrationStep.addGambler();
        return playerRegistrationStep;
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }
}
