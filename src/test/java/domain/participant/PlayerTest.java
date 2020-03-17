package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Shape;
import domain.card.Symbol;
import domain.result.Result;

class PlayerTest {
    @Test
    @DisplayName("유저 이름 입력이 공란일 때 예외를 잘 뱉어내는지")
    void inputBlankName() {
        String blankName = "";
        assertThatThrownBy(() ->
            new Player(blankName)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유저 이름 입력이 null일 때 예외를 잘 뱉어내는지")
    void inputNullName() {
        assertThatThrownBy(() ->
            new Player(null)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어가 버스트일 때 결과값 LOSE를 뱉어내는지")
    void isPlayerBust() {
        Player player = new Player("플레이어");
        Dealer dealer = new Dealer();
        player.receive(new Card(Symbol.SEVEN, Shape.다이아몬드));
        player.receive(new Card(Symbol.EIGHT, Shape.스페이드));
        player.receive(new Card(Symbol.EIGHT, Shape.하트));
        dealer.receive(new Card(Symbol.FOUR, Shape.스페이드));
        assertThat(player.beatDealer(dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("플레이어가 점수가 높을 때 결과값 WIN을 뱉어내는지")
    void isPlayerWin() {
        Player player = new Player("플레이어");
        Dealer dealer = new Dealer();
        player.receive(new Card(Symbol.SEVEN, Shape.다이아몬드));
        player.receive(new Card(Symbol.EIGHT, Shape.하트));
        dealer.receive(new Card(Symbol.FOUR, Shape.스페이드));
        assertThat(player.beatDealer(dealer)).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 점수가 같을 때 결과값 DRAW를 뱉어내는지")
    void isPlayerDraw() {
        Player player = new Player("플레이어");
        Dealer dealer = new Dealer();
        player.receive(new Card(Symbol.SEVEN, Shape.다이아몬드));
        dealer.receive(new Card(Symbol.SEVEN, Shape.스페이드));
        assertThat(player.beatDealer(dealer)).isEqualTo(Result.DRAW);
    }

}