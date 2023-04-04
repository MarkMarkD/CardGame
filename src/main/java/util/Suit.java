package util;

public enum Suit {
    DIAMONDS ("diamonds"),
    HEARTS ("hearts"),
    CLUBS ("clubs"),
    SPADES ("spades");

    private final String title;

    Suit(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
