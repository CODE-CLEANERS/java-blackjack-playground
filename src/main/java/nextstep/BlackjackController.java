package nextstep;

import nextstep.card.Card;
import nextstep.card.Deck;
import nextstep.gamer.Gamer;
import nextstep.gamer.Players;

import java.util.List;

public class BlackjackController {
    private final Deck deck;
    private final Players players;

    public BlackjackController(Deck deck) {
        this.deck = deck;
        this.players = new Players();
    }

    public void createDeck() {
        this.deck.createCardDeck();
        this.deck.shuffleDeck();
    }

    public Card popCard(){
        return this.deck.popCard();
    }

    public void enrollPlayer(List<Gamer> gamers) {
        this.players.addAll(gamers);
    }

    public int totalPlayerSize(){
        return this.players.size();
    }

    public void dealCards() {
        this.players.dealCards(this.deck);
    }
}
