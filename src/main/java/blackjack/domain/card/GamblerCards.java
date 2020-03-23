package blackjack.domain.card;

import blackjack.domain.result.CardsResult;
import blackjack.util.NullChecker;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class GamblerCards {

    private static final String DUPLICATE_CARD_EXCEPTION_MESSAGE = "카드가 중복되었습니다.";
    private static final String NO_CARD_EXCEPTION_MESSAGE = "카드가 없습니다.";

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        validateCard(card);
        cards.add(card);
    }

    private void validateCard(Card card) {
        NullChecker.validateNotNull(card);
        if (cards.contains(card)) {
            throw new IllegalArgumentException(DUPLICATE_CARD_EXCEPTION_MESSAGE);
        }
    }

    public CardsResult getResult() {
        int score = cards.stream()
            .mapToInt(Card::getValue)
            .sum();
        return new CardsResult(score, hasAce(), cards.size());
    }

    public boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public List<String> getInfos() {
        validationCards();
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.toList());
    }

    private void validationCards() {
        if (cards.isEmpty()) {
            throw new NullPointerException(NO_CARD_EXCEPTION_MESSAGE);
        }
    }
}