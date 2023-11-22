package blackjack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    void 배팅() {
        // given
        Player player = new Player("tester");
        int batAmount = 10000;

        // when
        player.bat(batAmount);

        // then
        assertThat(player.getBatAmount()).isEqualTo(batAmount);
    }

    @Test
    void 딜링() {
        // given
        Player player = new Player("tester");

        // when
        player.addHand(Card.CLOVER_ACE);

        // then
        assertThat(player.getHands().size()).isOne();
        assertThat(player.getHands()).contains(Card.CLOVER_ACE);
    }

    @Test
    void 히트() {
        // given
        Player player = new Player("tester");
        player.addHand(Card.CLOVER_KING);
        player.addHand(Card.HEART_KING);

        // when
        player.hit(Card.SPADE_KING);

        // then
        assertThat(player.getHands().size()).isEqualTo(3);
        assertThat(player.getHands()).contains(Card.CLOVER_KING, Card.HEART_KING, Card.SPADE_KING);
    }

    @ParameterizedTest
    @CsvSource(value = {"CLOVER_KING,HEART_KING,DIAMOND_2:22", "CLOVER_KING,HEART_KING:20"}, delimiter = ':')
    void 정산(String input, int expect) {
        // given
        Player player = new Player("tester");
        addHandFromInput(input, player);

        // when
        int result = player.calculate();

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
        int result = player.calculate();

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
        player.addHand(Card.HEART_7);
        player.addHand(Card.DIAMOND_4);

        // when
        int result = player.calculate();

        // then
        assertThat(result).isEqualTo(21);
        assertThat(player.isBlackJack()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"CLOVER_KING,HEART_7,DIAMOND_5", "CLOVER_KING,HEART_7"})
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
