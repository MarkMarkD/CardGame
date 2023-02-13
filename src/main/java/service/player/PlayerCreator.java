package service.player;

import domain.Player;

import java.util.List;

public interface PlayerCreator {

    Player create(String name);

    List<Player> createAll(int qty);
}
