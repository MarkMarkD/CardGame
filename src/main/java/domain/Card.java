package domain;

import util.Rank;
import util.Suit;

import java.util.Objects;

/**
 * A card. This class is immutable and cannot be changed after creation.
 */

public final class Card {
    private final Suit suit;
    private final Rank rank;

    // if class is immutable, we can store hashCode in a variable instead of calculating it each time
    private final int hashCode;

    public Card(Rank rank, Suit suit) {
        this.suit = suit;
        this.rank = rank;
        hashCode = Objects.hash(suit, rank);
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return suit == card.suit &&
                rank == card.rank;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return (rank + " of " + suit);
    }
}
