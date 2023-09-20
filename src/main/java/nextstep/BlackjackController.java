package nextstep;

import nextstep.card.Card;
import nextstep.card.Deck;
import nextstep.gamer.Players;

public class BlackjackController {
    private final Deck deck;
    private final Players players;

    public BlackjackController(Deck deck, Players players) {
        this.deck = deck;
        this.players = players;
    }

    public void createDeck() {
        this.deck.createCardDeck();
        this.deck.shuffleDeck();
    }

    public Card popCard(){
        return this.deck.popCard();
    }

    public int totalPlayerSize(){
        return this.players.size();
    }

    public void dealCards() {
        this.players.dealCards(this.deck);
    }
}
