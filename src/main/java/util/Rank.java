package util;

public enum Rank {
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("jack"),
    QUEEN("queen"),
    KING("king"),
    ACE("ace");

    private final String name;

    Rank (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
