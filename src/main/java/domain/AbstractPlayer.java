package domain;

import service.CardDeck;
import service.io.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public abstract class AbstractPlayer implements Player {

    private final String name;
    private CardDeck cardDeck;
    private UserInterface userInterface;
    protected List<Card> cardsInHand = new ArrayList<>();

    public AbstractPlayer(String name, CardDeck cardDeck, UserInterface userInterface) {
        this.name = name;
        this.cardDeck = cardDeck;
        this.userInterface = userInterface;
    }

    @Override
    public void fillHand() {
        while(cardsInHand.size() < 6) {
            if (cardDeck.isEmpty())
                return;
            cardsInHand.add(cardDeck.getNextCardFromDeck());
        }
    }

    @Override
    public void takeCard(Card placedCard) {
        userInterface.out("Player " + getName() + " is taking the card " + placedCard);
        cardsInHand.add(placedCard);
    }

    @Override
    public void showHand() {
        AtomicInteger counter = new AtomicInteger();
        cardsInHand.stream()
                .map(card -> counter.incrementAndGet() + ": " + card.toString())
                .forEach(System.out::println);
    }

    @Override
    public List<Card> getHand() {
        return cardsInHand;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Card makeMove() {
        userInterface.out("");
        userInterface.out(String.format("Player %s makes a move: ", getName()));
        userInterface.out(String.format("Trump card is %s ", getCardDeck().getTrumpSuit()));
        Card placedCard = getCardForMove();
        userInterface.out(String.format("Placed card: %s", placedCard));
        return placedCard;
    }

    @Override
    public Result processCard(Card placedCard) {
        userInterface.out("");
        userInterface.out("Player " + getName() + " is going to beat the card: " + placedCard);
        Result result = tryToBeatCard(placedCard);
        if (result.getResultType().equals(ResultType.DEFENDED)) {
            userInterface.out(("Player " + getName() + " successfully beats placed card"));
        }
        return result;
    }

    protected abstract Result tryToBeatCard(Card placedCard);

    protected abstract Card getCardForMove();

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public List<Card> getCardsThatCanBeatPlacedCard(Card placedCard) {
        return cardsInHand.stream()
                .filter((
                        ofTheSameSuit(placedCard).and(hasHigherRank(placedCard)))
                        .or((placedCardIsNotTrump(placedCard)).and(cardInHandIsTrump)))
                .toList();
    }

    private final Predicate<Card> cardInHandIsTrump = (card) -> card.getSuit().equals(getCardDeck().getTrumpSuit());

    private Predicate<Card> ofTheSameSuit(Card placedCard) {
        return card -> card.getSuit().equals(placedCard.getSuit());
    }

    private Predicate<Card> hasHigherRank(Card placedCard) {
        return card -> card.getRank().getNumber() > placedCard.getRank().getNumber();
    }

    private Predicate<Card> placedCardIsNotTrump(Card placedCard) {
        return card -> !placedCard.getSuit().equals(getCardDeck().getTrumpSuit());
    }
}
