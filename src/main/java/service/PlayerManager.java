package service;

import domain.*;

import java.util.*;

public class PlayerManager {
    private int numberOfHumanPlayers;
    private int numberOfAiPlayers;
    private List<AbstractPlayer> players = new ArrayList<>();
    private List<Card> deck;
    private List<String> names;
    PlayerService playerService;

    public PlayerManager(int numberOfHumanPlayers, int numberOfAiPlayers, List<String> names) {
        this.numberOfHumanPlayers = numberOfHumanPlayers;
        this.numberOfAiPlayers = numberOfAiPlayers;
        this.names = names;
        deck = DeckFiller.getDeck();
        playerService = new PlayerService(createTrumpCard());
    }

    // prepare for game and then start
    public void play() {

        createPlayers();
        Collections.shuffle(players);
        Collections.shuffle(deck);
        players.forEach(Player::fillHand);

        startGame();
        showGameResults();
    }

    // start the game
    private void startGame() {
        Card placedCard = null;
        Iterator iterator = players.iterator();
        Player player;
        while(players.size() >= 1) {
            if(iterator.hasNext()) {
                player = (Player) iterator.next();
                System.out.println();
                System.out.println("player " + ((AbstractPlayer) player).getName() + " makes a move: ");
                placedCard = player.move(placedCard);

                if (((AbstractPlayer) player).isSuccessfullyDefended()){
                    placedCard = null;
                    if (player.getHand().size() > 0) {
                        ((AbstractPlayer) player).setSuccessfullyDefended(false);
                        placedCard = player.move(placedCard);
                    }
                }

                if (player.getHand().size() == 0)
                    iterator.remove();
                if (players.size() == 1 && placedCard == null)
                    break;
            } else
                iterator = players.iterator();
        }
    }

    private void showGameResults() {
        System.out.println();
        System.out.println("Game results: ");
        System.out.println(players.size() == 1 ? players.get(0).getName() + " lost the game" : "Dead heat");
        System.out.println(deck.size());
    }

    private void createPlayers() {
        for(int i = 0; i < numberOfHumanPlayers; i++) {
            players.add(new HumanPlayer(this, playerService).name(names.get(i)));
        }

        for(int i = 0; i < numberOfAiPlayers; i++) {
            players.add(new AiPlayer(this, playerService).name(getRandomName()));
        }
    }

    private Card createTrumpCard() {
        return deck.get(new Random().nextInt(35));
    }

    private String getRandomName() {
        List<String> randomNames = Arrays.asList("Bob", "Pibody", "Serafim", "Bartolomew", "Leopold");
        return randomNames.get(new Random().nextInt(5));
    }

    public Card getCardFromDeck() {
        Card cardFromDeck = null;
        Iterator iterator = deck.iterator();
        if (iterator.hasNext()) {
            cardFromDeck = (Card) iterator.next();
            iterator.remove();
        }
        return cardFromDeck;
    }

}
