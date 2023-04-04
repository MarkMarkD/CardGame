package service.player;

import domain.Player;
import service.CardDeck;
import service.io.UserInterface;

import java.util.List;

public interface PlayerCreator {

    Player create(String name, CardDeck cardDeck, UserInterface userInterface);

    List<Player> createAll(int qty);
}
