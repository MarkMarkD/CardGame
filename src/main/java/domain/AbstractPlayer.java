package domain;

import service.DeckHolder;
import service.io.UserInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractPlayer implements Player {

    private final String name;
    private final DeckHolder deckHolder;
    private final UserInterface userInterface;
    protected List<Card> cardsInHand = new ArrayList<>();

    public AbstractPlayer(String name, DeckHolder deckHolder, UserInterface userInterface) {
        this.name = name;
        this.deckHolder = deckHolder;
        this.userInterface = userInterface;
    }

    @Override
    public void fillHand() {
        while(cardsInHand.size() < 6) {
            if (deckHolder.isEmpty())
                return;
            cardsInHand.add(deckHolder.getNextCardFromDeck());
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

    public DeckHolder getDeckHolder() {
        return deckHolder;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }
}
