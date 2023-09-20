package nextstep.gamer;

import nextstep.card.Card;
import nextstep.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayersTest {
    private Players players;
    @BeforeEach
    void init() {
        GameUser gameUser = new Player("DK");
        GameUser gameUser2 = new Player("DK2");
        Dealer dealer = new Dealer();
        players = new Players(List.of(gameUser, gameUser2, dealer));
    }

    @Test
    void findUserByUsername() {
        GameUser userByUsername = players.findUserByUsername("DK");
        assertThat(userByUsername.getName()).isEqualTo("DK");
        assertThatThrownBy(() -> players.findUserByUsername("Foo"));
    }

    @Test
    void getCardsInHandByUsername() {
        List<Card> cards = players.getCardsInHandByUsername("DK");
        assertEquals(0, cards.size());
        players.dealCards(new Deck());
        assertThat(cards.size()).isOne();
    }


}