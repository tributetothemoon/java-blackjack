package blackjack.domain.result;

import blackjack.domain.Money;
import blackjack.domain.ProfitTable;
import blackjack.domain.ResultType;
import blackjack.domain.user.Player;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.*;

public class Results {
    private static final long TO_NEGATIVE_VALUE = -1;
    private static final int INITIALIZE_VALUE = 0;

    private final Map<Player, ResultType> matchResults;
    private final Map<Player, Double> profits;

    public Results(Map<Player, ResultType> matchResults, Map<Player, Double> profits) {
        this.matchResults = matchResults;
        this.profits = profits;
    }

    public static Results of(Map<Player, ResultType> matchResults) {
        Map<Player, Double> profitMap = matchResults.keySet().stream().parallel()
                .collect(toMap(
                        player -> player,
                        player -> ProfitTable.translateBettingMoney(matchResults.get(player), player.getBettingMoney())));
        return new Results(matchResults, profitMap);
    }

    public ResultType getResultOf(Player player) {
        return matchResults.get(player);
    }

    public double getProfitOf(Player player) {
        return profits.get(player);
    }

    public DealerResult generateDealerResult() {
        HashMap<ResultType, Integer> dealerMatchResult = new HashMap<>();
        for (ResultType resultType: ResultType.values()) {
            dealerMatchResult.merge(resultType.reverse(), this.count(resultType), Integer::sum);
        }
        return new DealerResult(dealerMatchResult, this.getTotalProfit() * TO_NEGATIVE_VALUE);
    }

    private double getTotalProfit() {
        return profits.values().stream().mapToDouble(profit -> profit)
                .sum();
    }

    private int count(ResultType resultType) {
        return (int) this.matchResults.values().stream().parallel()
                .filter(resultType::equals)
                .count();
    }

    public Set<Player> playerSet() {
        return matchResults.keySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Results results1 = (Results) o;
        return Objects.equals(matchResults, results1.matchResults) && Objects.equals(profits, results1.profits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchResults, profits);
    }
}
