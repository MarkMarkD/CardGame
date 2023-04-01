package domain;

import service.CardDeck;
import service.io.UserInterface;

import java.util.*;

public interface Player {

    void fillHand();

    void takeCard(Card placedCard);

    Result attack();

    Result defend(Card actionCard);

    List<Card> getHand();

    void showHand();

    String getName();

    void setCardDeck(CardDeck cardDeck);

    void setUserInterface(UserInterface userInterface);
}
