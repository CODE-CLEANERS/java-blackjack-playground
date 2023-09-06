package nextstep.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardsTest {
    static Cards cards;
    @BeforeEach
    void initCards(){
        cards = new Cards();
    }

    @Test
    void createCardsTest() {
        cards.createCardDeck();
        Card card = cards.popCard();
        Card card1 = cards.popCard();
        assertThat(card).isEqualTo(new Card(CardShape.CLOVER, CardNumber.K));
        assertThat(card.equals(new Card(CardShape.CLOVER, CardNumber.K))
                && card1.equals(new Card(CardShape.CLOVER, CardNumber.Q)))
                .isTrue();
    }

    @Test
    void getEnumByCreatedCard() { // 아주 극히 작은 확률로 실패함 (1/50^2)
        cards.shuffleCards();
        Card card = cards.popCard();
        Card card1 = cards.popCard();
        assertThat(card.equals(new Card(CardShape.CLOVER, CardNumber.K))
                && card1.equals(new Card(CardShape.CLOVER, CardNumber.Q)))
                .isFalse();
    }
}
