package blackjack;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Suit;
import blackjack.player.Gambler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class GamblerTest {

    @Test
    void 생성() {
        // given
        Gambler gambler = new Gambler("pobi");

        // when
        String name = gambler.getName();

        // then
        assertThat(name).isEqualTo("pobi");
    }

    @Test
    void 손_패_추가() {
        // given
        Gambler gambler = new Gambler("tester");

        // when
        gambler.addCardToHand(new Card(Rank.ACE, Suit.CLUB));

        // then
        assertThat(gambler.getHand().size()).isOne();
        assertThat(gambler.getHand().get(0)).isEqualTo(new Card(Rank.ACE, Suit.CLUB));
    }

    @ParameterizedTest
    @CsvSource(value = {"CLUB_KING,HEART_KING,DIAMOND_TWO:22", "CLUB_KING,HEART_KING:20"}, delimiter = ':')
    void 정산(String input, int expect) {
        // given
        Gambler gambler = new Gambler("tester");
        addHandFromInput(input, gambler);

        // when
        int result = gambler.getHand().calculateHand();

        // then
        assertThat(result).isEqualTo(expect);
    }

    @ParameterizedTest
    @ValueSource(strings = {"CLUB_KING,HEART_KING,DIAMOND_ACE", "CLUB_KING,DIAMOND_ACE"})
    void 정산_에이스(String input) {
        // given
        Gambler gambler = new Gambler("tester");
        addHandFromInput(input, gambler);

        // when
        int result = gambler.getHand().calculateHand();

        // then
        assertThat(result).isEqualTo(21);
    }

    @ParameterizedTest
    @CsvSource(value = {"CLUB_KING,HEART_KING,DIAMOND_TWO:true", "CLUB_KING,HEART_KING:false"}, delimiter = ':')
    void 버스트_여부(String input, boolean expect) {
        // given
        Gambler gambler = new Gambler("tester");
        addHandFromInput(input, gambler);

        // when
        boolean isBust = gambler.getHand().isBust();

        // then
        assertThat(isBust).isEqualTo(expect);
    }

    @Test
    void 블랙잭() {
        // given
        Gambler gambler = new Gambler("tester");
        gambler.addCardToHand(new Card(Rank.KING, Suit.CLUB));
        gambler.addCardToHand(new Card(Rank.ACE, Suit.DIAMOND));

        // when
        int result = gambler.getHand().calculateHand();

        // then
        assertThat(result).isEqualTo(21);
        assertThat(gambler.getHand().isBlackJack()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"CLUB_KING,HEART_SEVEN,DIAMOND_FIVE", "CLUB_KING,HEART_SEVEN", "CLUB_KING,HEART_KING,CLUB_ACE"})
    void 블랙잭_아님(String input) {
        // given
        Gambler gambler = new Gambler("tester");
        addHandFromInput(input, gambler);

        // when
        boolean isBlackJack = gambler.getHand().isBlackJack();

        // then
        assertThat(isBlackJack).isFalse();
    }

    private static void addHandFromInput(String input, Gambler gambler) {
        String[] cardNames = input.split(",");
        for (String cardName : cardNames) {
            String[] suitAndRank = cardName.split("_");
            Card card = new Card(Rank.valueOf(suitAndRank[1]), Suit.valueOf(suitAndRank[0]));
            gambler.addCardToHand(card);
        }
    }
}
