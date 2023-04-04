package service.player;

import domain.Player;
import domain.PlayerType;
import exception.PlayerNumberException;
import service.CardDeck;
import service.io.UserInterface;
import validator.PlayerNumberValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static validator.PlayerNumberValidator.MAX_PLAYERS_MSG;
import static validator.PlayerNumberValidator.MIN_PLAYERS_MSG;

/**
 * The class is intended to provide a list of initialized players
 */
public class PlayerInitializer {

    private final UserInterface userInterface;
    private final CardDeck cardDeck;

    public PlayerInitializer(UserInterface userInterface, CardDeck cardDeck) {
        this.userInterface = userInterface;
        this.cardDeck = cardDeck;
    }

    public List<Player> initPlayers() {
        int humanPlayersNum = 0;
        int aiPlayersNum = 0;
        boolean isValid = false;
        do {
            try {
                humanPlayersNum = getNumberOfPlayers(PlayerType.HUMAN);
                aiPlayersNum = getNumberOfPlayers(PlayerType.AI);
                playerNumberValidator.validate(humanPlayersNum + aiPlayersNum);
                isValid = true;
            } catch (NumberFormatException | PlayerNumberException ex) {
                userInterface.out(String.format("Wrong number value: %s", ex));
            }
        } while (!isValid);

        List<Player> players = new ArrayList<>();
        players.addAll(PlayerCreatorFactory.getPlayerCreator(PlayerType.AI, cardDeck, userInterface)
                .createAll(aiPlayersNum));
        players.addAll(PlayerCreatorFactory.getPlayerCreator(PlayerType.HUMAN, cardDeck, userInterface)
                .createAll(humanPlayersNum));
        players.forEach(player -> {
            player.setCardDeck(cardDeck);
            player.setUserInterface(userInterface);
        });
        Collections.shuffle(players);
        return players;
    }

    private int getNumberOfPlayers(PlayerType playerType) throws NumberFormatException, PlayerNumberException {
        userInterface.out(String.format("Please enter the number of %s players. (0-6):", playerType));
        userInterface.out("Total number of players should be between 2 and 6:");
        return Integer.parseInt(userInterface.in());
    }

    private final PlayerNumberValidator playerNumberValidator = (num) -> {
        if(num > (PlayerNumberValidator.MAX_PLAYERS)) {
            throw new PlayerNumberException(MAX_PLAYERS_MSG);
        }
        if(num < (PlayerNumberValidator.MIN_PLAYERS)) {
            throw new PlayerNumberException(MIN_PLAYERS_MSG);
        }
    };
}
