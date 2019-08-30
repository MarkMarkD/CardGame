package domain;

import java.util.*;

public interface Player {

    void fillHand();

    void takeCard(Card placedCard);

    Card move(Card placedCard);

    List<Card> getHand();

    void showHand();

    String getName();

}
