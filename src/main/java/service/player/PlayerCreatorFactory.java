package service.player;

import service.CardDeck;
import service.io.UserInterface;
import util.RandomNameGenerator;
import domain.PlayerType;
import util.UserBasedNameProvider;

public class PlayerCreatorFactory {

    public static PlayerCreator getPlayerCreator(PlayerType type, CardDeck cardDeck, UserInterface userInterface) {
        return switch (type) {
            case AI -> new AiPlayerCreator(new RandomNameGenerator(), cardDeck, userInterface);
            case HUMAN -> new HumanPlayerCreator(new UserBasedNameProvider(userInterface), cardDeck, userInterface);
        };
    }
}
