package domain;

import java.util.*;

public interface Player {

    void fillHand();

    void takeCard(Card placedCard);

    Result attack();

    Result defend(Card actionCard);

    List<Card> getHand();

    void showHand();

    String getName();
}
