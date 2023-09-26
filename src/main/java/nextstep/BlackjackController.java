package nextstep;

import nextstep.card.Card;
import nextstep.card.Deck;
import nextstep.gamer.Players;
import nextstep.view.InputView;
import nextstep.view.OutputView;

import java.util.List;

public class BlackjackController {
    private final Deck deck;
    private final Players players;
    private final InputView inputView;
    private final OutputView outputView;
    public BlackjackController(Deck deck, Players players, InputView inputView, OutputView outputView) {
        this.deck = deck;
        this.inputView = inputView;
        this.outputView = outputView;
        this.deck.shuffleDeck();
        this.players = players;
    }

    public int totalPlayerSize(){
        return this.players.size();
    }

    public void dealCards(int trial) {
        for (int i = 0; i < trial; i++) {
            this.players.dealCards(this.deck);
        }
    }
    public List<String> getAddablePlayersNames(){
        return this.players.getAddablePlayersNames();
    }
}
