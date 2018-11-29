package domain;

import service.PlayerManager;
import service.PlayerService;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer implements Player {

    protected PlayerManager manager;
    protected PlayerService playerService;
    protected String name;                              // player's name
    protected List<Card> hand = new ArrayList<>();      // cards in player's hand
    protected boolean isSuccessfullyDefended;           // flag to define if player was defending or attacking
    private int i;                                      // counter for showing hand

    public AbstractPlayer (PlayerManager playerManager, PlayerService playerService) {
        this.manager = playerManager;
        this.playerService = playerService;
    }


    @Override
    public void fillHand() {
        while(hand.size() < 6) {
            Card cardFromDeck = manager.getCardFromDeck();
            if (cardFromDeck == null)
                break;
            hand.add(cardFromDeck);
        }
    }

    @Override
    public void takeCard(Card placedCard) {
        System.out.println("taking the card " + placedCard);
        hand.add(placedCard);
        placedCard = null;
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
