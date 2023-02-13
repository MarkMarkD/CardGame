package service.player;

import domain.Player;
import util.NameProvider;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayerCreator implements PlayerCreator {

    private final NameProvider nameProvider;

    public AbstractPlayerCreator(NameProvider nameProvider) {
        this.nameProvider = nameProvider;
    }

    @Override
    public List<Player> createAll(int qty) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < qty; i++) {
            players.add(create(nameProvider.provideName()));
        }
        return players;
    }

    public NameProvider getNameProvider() {
        return nameProvider;
    }
}
