package service;

import domain.Card;
import util.Rank;
import util.Suit;

import java.util.*;

public class DeckHolder {

    private final Queue<Card> cardDeck;
    private final Card trump;

    public DeckHolder() {
        this.cardDeck = createNewDeck();
        this.trump = cardDeck.peek();
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
        return cardDeck.poll();
    }

    public Suit getTrumpSuit() {
        return trump.getSuit();
    }

    public boolean isEmpty() {
        return cardDeck.isEmpty();
    }
}
