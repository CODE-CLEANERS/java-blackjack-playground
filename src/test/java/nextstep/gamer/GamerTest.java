package nextstep.gamer;

import java.util.*;
import nextstep.card.Card;
import nextstep.card.CardNumber;
import nextstep.card.CardShape;
import nextstep.card.Deck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GamerTest {
    static Deck deck;
    static Gamer gamer;
    static Dealer dealer;
    static final String NAME = "DK";
    static final Integer VALANCE = 100000;
    static final Integer THIS_GAME_COST = 10000;
    @BeforeEach
    void createGamer() {
        gamer = new Player(NAME);
        cards = new Cards();
    }

    @Test
    void getName() {
        assertThat(gamer.getName()).isEqualTo(NAME);
    }

    @Test
    void valanceTest(){
        assertThat(gamer.getValance()).isEqualTo(VALANCE);
        gamer.offerStake(THIS_GAME_COST);
        assertThat(gamer.getValance()).isEqualTo(VALANCE - THIS_GAME_COST);
    }

    @Test
    void pickCardTest(){
        Card card = deck.popCard();
        gamer.pickCard(card);
        assertThat(gamer.getCardSum()).isEqualTo(10);
    }

    @Test
    void checkSum(){
        Card card = deck.popCard();
        Card card1 = deck.popCard();
        Card card2 = deck.popCard(); // K, Q, J 순서로 뽑힘
        gamer.pickCard(List.of(card2, card1, card));
        assertThat(gamer.isSumOverMax()).isTrue();
    }

    @Test
    @DisplayName("유저가 ACE를 가지고, 총합이 21이라서 1로 계산된다.")
    void userSum_Equals_21_AND_ACE_IS_1() {
        Card card = deck.popCard();
        Card card1 = deck.popCard();
        Card card2 = new Card(CardShape.SPADE, CardNumber.ACE); // == 21
        gamer.pickCard(List.of(card2, card1, card));
        assertThat(gamer.getCardSum()).isEqualTo(21);
    }

    @Test
    @DisplayName("유저가 ACE를 가지고, 총합이 21이라서 11로 계산된다")
    void userSum_Equals_21_AND_ACE_IS_11() {
        Card card = deck.popCard();
        Card card2 = new Card(CardShape.SPADE, CardNumber.ACE); // == 21
        gamer.pickCard(List.of(card2, card));
        assertThat(gamer.getCardSum()).isEqualTo(21);
    }

    @Test
    @DisplayName("유저가 ACE를 가지고, 총합이 25가 넘어서 1로 계산된다")
    void userSum_Above_21_AND_ACE_IS_1() {
        Card card = deck.popCard();
        Card card1 = deck.popCard();
        Card card2 = new Card(CardShape.SPADE, CardNumber.ACE);
        Card card3 = new Card(CardShape.SPADE, CardNumber.FIVE); // 이미 총합 25
        gamer.pickCard(List.of(card2, card, card1, card3));
        assertThat(gamer.getCardSum()).isEqualTo(26);
    }

    @Test
    @DisplayName("유저가 ACE를 가지고, ACE가 11로 계산되면 21이 넘어서 1로 계산된다.")
    void userSum_ABOUT_TO_ABOVE_21_AND_ACE_IS_1() {
        Card card = new Card(CardShape.SPADE, CardNumber.SIX);
        Card card3 = new Card(CardShape.SPADE, CardNumber.FIVE); // 이미 11, ACE가 11 이라면 초과해버림 (21을)
        Card card2 = new Card(CardShape.SPADE, CardNumber.ACE);
        gamer.pickCard(List.of(card2, card, card3));
        assertThat(gamer.getCardSum()).isEqualTo(12);
    }

    @Test
    @DisplayName("유저가 ACE를 여러 개 가진다 PLAN1 - ACE3개")
    void user_HAS_SEVERAL_ACE_allACE() {
        Card card = new Card(CardShape.SPADE, CardNumber.ACE);
        Card card3 = new Card(CardShape.SPADE, CardNumber.ACE); // 이미 11, ACE가 11 이라면 초과해버림 (21을)
        Card card2 = new Card(CardShape.SPADE, CardNumber.ACE);
        gamer.pickCard(List.of(card2, card, card3));
        assertThat(gamer.getCardSum()).isEqualTo(13);
    }

    @Test
    @DisplayName("유저가 ACE를 여러 개 가진다 PLAN2 - ACE2개")
    void user_HAS_SEVERAL_TWO_ACE() {
        Card card = new Card(CardShape.SPADE, CardNumber.EIGHT);
        Card card3 = new Card(CardShape.SPADE, CardNumber.ACE); // 이미 11, ACE가 11 이라면 초과해버림 (21을)
        Card card2 = new Card(CardShape.SPADE, CardNumber.ACE);
        gamer.pickCard(List.of(card2, card, card3));
        assertThat(gamer.getCardSum()).isEqualTo(20);
    }
}
