package service;

import domain.*;

import java.util.*;

/**
 * A class for creating players before the game. Can create human players and AI players.
 * Injects a reference to a card deck to every Player class so that player can interact with the card deck during the game
 */

public class PlayerCreator {

    private int numHumanPlayers;
    private int numAiPlayers;
    private final Queue<Card> cardDeck;
    private final PlayerService playerService;

    public PlayerCreator(Queue<Card> cardDeck, PlayerService playerService) {
        this.cardDeck = cardDeck;
        this.playerService = playerService;
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

    private String getRandomName() {
        List<String> randomNames = Arrays.asList("Bob", "Pibody", "Serafim", "Bartolomew", "Leopold");
        return randomNames.get(new Random().nextInt(5));
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
