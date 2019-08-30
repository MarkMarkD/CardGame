package util;

public enum Suit {
    DIAMONDS ("Diamonds"),
    HEARTS ("Hearts"),
    CLUBS ("Clubs"),
    SPADES ("Spades");

    private String title;

    Suit(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
