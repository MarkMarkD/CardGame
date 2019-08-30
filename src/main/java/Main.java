import domain.AbstractPlayer;
import domain.Card;
import domain.Player;
import service.DeckService;
import service.PlayerCreator;
import service.PlayerManager;
import service.PlayerService;

import java.util.*;

/*
    In this small project I used Java core technologies and OOP concepts like encapsulation, abstraction, inheritance,
    polymorphism, Java 8 streams and lambdas, a little bit of collections and exception handling
 */

public class Main {
    public static void main(String[] args) {

        System.out.println("Greetings!");
        System.out.println("Today you gonna challenge against the computer in this awesome card game");
        System.out.println("Total number of players must be 2-6");

        Queue<Card> cardDeck = new DeckService().createNewDeck();
        PlayerService playerService = new PlayerService(cardDeck.peek());
        PlayerCreator playerCreator = new PlayerCreator(cardDeck, playerService);

        List<Player> players = playerCreator.createPlayers();

        if (players.size() < 2 || players.size() > 6) {
            System.out.println("Error. Number of players must be 2 to 6");
            System.exit(0);
        }

        PlayerManager manager = new PlayerManager(players, cardDeck);
        manager.play();

    }
}
