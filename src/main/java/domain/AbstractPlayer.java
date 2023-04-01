package domain;

import service.CardDeck;
import service.io.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractPlayer implements Player {

    private final String name;
    private final CardDeck cardDeck;
    private final UserInterface userInterface;
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

    public CardDeck getDeckHolder() {
        return cardDeck;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }
}
