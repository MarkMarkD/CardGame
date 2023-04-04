package service;

import domain.Card;
import util.Rank;
import util.Suit;

import java.util.*;

public class CardDeck {

    private final Queue<Card> deck;
    private final Card trump;

    public CardDeck() {
        this.deck = createNewDeck();
        this.trump = deck.peek();
    }

    private Queue<Card> createNewDeck() {
        List<Card> cardDeck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cardDeck.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cardDeck);
        return new LinkedList<>(cardDeck);
    }

    public Card getNextCardFromDeck() {
        return deck.poll();
    }

    public Suit getTrumpSuit() {
        return trump.getSuit();
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }
}
