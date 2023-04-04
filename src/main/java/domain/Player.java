package domain;

import service.CardDeck;
import service.io.UserInterface;

import java.util.*;

public interface Player {

    void fillHand();

    void takeCard(Card placedCard);

    Card makeMove();

    Result processCard(Card card);

    List<Card> getHand();

    void showHand();

    String getName();

    void setCardDeck(CardDeck cardDeck);

    void setUserInterface(UserInterface userInterface);
}
