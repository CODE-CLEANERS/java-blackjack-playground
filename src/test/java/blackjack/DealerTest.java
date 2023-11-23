package blackjack;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {

    @Test
    void 생성() {
        // given
        Dealer dealer = new Dealer();

        // when
        String name = dealer.getName();

        // then
        assertThat(name).isEqualTo("딜러");
    }

    @Test
    void 플레이_16이하() {
        // given
        Dealer dealer = new Dealer();
        dealer.addHand(new Card(Rank.KING, Suit.HEART));

        // when
        boolean isPlay = dealer.isPlay();

        // then
        assertThat(isPlay).isTrue();
    }

    @Test
    void 플레이_17이상() {
        // given
        Dealer dealer = new Dealer();
        dealer.addHand(new Card(Rank.KING, Suit.HEART));
        dealer.addHand(new Card(Rank.SEVEN, Suit.HEART));

        // when
        boolean isPlay = dealer.isPlay();

        // then
        assertThat(isPlay).isFalse();
    }
}
