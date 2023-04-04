package service.player;

import domain.Player;
import service.CardDeck;
import service.io.UserInterface;
import util.NameProvider;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayerCreator implements PlayerCreator {

    private final NameProvider nameProvider;
    private final CardDeck cardDeck;
    private final UserInterface userInterface;

    public AbstractPlayerCreator(NameProvider nameProvider, CardDeck cardDeck, UserInterface userInterface) {
        this.nameProvider = nameProvider;
        this.cardDeck = cardDeck;
        this.userInterface = userInterface;
    }

    @Override
    public List<Player> createAll(int qty) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < qty; i++) {
            players.add(create(nameProvider.provideName(), cardDeck, userInterface));
        }
        return players;
    }
}
