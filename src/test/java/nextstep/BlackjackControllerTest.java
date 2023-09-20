package nextstep;

import nextstep.card.Deck;
import nextstep.gamer.Dealer;
import nextstep.gamer.GameUser;
import nextstep.gamer.Player;
import nextstep.gamer.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackjackControllerTest {

    /**
     * 1. 카드를 만들고 섞는다.
     * 2. 플레이어에게 나눠준다 (딜러 포함) -> 2장씩
     */
    private Deck cards;
    private BlackjackController controller;
    @BeforeEach
    void init(){
        cards = new Deck();
        GameUser gameUser = new Player("DK");
        GameUser gameUser2 = new Player("DK2");
        Dealer dealer = new Dealer();
        Players players = new Players(Arrays.asList(gameUser, gameUser2, dealer));
        controller = new BlackjackController(cards, players);
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
    @Deprecated
    void enroll_player() {
        GameUser gameUser = new Player("DK");
        GameUser gameUser2 = new Player("DK2");
        // 불변 컬렉션(일급 컬렉션) 으로 변경
        assertThat(controller.totalPlayerSize()).isEqualTo(3);
    }

    @Test
    @DisplayName("패 돌려")
    void distribute_card(){
        controller.dealCards();
        assertThat(cards.size()).isEqualTo(45);
    }

    @Test
    @DisplayName("유저마다 패 보여주는 로직")
    void player_sum(){
        controller.dealCards();

        assertThat(cards.size()).isEqualTo(45);
    }
}