package blackjack.domain.card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardsTest {
    private Cards cards;
    private Card king;
    private Card queen;
    private Card ace;
    private Card two;

    @BeforeEach
    @Test
    void setUp() {
        cards = new Cards();
        king = new Card(Suit.CLUB, CardNumber.KING);
        queen = new Card(Suit.CLUB, CardNumber.QUEEN);
        ace = new Card(Suit.CLUB, CardNumber.ACE);
        two = new Card(Suit.CLUB, CardNumber.TWO);
    }

    @DisplayName("가진 패의 점수를 계산할 수 있다.")
    @Test
    void getScoreTest() {
        cards.hit(king);
        cards.hit(two);
        assertThat(cards.getScore()).isEqualTo(12);
    }

    @DisplayName("Ace가 섞여 있을 때 최선의 점수를 계산할 수 있다.")
    @Test
    void getMaximunScoreTest() {
        cards.hit(ace);
        cards.hit(two);
        assertThat(cards.getScore()).isEqualTo(13);

        cards.hit(king);
        assertThat(cards.getScore()).isEqualTo(13);
    }

    @DisplayName("블랙잭 여부를 판별할 수 있다.")
    @Test
    void isBlackJackTest() {
        cards.hit(king);
        cards.hit(ace);
        assertTrue(cards.isBlackJack());
    }

    @DisplayName("버스트 여부를 판별할 수 있다.")
    @Test
    void isBustTest() {
        cards.hit(king);
        cards.hit(queen);
        cards.hit(two);
        assertTrue(cards.isBust());
    }
}
