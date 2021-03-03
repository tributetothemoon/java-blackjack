package blackjack.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> players) {
        this.players = players.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Map<User, ResultType> checkWinOrLose(int score) {
        return players.stream()
                .collect(Collectors.toMap(player -> player, player -> player.decisionResult(score)));
    }
}