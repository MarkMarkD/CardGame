package domain;

import java.util.List;

public interface Player {

    void fillHand();

    void takeCard(Card placedCard);

    Card makeMove();

    Result processCard(Card card);

    List<Card> getHand();

    void showHand();

    String getName();
}
