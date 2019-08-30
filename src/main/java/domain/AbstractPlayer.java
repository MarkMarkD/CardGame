package domain;

import service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractPlayer implements Player {

    private final Queue<Card> cardDeck;
    protected PlayerService playerService;
    protected String name;                              // player's name
    protected List<Card> hand = new ArrayList<>();      // cards in player's hand
    protected boolean isSuccessfullyDefended;           // flag to define if player was defending or attacking
    private int i;                                      // counter for showing hand

    public AbstractPlayer(Queue<Card> cardDeck, PlayerService playerService) {
        this.cardDeck = cardDeck;
        this.playerService = playerService;
    }

    @Override
    public void fillHand() {
        while(hand.size() < 6) {
            if (cardDeck.isEmpty())
                return;
            hand.add(cardDeck.poll());
        }
    }

    @Override
    public void takeCard(Card placedCard) {
        System.out.println("taking the card " + placedCard);
        hand.add(placedCard);
    }

    @Override
    public void showHand() {
        i = 0;
        hand.stream().map(card -> ++i + ": " + card.toString()).forEach(System.out::println);
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public String getName() {
        return name;
    }

    public AbstractPlayer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSuccessfullyDefended() {
        return isSuccessfullyDefended;
    }

    public void setSuccessfullyDefended(boolean successfullyDefended) {
        isSuccessfullyDefended = successfullyDefended;
    }


}
