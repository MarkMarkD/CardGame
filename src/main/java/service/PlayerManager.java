package service;

import domain.*;
import service.io.UserInterface;

import java.util.*;

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
//        Optional<Card> actionCard = Optional.empty();
        Result result = null;
        Iterator<Player> iterator = players.iterator();
        Player currentPlayer;
        while(players.size() >= 1) {
            if(!iterator.hasNext()) {
                iterator = players.iterator();
            }
            currentPlayer = iterator.next();
            userInterface.out("");
            userInterface.out("Player " + currentPlayer.getName() + " makes a move: ");
            // first turn in game
            if (result == null) {
                result = currentPlayer.attack();
                currentPlayer.fillHand();
            }
            else {
                if(ResultType.ATTACKED.equals(result.getResultType())) {
                    result = currentPlayer.defend(result.getActionCard());
                    currentPlayer.fillHand();
                    if (currentPlayer.getHand().isEmpty()) {
                        iterator.remove();
                        continue;
                    }
                }
                if(ResultType.DEFENDED.equals(result.getResultType())) {
                    result = currentPlayer.attack();
                    currentPlayer.fillHand();
                    if (currentPlayer.getHand().isEmpty()) {
                        iterator.remove();
                        continue;
                    }
                }
            }

            if (players.size() == 1 && (result.getResultType().equals(ResultType.DEFENDED)
                    || (result.getResultType().equals(ResultType.TOOK_PLACED_CARD)))) {
                break;
            }
        }
    }

    private void showGameResults() {
        userInterface.out("");
        userInterface.out("Game results: ");
        userInterface.out(players.size() == 1 ? players.get(0).getName() + " lost the game" : "Dead heat");
    }

}
