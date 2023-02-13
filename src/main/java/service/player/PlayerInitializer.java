package service.player;

import domain.Player;
import exception.PlayerNumberException;
import service.io.UserInterface;
import domain.PlayerType;
import validator.PlayerNumberValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerInitializer {

    private final PlayerNumberValidator playerValidator;
    private final UserInterface userInterface;

    public PlayerInitializer(PlayerNumberValidator playerValidator, UserInterface userInterface) {
        this.playerValidator = playerValidator;
        this.userInterface = userInterface;
    }

    public List<Player> initPlayers() {
        List<Player> players = new ArrayList<>();
        players.addAll(createPlayers(PlayerType.AI));
        players.addAll(createPlayers(PlayerType.HUMAN));
        playerValidator.validate(players.size());
        Collections.shuffle(players);
        return players;
    }

    private List<Player> createPlayers(PlayerType playerType) {
        int qty = 0;
        boolean isParsed = false;
        do {
            userInterface.out(String.format("Please enter the number of %s players. (0-6):", playerType));
            userInterface.out("Total number of players can be between 2 and 6:");
            try {
                qty = Integer.parseInt(userInterface.in());
                if (qty > 6)
                    throw new PlayerNumberException();
                isParsed = true;
            } catch (NumberFormatException | PlayerNumberException ex) {
                userInterface.out(String.format("Wrong number value: %s", ex));
            }
        } while (!isParsed);
        return PlayerCreatorFactory.getPlayerCreator(playerType, userInterface).createAll(qty);
    }
}
