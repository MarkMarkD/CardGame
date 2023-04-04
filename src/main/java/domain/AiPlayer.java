package domain;

import service.CardDeck;
import service.io.UserInterface;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class AiPlayer extends AbstractPlayer {

    public AiPlayer(String name, CardDeck cardDeck, UserInterface ui) {
        super(name, cardDeck, ui);
    }

    @Override
    protected Result tryToBeatCard(Card placedCard) {
        Optional<Card> defendCard = chooseCardToBeatPlacedCard(placedCard);
        if (defendCard.isPresent()) {
            cardsInHand.remove(defendCard.get());
            getUserInterface().out(getName() + " selected card " + defendCard.get());
            return new Result(ResultType.DEFENDED);
        }
        takeCard(placedCard);
        return new Result(ResultType.TOOK_PLACED_CARD);

    }

    @Override
    protected Card getCardForMove() {
        Card card = getSmallestNonTrump(cardsInHand).or(() -> getSmallestTrump(cardsInHand))
                .orElseThrow(() -> new RuntimeException("Couldn't choose card for move from hand"));
        cardsInHand.remove(card);
        return card;
    }

    private Optional<Card> getSmallestTrump(List<Card> hand) {
        return hand.stream()
                .filter(card -> card.getSuit().equals(getCardDeck().getTrumpSuit()))
                .min(Comparator.comparingInt(o -> o.getRank().getNumber()));
    }

    private Optional<Card> getSmallestNonTrump(List<Card> hand) {
        return hand.stream()
                .filter(card -> !card.getSuit().equals(getCardDeck().getTrumpSuit()))
                .min(Comparator.comparingInt(o -> o.getRank().getNumber()));
    }

    private Optional<Card> chooseCardToBeatPlacedCard(Card placedCard) {
        List<Card> cardsToBeatPlacedCard = getCardsThatCanBeatPlacedCard(placedCard);

        Optional<Card> cardToBeatPlacedCard = getSmallestNonTrump(cardsToBeatPlacedCard)
                .or(() -> getSmallestTrump(cardsToBeatPlacedCard));
        cardToBeatPlacedCard.ifPresent(cardsInHand::remove);
        return cardToBeatPlacedCard;
    }
}
