package domain;

import java.util.ArrayList;
import java.util.List;

public interface Player {

    void fillHand();

    void takeCard(Card placedCard);

    Card move(Card placedCard);

    List<Card> getHand();

    void showHand();

}
