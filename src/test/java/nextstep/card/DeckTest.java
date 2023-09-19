package nextstep.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeckTest {
    static Deck deck;
    @BeforeEach
    void initCards(){
        deck = new Deck();
    }

    @Test
    void createCardsTest() {
        deck.createCardDeck();
        Card card = deck.popCard();
        Card card1 = deck.popCard();
        assertThat(card).isEqualTo(new Card(CardShape.CLOVER, CardNumber.K));
        assertThat(card.equals(new Card(CardShape.CLOVER, CardNumber.K))
                && card1.equals(new Card(CardShape.CLOVER, CardNumber.Q)))
                .isTrue();
    }

    @Test
    void getEnumByCreatedCard() { // 아주 극히 작은 확률로 실패함 (1/50^2)
        deck.shuffleDeck();
        Card card = deck.popCard();
        Card card1 = deck.popCard();
        assertThat(card.equals(new Card(CardShape.CLOVER, CardNumber.K))
                && card1.equals(new Card(CardShape.CLOVER, CardNumber.Q)))
                .isFalse();
    }
}
