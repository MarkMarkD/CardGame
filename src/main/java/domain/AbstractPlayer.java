package domain;

import service.CardDeck;
import service.io.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        userInterface.out("taking the card " + placedCard);
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
        userInterface.out("Player " + getName() + " makes a move: ");
        return getCardForMove();
    }

    @Override
    public Result processCard(Card placedCard) {
        userInterface.out("");
        userInterface.out("Player " + getName() + " is going to beat the card: " + placedCard);
        return tryToBeatCard(placedCard);
    }

    protected abstract Result tryToBeatCard(Card placedCard);

    protected abstract Card getCardForMove();

    public CardDeck getCardDeck() {
        return cardDeck;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void setCardDeck(CardDeck cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public List<Card> getCardsThatCanBeatPlacedCard(Card placedCard) {
        return cardsInHand.stream()
                .filter(card -> card.getSuit().equals(placedCard.getSuit())
                        || card.getSuit().equals(getCardDeck().getTrumpSuit()))
                .filter(card -> card.getRank().getNumber() > placedCard.getRank().getNumber()).toList();
    }
}
