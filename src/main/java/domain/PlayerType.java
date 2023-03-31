package domain;

public enum PlayerType {
    HUMAN("human"),
    AI("ai");

    private final String name;

    PlayerType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
