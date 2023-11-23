package card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    @Test
    void getCard() {
        Deck deck = new Deck();
        Card card = deck.getCard();
        // 항상 ACE HEART (셔플 아직 안했기때문)
        assertThat(card.getCardInfo()).isEqualTo(Number.ACE.getName() + Shape.HEART.getName());
    }
}