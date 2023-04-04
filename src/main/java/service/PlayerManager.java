package service;

import domain.Card;
import domain.Player;
import domain.Result;
import domain.ResultType;
import service.io.UserInterface;

import java.util.Iterator;
import java.util.List;

public class PlayerManager {

    private final List<Player> players;
    private final UserInterface userInterface;

    public PlayerManager(List<Player> players, UserInterface userInterface) {
        this.players = players;
        this.userInterface = userInterface;
    }

    public void play() {
        players.forEach(Player::fillHand);
        startGame();
        showGameResults();
    }

    private void startGame() {
        Result result;
        Iterator<Player> iterator = players.iterator();
        Player currentPlayer;
        Card placedCard = null;
        while(players.size() > 1) {
            if(!iterator.hasNext()) {
                iterator = players.iterator();
            }
            currentPlayer = iterator.next();
            if (placedCard != null) {
                result = currentPlayer.processCard(placedCard);
                if (result.getResultType().equals(ResultType.TOOK_PLACED_CARD)) {
                    placedCard = null;
                    continue;   // take card and skip move
                }
                currentPlayer.fillHand();
            }
            if (currentPlayer.getHand().isEmpty()) {
                iterator.remove();
                continue;
            }
            if (players.size() == 1) {
                break;
            }
            placedCard =  currentPlayer.makeMove();
            currentPlayer.fillHand();
            if (currentPlayer.getHand().isEmpty()) {
                iterator.remove();
            }
        }
    }

    private void showGameResults() {
        userInterface.out("");
        userInterface.out("Game results: ");
        userInterface.out(players.size() == 1 ? players.get(0).getName() + " lost the game" : "Dead heat");
    }
}
