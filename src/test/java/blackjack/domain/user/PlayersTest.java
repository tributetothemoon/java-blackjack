package blackjack.domain.user;

import blackjack.domain.ResultType;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    private Players players;
    private Player player1;
    private Player player2;
    private Dealer dealer;
    private Card jack = new Card(Suit.CLUB, CardNumber.JACK);
    private Card ace = new Card(Suit.CLUB, CardNumber.ACE);
    private Card seven = new Card(Suit.CLUB, CardNumber.SEVEN);
    private Card six = new Card(Suit.CLUB, CardNumber.SIX);

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        players = Players.of(Arrays.asList("youngE", "kimkim"));
        player1 = players.players().get(0);
        player2 = players.players().get(1);
        player1.addCard(ace);
        player1.addCard(jack);
        // 플레이어 youngE에게 블랙잭을 준다.
        player2.addCard(jack);
        player2.addCard(six);
        // 플레이어 Kimkim에게 16을 준다.
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러가 블랙잭일 때")
    @Test
    void checkWinOrLoseWhenDealerHasBlackJackTest() {
        dealer.addCard(ace);
        dealer.addCard(jack);
        Map<User, ResultType> resultMap = players.generateResultsMapAgainstDealer(dealer);
        assertThat(resultMap).isEqualTo(new HashMap<User, ResultType>() {
            {
                put(player1, ResultType.DRAW);
                put(player2, ResultType.LOSE);
            }
        });
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러가 블랙잭이 아니었을 때")
    @Test
    void checkWinOrLoseWhenDealerHasNotBlackJackTest() {
        dealer.addCard(ace);
        dealer.addCard(seven);
        Map<User, ResultType> resultMap = players.generateResultsMapAgainstDealer(dealer);
        assertThat(resultMap).isEqualTo(new HashMap<User, ResultType>() {
            {
                put(player1, ResultType.WIN);
                put(player2, ResultType.LOSE);
            }
        });
    }

    @DisplayName("딜러와 각 플레이어 간의 승패를 가린다. - 딜러와 플레이어가 버스트일 때")
    @Test
    void checkWinOrLoseTest() {
        dealer.addCard(six);
        dealer.addCard(seven);
        dealer.addCard(jack);
        player2.addCard(seven); // player2 에게 7을 추가로 주어 23을 만들어 버스트 상태로 만든다.
        Map<User, ResultType> resultMap = players.generateResultsMapAgainstDealer(dealer);
        assertThat(resultMap).isEqualTo(new HashMap<User, ResultType>() {
            {
                put(player1, ResultType.WIN);
                put(player2, ResultType.LOSE);
            }
        });
    }

}
