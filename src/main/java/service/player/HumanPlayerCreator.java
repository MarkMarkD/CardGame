package service.player;

import domain.HumanPlayer;
import domain.Player;
import util.NameProvider;

public class HumanPlayerCreator extends AbstractPlayerCreator {

    public HumanPlayerCreator(NameProvider nameProvider) {
       super(nameProvider);
    }

    @Override
    public Player create(String name) {
        return new HumanPlayer(name);
    }
}
