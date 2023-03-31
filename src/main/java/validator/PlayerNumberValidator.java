package validator;

import exception.PlayerNumberException;

//todo delete class
public class PlayerNumberValidator implements PlayerValidator {

    @Override
    public void validate(int players) {
        if (players < 2) {
            throw new PlayerNumberException(MIN_PLAYERS_MSG);
        }

        if (players > 6) {
            throw new PlayerNumberException(MAX_PLAYERS_MSG);
        }
    }
}
