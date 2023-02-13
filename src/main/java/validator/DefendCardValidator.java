package validator;

import domain.Card;

public interface DefendCardValidator {

    void validate(Card cardForMove, Card placedCard);
}
