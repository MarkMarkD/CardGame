package service.player;

import domain.AiPlayer;
import domain.Player;
import util.NameProvider;

public class AiPlayerCreator extends AbstractPlayerCreator {

    public AiPlayerCreator(NameProvider nameProvider) {
        super(nameProvider);
    }

    @Override
    public Player create(String name) {
        return new AiPlayer(name);
    }
}
