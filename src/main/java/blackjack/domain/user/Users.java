package blackjack.domain.user;

import blackjack.domain.card.CardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Users {
    private static final int INITIAL_DRAW_CARD_NUMBER = 2;

    private final List<User> users = new ArrayList<>();

    private Users(Dealer dealer, Players players) {
        this.users.add(dealer);
        this.users.addAll(new ArrayList<>(players.players()));
    }

    public static Users of(Dealer dealer, Players players) {
        return new Users(dealer, players);
    }

    public void dealCards(CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_DRAW_CARD_NUMBER; i++) {
            this.users.forEach(user -> user.draw(cardDeck.drawCard()));
        }
    }

    public List<User> users() {
        return Collections.unmodifiableList(this.users);
    }
}
