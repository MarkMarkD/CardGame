package validator;

import exception.PlayerNumberException;

public class PlayerNumberValidator implements PlayerValidator {

    @Override
    public void validate(int players) {
        if (players < 2 || players > 6)
            throw new PlayerNumberException();
    }
}
