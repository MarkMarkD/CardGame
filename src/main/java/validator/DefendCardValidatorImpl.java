package validator;

import domain.Card;
import exception.CardValidationException;
import util.Suit;

public class DefendCardValidatorImpl implements DefendCardValidator {

    private final Suit trump;

    public DefendCardValidatorImpl(Suit trump) {
        this.trump = trump;
    }

    @Override
    public void validate(Card cardForMove, Card placedCard) {
        if (cardForMove.getRank().compareTo(placedCard.getRank()) <= 0) {
            if (isTrump(cardForMove) && isTrump(placedCard)) {
                throw new CardValidationException("Rank of the chosen card is lower than rank of placed card.");
            }
            if (!isTrump(cardForMove)) {
                throw new CardValidationException("Rank of the chosen card is lower than rank of placed card.");
            }
        }
        if (!cardForMove.getSuit().equals(placedCard.getSuit()) && !isTrump(cardForMove)) {
            throw new CardValidationException("Suits of the cards do not match.");
        }
    }

    private boolean isTrump(Card cardForMove) {
        return cardForMove.getSuit().equals(trump);
    }
}
