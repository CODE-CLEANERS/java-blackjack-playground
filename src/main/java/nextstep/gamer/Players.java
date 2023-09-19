package nextstep.gamer;

import nextstep.card.Deck;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Gamer> players;

    public Players() {
        this.players = new ArrayList<>();
        this.players.add(new Dealer("Dealer")); // 딜러는 무적권 존재
    }

    public void addAll(List<Gamer> gamers) {
        this.players.addAll(gamers);
    }

    public int size() {
        return players.size();
    }

    public void dealCards(Deck cards) {
        this.players.forEach(gamer -> gamer.pickCard(cards.popCard()));
    }
}
