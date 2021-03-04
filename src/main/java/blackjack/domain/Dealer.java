package blackjack.domain;

import blackjack.domain.card.Card;

public class Dealer extends User {
    private static final int DEALER_REDRAW_STANDARD = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super();
        this.name = DEALER_NAME;
    }

    public boolean hasToDrawACard() {
        return this.getScore() < DEALER_REDRAW_STANDARD && this.getScore() != Card.BUST;
    }

    @Override
    public void hit(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isStay() {
        return getScore() > 16;
    }
}
