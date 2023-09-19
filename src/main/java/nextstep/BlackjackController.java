package nextstep;

import nextstep.card.Card;
import nextstep.card.Cards;
import nextstep.gamer.Gamer;
import nextstep.gamer.Players;

import java.util.List;

public class BlackjackController {
    private final Cards cards;
    private final Players players;

    public BlackjackController(Cards cards) {
        this.cards = cards;
        this.players = new Players();
    }

    public void createDeck() {
        this.cards.createCardDeck();
        this.cards.shuffleCards();
    }

    public Card popCard(){
        return this.cards.popCard();
    }

    public void enrollPlayer(List<Gamer> gamers) {
        this.players.addAll(gamers);
    }

    public int totalPlayerSize(){
        return this.players.size();
    }

    public void dealCards() {
        this.players.dealCards(this.cards);
    }
}
