package service.player;

import domain.HumanPlayer;
import domain.Player;
import service.CardDeck;
import service.io.UserInterface;
import util.NameProvider;

public class HumanPlayerCreator extends AbstractPlayerCreator {

    public HumanPlayerCreator(NameProvider nameProvider, CardDeck cardDeck, UserInterface userInterface) {
       super(nameProvider, cardDeck, userInterface);
    }

    @Override
    public Player create(String name, CardDeck cardDeck, UserInterface userInterface) {
        return new HumanPlayer(name, cardDeck, userInterface);
    }
}
