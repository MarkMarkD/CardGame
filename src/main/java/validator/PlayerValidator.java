package validator;

@FunctionalInterface
public interface PlayerValidator {

    int MIN = 2;
    int MAX = 6;
    String MIN_PLAYERS_MSG = String.format("Number of players can't be less than %s", MIN);
    String MAX_PLAYERS_MSG = String.format("Number of players can't exceed %s", MAX);

    void validate(int players);
}
