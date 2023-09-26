package nextstep;

import nextstep.card.Deck;
import nextstep.gamer.Dealer;
import nextstep.gamer.GameUser;
import nextstep.gamer.Player;
import nextstep.gamer.Players;
import nextstep.view.InputView;
import nextstep.view.OutputView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        controller = new BlackjackController(cards, players, inputView, outputView);
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
        controller.dealCards(1);
        assertThat(cards.size()).isEqualTo(45);
    }

    @Test
    @Deprecated
    @DisplayName("유저마다 패 보여주는 로직 - 출력으로만 제어할듯")
    void player_sum(){
        // 유저들에게이미 카드가 돌아가 있는 상황, 따라서 유저들은 뭔 카드를 가지고 있는지만 보여주면 됨.
        controller.dealCards(1);
        // 유저의 이름과, 가지고 있는 패를 단순히 찍어주기만 (sout) 하면 됨...
    }
}