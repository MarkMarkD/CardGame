package domain;

import service.CardDeck;
import service.io.UserInterface;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class AiPlayer extends AbstractPlayer {

    public AiPlayer(String name, CardDeck cardDeck, UserInterface ui) {
        super(name, cardDeck, ui);
    }

    @Override
    public Result attack() {
        Card actionCard = chooseTheSmallestCardFromHand();
        cardsInHand.remove(actionCard);
        return new Result(ResultType.ATTACKED, actionCard);
    }

    @Override
    public Result defend(Card actionCard) {
        Optional<Card> defendCard = chooseCardToBeatPlacedCard(actionCard);
        if (defendCard.isPresent()) {
            cardsInHand.remove(defendCard.get());
            return new Result(ResultType.DEFENDED);
        }
        else {
            takeCard(actionCard);
            return new Result(ResultType.TOOK_PLACED_CARD);
        }
    }

    //method returns the smallest card from the hand except trumps
    //or method returns smallest trump
    private Card chooseTheSmallestCardFromHand() {
        List<Card> hand = getHand();

        List<Card> trumpCards = hand
                .stream()
                .filter(card -> card.getSuit().equals(getDeckHolder().getTrumpSuit()))
                .collect(Collectors.toList());
        List<Card> notTrumpCards = hand
                .stream()
                .filter(card -> !card.getSuit().equals(getDeckHolder().getTrumpSuit()))
                .collect(Collectors.toList());

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
                if (card.getRank().compareTo(minCard.getRank()) < 0) {
                    minCard = card;
                }
            }
        }
        return minCard;
    }

    //method returns the smallest card from the hand except trumps that beats placed card
    //or method returns smallest trump that beats placed card
    private Optional<Card> chooseCardToBeatPlacedCard(Card placedCard) {
        List<Card> hand = getHand();

        List<Card> cardsThatCanBeatPlacedCard = hand
                .stream()
                .filter(card -> card.getSuit().equals(placedCard.getSuit()))
                .filter(card -> card.getRank().compareTo(placedCard.getRank()) > 0)
                .collect(Collectors.toList());

        if (cardsThatCanBeatPlacedCard.size() > 0 & !cardsThatCanBeatPlacedCard.isEmpty()) {
            Card minCard = getMinCardFromList(cardsThatCanBeatPlacedCard);
            hand.remove(minCard);
            return Optional.of(minCard);     //return min card that can beat placed card
        }

        if (placedCard.getSuit().equals(getDeckHolder().getTrumpSuit()))
            return Optional.empty();    //take card

        List<Card> trumpsThatCanBeatPlacedCard = hand
                .stream()
                .filter(card -> card.getSuit().equals(getDeckHolder().getTrumpSuit()))
                .collect(Collectors.toList());

        if (trumpsThatCanBeatPlacedCard.size() > 0 & !trumpsThatCanBeatPlacedCard.isEmpty()) {
            Card minCard = getMinCardFromList(trumpsThatCanBeatPlacedCard);
            hand.remove(minCard);
            return Optional.of(minCard);     //return min card that can beat placed card
        }
        return Optional.empty();    //take card
    }
}
