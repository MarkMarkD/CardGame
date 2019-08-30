package service;

import domain.Card;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerService {

    private final Card trump;

    public PlayerService(Card trump) {
        this.trump = trump;
    }

    public Card getCardForMove(Card placedCard, List<Card> hand) {
         if (placedCard == null) {
             return chooseTheSmallestCardFromHand(hand);
         }
         else {
             return chooseCardToBeatPlacedCard(placedCard, hand);
         }
    }

    //method returns the smallest card from the hand except trumps that beats placed card
    //or method returns smallest trump that beats placed card
    private Card chooseCardToBeatPlacedCard(Card placedCard, List<Card> hand) {

        List<Card> cardsThatCanBeatPlacedCard = hand
                .stream()
                .filter(card -> card.getSuit().equals(placedCard.getSuit()))
                .filter(card -> card.getRank() > placedCard.getRank())
                .collect(Collectors.toList());

        if (cardsThatCanBeatPlacedCard.size() > 0 & !cardsThatCanBeatPlacedCard.isEmpty()) {
            Card minCard = getMinCardFromList(cardsThatCanBeatPlacedCard);
            hand.remove(minCard);
            return minCard;     //return min card that can beat placed card
        }

        if (placedCard.getSuit().equals(trump.getSuit()))
            return null;    //take card

        List<Card> trumpsThatCanBeatPlacedCard = hand
                .stream()
                .filter(card -> card.getSuit().equals(trump.getSuit()))
                .collect(Collectors.toList());

        if (trumpsThatCanBeatPlacedCard.size() > 0 & !trumpsThatCanBeatPlacedCard.isEmpty()) {
            Card minCard = getMinCardFromList(trumpsThatCanBeatPlacedCard);
            hand.remove(minCard);
            return minCard;     //return min card that can beat placed card
        }
        return null;    //take card
    }

    //method returns the smallest card from the hand except trumps
    //or method returns smallest trump
    private Card chooseTheSmallestCardFromHand(List<Card> hand) {

        List<Card> trumpCards = hand.stream().filter(card -> card.getSuit().equals(trump.getSuit())).collect(Collectors.toList());
        List<Card> notTrumpCards = hand.stream().filter(card -> !card.getSuit().equals(trump.getSuit())).collect(Collectors.toList());

        if (notTrumpCards.size() > 0 & !notTrumpCards.isEmpty()) {
            Card minCard = getMinCardFromList(notTrumpCards);
            hand.remove(minCard);
            return (minCard);
        }
        else {
            Card minCard = getMinCardFromList(trumpCards);
            hand.remove(minCard);
            return (minCard); 
        }
    }

    // returns card with smallest number from passed list
    private Card getMinCardFromList(List<Card> cards) {
        Card minCard = cards.get(0);
        if (cards.size() > 1) {
            for (Card card : cards) {
                if (card.getRank() < minCard.getRank()) {
                    minCard = card;
                }
            }
        }
        return minCard;
    }

    // check if chosen card is able to beat the card placed by another player
    public boolean isCardBeatsCard(Card cardForMove, Card placedCard) {
        boolean isNumberChecked= false;
        boolean isSuitChecked = false;
        if (cardForMove.getRank() > placedCard.getRank()) {
            isNumberChecked = true;
        }
        if ((cardForMove.getSuit().equals(trump.getSuit())) && !(placedCard.getSuit().equals(trump.getSuit()))) {
            isNumberChecked = true;
            isSuitChecked = true;
        }
        if (cardForMove.getSuit().equals(placedCard.getSuit())) {
            isSuitChecked = true;
        }
        return (isNumberChecked) && (isSuitChecked);
    }

    public Card getTrump() {
        return trump;
    }

}
