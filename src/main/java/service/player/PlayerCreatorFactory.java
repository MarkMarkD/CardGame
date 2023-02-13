package service.player;

import service.io.UserInterface;
import util.RandomNameGenerator;
import domain.PlayerType;
import util.UserBasedNameProvider;

public class PlayerCreatorFactory {

    public static PlayerCreator getPlayerCreator(PlayerType type, UserInterface userInterface) {
        switch (type) {
            case AI:
                return new AiPlayerCreator(new RandomNameGenerator());
            case HUMAN:
                return new HumanPlayerCreator(new UserBasedNameProvider(userInterface));
            default:
                throw new IllegalArgumentException("Player type is not supported.");
        }
    }
}
