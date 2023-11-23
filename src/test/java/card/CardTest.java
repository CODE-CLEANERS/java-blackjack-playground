package card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {
    @Test
    void initTest() {
        Card card = new Card(Shape.DIAMOND, Number.ACE);
        assertThat(card.getCardInfo()).isEqualTo(Number.ACE.getName() + Shape.DIAMOND.getName());
    }
}