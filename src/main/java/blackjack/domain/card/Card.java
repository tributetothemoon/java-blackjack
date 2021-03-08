package blackjack.domain.card;

public class Card {
    private final Suit suit;
    private final CardNumber cardNumber;

    public Card(Suit suit, CardNumber cardNumber) {
        this.suit = suit;
        this.cardNumber = cardNumber;
    }

    public int getScore() {
        return cardNumber.getScore();
    }

    @Override
    public String toString() {
        return this.cardNumber.getScore() + this.suit.getName();
    }
}
