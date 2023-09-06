package nextstep.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    static Card card;
    static Card card2;
    @BeforeEach
    void initTwoCard(){
       card = new Card(CardShape.SPADE, CardNumber.ACE);
       card2 = new Card(CardShape.SPADE, CardNumber.ACE);
    }
    @Test
    void cardCreateTest() {
        assertThat(card).isEqualTo(card2);
    }

    @Test
    void cardNumberTest() {
//        assertThat(card.getCardNumber().getNumericValue()).isEqualTo(1); // Deprecated
        assertThat(card.getNumericValue()).isEqualTo(1);
    }
}
