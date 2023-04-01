package service.player;

import service.io.UserInterface;
import util.RandomNameGenerator;
import domain.PlayerType;
import util.UserBasedNameProvider;

public class PlayerCreatorFactory {

    public static PlayerCreator getPlayerCreator(PlayerType type, UserInterface userInterface) {
        return switch (type) {
            case AI -> new AiPlayerCreator(new RandomNameGenerator());
            case HUMAN -> new HumanPlayerCreator(new UserBasedNameProvider(userInterface));
        };
    }
}
