package service;

import domain.Card;
import util.Suit;

import java.util.*;

public class DeckService {
    public Queue<Card> createNewDeck() {
        List<Card> cardDeck = new ArrayList<>();
        List<Suit> suits = new ArrayList<>(Arrays.asList(
                Suit.CLUBS,
                Suit.DIAMONDS,
                Suit.HEARTS,
                Suit.SPADES));
        for (Suit suit : suits) {
            for (int i = 6; i <= 14; i++) {
                try {
                    (cardDeck).add(new Card(i, suit));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        Collections.shuffle(cardDeck);
        return new LinkedList<>(cardDeck);
    }
}
