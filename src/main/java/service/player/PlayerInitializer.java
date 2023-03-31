package service.player;

import domain.Player;
import domain.PlayerType;
import exception.PlayerNumberException;
import service.io.UserInterface;
import validator.PlayerValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static validator.PlayerValidator.MAX_PLAYERS_MSG;
import static validator.PlayerValidator.MIN_PLAYERS_MSG;

/**
 * The class is intended to provide a list of initialized players
 */
public class PlayerInitializer {

    private int totalNumberOfPlayers;
    private final UserInterface userInterface;

    public PlayerInitializer(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public List<Player> initPlayers() {
        int humanPlayersNum = 0;
        int aiPlayersNum = 0;
        boolean isValid = false;
        do {
            try {
                humanPlayersNum = askNumberOfPlayers(PlayerType.HUMAN);
                aiPlayersNum = askNumberOfPlayers(PlayerType.AI);
                validatorMin.validate(totalNumberOfPlayers);
                isValid = true;
            } catch (NumberFormatException | PlayerNumberException ex) {
                userInterface.out(String.format("Wrong number value: %s", ex));
            }
        } while (!isValid);

        List<Player> players = new ArrayList<>();
        players.addAll(PlayerCreatorFactory.getPlayerCreator(PlayerType.AI, userInterface).createAll(aiPlayersNum));
        players.addAll(PlayerCreatorFactory.getPlayerCreator(PlayerType.HUMAN, userInterface).createAll(humanPlayersNum));
        Collections.shuffle(players);
        return players;
    }

    private int askNumberOfPlayers(PlayerType playerType) throws NumberFormatException, PlayerNumberException{
        userInterface.out(String.format("Please enter the number of %s players. (0-6):", playerType));
        userInterface.out("Total number of players should be between 2 and 6:");
        int num = Integer.parseInt(userInterface.in());
        validatorMax.validate(num);
        totalNumberOfPlayers += num;
        return num;
    }

    private final PlayerValidator validatorMax = (num) -> {
        if(num > (PlayerValidator.MAX - totalNumberOfPlayers))
            throw new PlayerNumberException(MAX_PLAYERS_MSG);
    };

    private final PlayerValidator validatorMin = (num) -> {
        if(num < (PlayerValidator.MIN - totalNumberOfPlayers))
            throw new PlayerNumberException(MIN_PLAYERS_MSG);
    };
}
