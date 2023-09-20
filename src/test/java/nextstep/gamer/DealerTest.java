package nextstep.gamer;

import nextstep.card.Card;
import nextstep.card.CardNumber;
import nextstep.card.CardShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
class DealerTest {
    static Dealer dealer;
    @BeforeEach
    void initDealer(){
        dealer = new Dealer();
    }

    @Test
    void dealerStatusCheck_PICK() {
        assertThat(dealer.statusCheck()).isEqualTo(CardSumStatus.DEALER_MUST_PICK);
    }

    @Test
    void dealerStatusCheck_STAY() {
        dealer.pickCard(new Card(CardShape.SPADE, CardNumber.J));
        dealer.pickCard(new Card(CardShape.SPADE, CardNumber.K));
        assertThat(dealer.statusCheck()).isEqualTo(CardSumStatus.DEALER_MUST_STAY);
    }

    @Test
    void dealerStatusCheck_BLACKJACK() {
        dealer.pickCard(new Card(CardShape.SPADE, CardNumber.ACE));
        dealer.pickCard(new Card(CardShape.SPADE, CardNumber.K));
        assertThat(dealer.statusCheck()).isEqualTo(CardSumStatus.BLACK_JACK);
    }
}
