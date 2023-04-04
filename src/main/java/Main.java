import domain.Player;
import service.CardDeck;
import service.PlayerManager;
import service.io.ConsoleUserInterface;
import service.io.UserInterface;
import service.player.PlayerInitializer;

import java.util.List;

/**
    Just a small card game. Have fun
 */
public class Main {

    public static void main(String[] args) {
        UserInterface userInterface = new ConsoleUserInterface();
        printGreetings(userInterface);
        CardDeck cardDeck = new CardDeck();
        List<Player> players = new PlayerInitializer(userInterface, cardDeck).initPlayers();
        new PlayerManager(players, userInterface).play();
    }

    public static void printGreetings(UserInterface userInterface) {
        userInterface.out("Greetings!");
        userInterface.out("Today you gonna challenge against the computer or other players in this awesome card game.");
        userInterface.out("Total number of players should be between 2 and 6.");
    }
}
