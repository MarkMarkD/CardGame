package service;

import domain.*;
import domain.PlayerType;
import util.RandomNameGenerator;

import java.util.*;

/**
 * A class for creating players before the game. Can create human players and AI players.
 * Injects a reference to a card deck to every Player class so that player can interact with the card deck during the game
 */

public class PlayerCreatorOld {

    private final int humanPlayers;
    private final int aiPlayers;
    private final RandomNameGenerator nameGenerator;

    public PlayerCreatorOld() {
        this.nameGenerator = new RandomNameGenerator();
    }

    Player create(PlayerType type) {

    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.addAll(createHumanPlayers());
        players.addAll(createAiPlayers());
        Collections.shuffle(players);
        return players;
    }

    private List<AbstractPlayer> createAiPlayers() {
        List<AbstractPlayer> humanPlayers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please enter the number of AI players (0-6):");
            try {
                numAiPlayers = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Cannot read the number");
            }
        } while (numAiPlayers < 0 || numAiPlayers > (6 - numHumanPlayers));

        if(numAiPlayers != 0) {
            for (int i = 0; i < numAiPlayers; i++) {
                humanPlayers.add(new AiPlayer(cardDeck, playerService).name(getRandomName()));
            }
        }
        return humanPlayers;
    }



    private List<AbstractPlayer> createHumanPlayers() {
        List<AbstractPlayer> aiPlayers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please enter the number of human players (0-6):");
            try {
                numHumanPlayers = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Cannot read the number");
            }
        } while (numHumanPlayers < 0 || numHumanPlayers > 6);

        if(numHumanPlayers != 0) {
            for (int i = 0; i < numHumanPlayers; i++) {
                System.out.println("Please enter the name of " + ++i + " human player:");
                aiPlayers.add(new HumanPlayer(cardDeck, playerService).name(scanner.nextLine()));
            }
        }
        return aiPlayers;
    }

}
