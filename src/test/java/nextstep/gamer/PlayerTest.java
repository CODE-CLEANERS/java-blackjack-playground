package nextstep.gamer;

import nextstep.card.Card;
import nextstep.card.CardNumber;
import nextstep.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    private static Player player;

    @BeforeEach
    void initPlayer(){
        player = new Player("DK");
    }
    @Test
    void gamePlayersSum() {
        assertThat(player.statusCheck()).isEqualTo(CardSumStatus.UNDER);
    }

    @Test
    void gamePlayerSum_BlACKJACK(){
        player.pickCard(new Card(CardShape.SPADE, CardNumber.ACE));
        player.pickCard(new Card(CardShape.SPADE, CardNumber.J));
        assertThat(player.statusCheck()).isEqualTo(CardSumStatus.BLACK_JACK);
    }

    @Test
    void gamePlayerSum_EXCEEDED(){
        player.pickCard(new Card(CardShape.SPADE, CardNumber.J));
        player.pickCard(new Card(CardShape.SPADE, CardNumber.Q));
        player.pickCard(new Card(CardShape.SPADE, CardNumber.K));
        assertThat(player.statusCheck()).isEqualTo(CardSumStatus.EXCEEDED);
    }
}
