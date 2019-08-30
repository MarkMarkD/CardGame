package domain;

import util.Suit;

import java.util.Objects;

/**
 * A card. This is an immutable class and cannot be changed after creation.
 */

public final class Card {
    private final Suit suit;
    private final Integer rank;

    private final int hashCode;  // if class is immutable, we can store hashCode in a variable instead of calculating it each time

    public Card(int rank, Suit suit) throws IllegalAccessException {
        this.suit = suit;
        if (rank < 6 || rank > 14)
            throw new IllegalAccessException("Value is out of range for a card rank");
        this.rank = rank;
        hashCode = Objects.hash(suit, rank);
    }

    public Suit getSuit() {
        return Suit.valueOf(suit.name());
    }

    public Integer getRank() {
        return new Integer(rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                Objects.equals(rank, card.rank);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        if (rank.equals(11))
            return ("jack of " + suit);
        else if (rank.equals(12))
            return ("queen of " + suit);
        else if (rank.equals(13))
            return ("king of " + suit);
        else if (rank.equals(14))
            return ("ace of " + suit);
        else return (rank + " of " + suit);
    }
}
