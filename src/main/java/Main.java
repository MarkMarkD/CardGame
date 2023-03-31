import domain.Player;
import service.DeckHolder;
import service.PlayerManager;
import service.io.ConsoleUserInterface;
import service.io.UserInterface;
import service.player.PlayerInitializer;

import java.util.List;

/*
    Just a small card game. Have fun
 */
public class Main {

    public static void main(String[] args) {
        UserInterface userInterface = new ConsoleUserInterface();
        printGreetings(userInterface);
        DeckHolder deckHolder = new DeckHolder();
        List<Player> players = initPlayers(userInterface);
        new PlayerManager(players, userInterface).play();
    }

    public static List<Player> initPlayers(UserInterface userInterface) {
        return new PlayerInitializer(userInterface).initPlayers();
    }

    public static void printGreetings(UserInterface userInterface) {
        userInterface.out("Greetings!");
        userInterface.out("Today you gonna challenge against the computer or other players in this awesome card game");
        userInterface.out("Total number of players has to be 2-6");
    }
}
