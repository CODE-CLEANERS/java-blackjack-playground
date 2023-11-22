package blackjack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void 생성() {
        // given
        Player player = new Player("pobi");

        // when
        String name = player.getName();

        // then
        assertThat(name).isEqualTo("pobi");
    }

    @Test
    void 손_패_추가() {
        // given
        Player player = new Player("tester");

        // when
        player.addHand(Card.CLOVER_ACE);

        // then
        assertThat(player.getHands().size()).isOne();
        assertThat(player.getHands()).contains(Card.CLOVER_ACE);
    }

    @ParameterizedTest
    @CsvSource(value = {"CLOVER_KING,HEART_KING,DIAMOND_2:22", "CLOVER_KING,HEART_KING:20"}, delimiter = ':')
    void 정산(String input, int expect) {
        // given
        Player player = new Player("tester");
        addHandFromInput(input, player);

        // when
        int result = player.calculateHands();

        // then
        assertThat(result).isEqualTo(expect);
    }

    @ParameterizedTest
    @ValueSource(strings = {"CLOVER_KING,HEART_KING,DIAMOND_ACE", "CLOVER_KING,DIAMOND_ACE"})
    void 정산_에이스(String input) {
        // given
        Player player = new Player("tester");
        addHandFromInput(input, player);

        // when
        int result = player.calculateHands();

        // then
        assertThat(result).isEqualTo(21);
    }

    @ParameterizedTest
    @CsvSource(value = {"CLOVER_KING,HEART_KING,DIAMOND_2:true", "CLOVER_KING,HEART_KING:false"}, delimiter = ':')
    void 버스트_여부(String input, boolean expect) {
        // given
        Player player = new Player("tester");
        addHandFromInput(input, player);

        // when
        boolean isBust = player.isBust();

        // then
        assertThat(isBust).isEqualTo(expect);
    }

    @Test
    void 블랙잭() {
        // given
        Player player = new Player("tester");
        player.addHand(Card.CLOVER_KING);
        player.addHand(Card.DIAMOND_ACE);

        // when
        int result = player.calculateHands();

        // then
        assertThat(result).isEqualTo(21);
        assertThat(player.isBlackJack()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"CLOVER_KING,HEART_7,DIAMOND_5", "CLOVER_KING,HEART_7", "CLOVER_KING,HEART_KING,CLOVER_ACE"})
    void 블랙잭_아님(String input) {
        // given
        Player player = new Player("tester");
        addHandFromInput(input, player);

        // when
        boolean isBlackJack = player.isBlackJack();

        // then
        assertThat(isBlackJack).isFalse();
    }

    private static void addHandFromInput(String input, Player player) {
        String[] cards = input.split(",");
        for (String card : cards) {
            player.addHand(Card.valueOf(card));
        }
    }
}
