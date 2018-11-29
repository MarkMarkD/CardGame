package service;

import domain.Card;

import java.util.ArrayList;

public class DeckFiller {
    //create list of cards
    private static ArrayList<Card> deck = new ArrayList<>(36);

    //fill card deck with cards
    private static void createDeck() {
        int counterNum = 0;
        int counterSuit = 0;
        int n;
        String suit = "unidentified";

        for (int i = 0; i < 36; i++) {

            if (i % 9 == 0 && i != 0) {
                counterNum = 0;
                counterSuit += 1;
            }

            switch(counterSuit) {
                case 0:
                    suit = "diamonds";
                    break;
                case 1:
                    suit = "hearts";
                    break;
                case 2:
                    suit = "clubs";
                    break;
                case 3:
                    suit = "spades";
                    break;
            }
            n = counterNum + 6;
            deck.add(new Card(n, suit));
            counterNum += 1;
        }
    }

    public static ArrayList<Card> getDeck() {
        createDeck();
        return deck;
    }
}
