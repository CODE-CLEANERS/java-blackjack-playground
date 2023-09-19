package nextstep;

import nextstep.card.Deck;
import nextstep.gamer.Gamer;
import nextstep.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackControllerTest {
    // 컨트롤러가 해야 할 일

    /**
     * 1. 카드를 만들고 섞는다.
     * 2. 플레이어에게 나눠준다 (딜러 포함) -> 2장씩
     */
    private Deck cards;
    private BlackjackController controller;
    @BeforeEach
    void init(){
        cards = new Deck();
        controller = new BlackjackController(cards);
        Gamer gamer = new Player("DK");
        Gamer gamer2 = new Player("DK2");
        controller.enrollPlayer(Arrays.asList(gamer2, gamer));
    }
    @Test
    void generateCardTest() {
        controller.createDeck();
        controller.popCard();
        assertThat(cards.size()).isEqualTo(47);
        controller.popCard();
        assertThat(cards.size()).isEqualTo(46);
    }

    @Test
    @DisplayName("플레이어 등록")
    void enroll_player() {
        Gamer gamer = new Player("DK");
        Gamer gamer2 = new Player("DK2");
        controller.enrollPlayer(Arrays.asList(gamer2, gamer));

        assertThat(controller.totalPlayerSize()).isEqualTo(5);
    }

    @Test
    @DisplayName("패 돌려")
    void distribute_card(){
        controller.dealCards();
        assertThat(cards.size()).isEqualTo(45);
    }

    @Test
    @DisplayName("패 돌려")
    void player_sum(){
        controller.dealCards();
        assertThat(cards.size()).isEqualTo(45);
    }
}