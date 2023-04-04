package validator;

import domain.Card;

@FunctionalInterface
public interface DefendCardValidator {

    void validate(Card cardForMove, Card placedCard);
}
