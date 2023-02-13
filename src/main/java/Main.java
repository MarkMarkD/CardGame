import service.*;
import service.io.ConsoleUserInterface;
import service.io.UserInterface;
import service.player.PlayerInitializer;
import validator.PlayerNumberValidator;

/*
    Just a small card game. Have fun
 */
public class Main {

    public static void main(String[] args) {
        UserInterface userInterface = new ConsoleUserInterface();
        printGreetings(userInterface);
        DeckHolder deckHolder = new DeckHolder();
        PlayerNumberValidator playerValidator = new PlayerNumberValidator();
        PlayerInitializer playerInitializer = new PlayerInitializer(playerValidator, userInterface);
        PlayerManager manager = new PlayerManager(playerInitializer.initPlayers(), userInterface);
        manager.play();
    }

    public static void printGreetings(UserInterface userInterface) {
        userInterface.out("Greetings!");
        userInterface.out("Today you gonna challenge against the computer or other players in this awesome card game");
        userInterface.out("Total number of players has to be 2-6");
    }
}
