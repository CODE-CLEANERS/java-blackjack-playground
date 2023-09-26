package nextstep.gamer;

import nextstep.card.Card;
import nextstep.card.Deck;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<GameUser> users;

    public Players(List<GameUser> users) {
        this.users = users;
    }

    public int size() {
        return users.size();
    }

    public void dealCards(Deck cards) {
        this.users.forEach(gamer -> gamer.pickCard(cards.popCard()));
    }

    public List<String> getPlayersNames(){
        return this.users.stream().map(GameUser::getName).collect(Collectors.toList());
    }

    public GameUser findUserByUsername(String username){
        return this.users.stream().filter(user -> user.getName().equals(username)).findAny().orElseThrow(
                () -> new IllegalArgumentException("해당하는 유저가 없다.")
        );
    }

    public List<Card> getCardsInHandByUsername(String username){
        GameUser user = this.findUserByUsername(username);
        return user.getCardsInHand();
    }

    public List<String> getAddablePlayersNames(){
        return this.users.stream().filter(user -> user.statusCheck().equals(CardSumStatus.UNDER) || user.statusCheck().equals(CardSumStatus.DEALER_MUST_PICK)).map(GameUser::getName).collect(Collectors.toList());
    }
}
