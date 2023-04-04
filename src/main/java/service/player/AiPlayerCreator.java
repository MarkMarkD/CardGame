package service.player;

import domain.AiPlayer;
import domain.Player;
import service.CardDeck;
import service.io.UserInterface;
import util.NameProvider;

public class AiPlayerCreator extends AbstractPlayerCreator {

    public AiPlayerCreator(NameProvider nameProvider, CardDeck cardDeck, UserInterface userInterface) {
        super(nameProvider, cardDeck, userInterface);
    }

    @Override
    public Player create(String name, CardDeck cardDeck, UserInterface userInterface) {
        return new AiPlayer(name, cardDeck, userInterface);
    }
}
