package service;

import domain.*;

import java.util.*;

public class PlayerManager {

    private final List<Player> players;
    private final Queue<Card> deck;

    public PlayerManager(List<Player> players,
                         Queue<Card> deck) {
        this.players = players;
        this.deck = deck;
    }

    public void play() {
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
                System.out.println("player " + (player).getName() + " makes a move: ");
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
