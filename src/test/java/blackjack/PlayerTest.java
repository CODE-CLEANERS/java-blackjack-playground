package blackjack;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Suit;
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
        player.addHand(new Card(Rank.ACE, Suit.CLUB));

        // then
        assertThat(player.getHands().size()).isOne();
        assertThat(player.getHands()).contains(new Card(Rank.ACE, Suit.CLUB));
    }

    @ParameterizedTest
    @CsvSource(value = {"CLUB_KING,HEART_KING,DIAMOND_TWO:22", "CLUB_KING,HEART_KING:20"}, delimiter = ':')
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
    @ValueSource(strings = {"CLUB_KING,HEART_KING,DIAMOND_ACE", "CLUB_KING,DIAMOND_ACE"})
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
    @CsvSource(value = {"CLUB_KING,HEART_KING,DIAMOND_TWO:true", "CLUB_KING,HEART_KING:false"}, delimiter = ':')
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
        player.addHand(new Card(Rank.KING, Suit.CLUB));
        player.addHand(new Card(Rank.ACE, Suit.DIAMOND));

        // when
        int result = player.calculateHands();

        // then
        assertThat(result).isEqualTo(21);
        assertThat(player.isBlackJack()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"CLUB_KING,HEART_SEVEN,DIAMOND_FIVE", "CLUB_KING,HEART_SEVEN", "CLUB_KING,HEART_KING,CLUB_ACE"})
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
        String[] cardNames = input.split(",");
        for (String cardName : cardNames) {
            String[] suitAndRank = cardName.split("_");
            Card card = new Card(Rank.valueOf(suitAndRank[1]), Suit.valueOf(suitAndRank[0]));
            player.addHand(card);
        }
    }
}
