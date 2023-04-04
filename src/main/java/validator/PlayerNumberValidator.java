package validator;

@FunctionalInterface
public interface PlayerNumberValidator extends Validator<Integer>{

    int MIN_PLAYERS = 2;
    int MAX_PLAYERS = 6;
    String MIN_PLAYERS_MSG = String.format("Number of players can't be less than %s", MIN_PLAYERS);
    String MAX_PLAYERS_MSG = String.format("Number of players can't exceed %s", MAX_PLAYERS);

    void validate(Integer players);
}
