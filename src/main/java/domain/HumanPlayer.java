package domain;

import exception.CardValidationException;
import service.DeckHolder;
import service.io.UserInterface;
import validator.DefendCardValidator;

import java.util.InputMismatchException;
import java.util.Optional;


public class HumanPlayer extends AbstractPlayer {

    private final DefendCardValidator cardValidator;

    public HumanPlayer(String name, DeckHolder deckHolder, DefendCardValidator defendCardValidator, UserInterface ui) {
        super(name, deckHolder, ui);
        this.cardValidator = defendCardValidator;
    }

    @Override
    public Result attack() {
        Card cardForMove = askHumanPlayerToChooseCard();
        cardsInHand.remove(cardForMove);
        return new Result(ResultType.ATTACKED, cardForMove);
    }

    @Override
    public Result defend(Card placedCard) {
        Optional<Card> defendCard;
        while (true) {
            defendCard = askHumanPlayerToChooseCard(placedCard);
            if (defendCard.isEmpty()) {
                cardsInHand.add(placedCard);
                return new Result(ResultType.TOOK_PLACED_CARD);
            }
            try {
                cardValidator.validate(defendCard.get(), placedCard);
            } catch (CardValidationException ex) {
                getUserInterface().out(ex.getMessage());
                getUserInterface().out("Please choose another card or type \"take\" to take the card: ");
                continue;
            }
            cardsInHand.remove(defendCard.get());
            return new Result(ResultType.DEFENDED, defendCard.get());
        }
    }

    private Card askHumanPlayerToChooseCard() {
        Card chosenCard;
        String message = "Choose the card from your hand: ";
        getUserInterface().out(message);
        showHand();

        while (true) {
            String result = getUserInterface().in();
            try {
                chosenCard = cardsInHand.get((Integer.parseInt(result)-1));
            } catch (IndexOutOfBoundsException | InputMismatchException ex) {
                getUserInterface().out("Please enter the row number of card in your hand");
                continue;
            }
            return chosenCard;
        }
    }

    private Optional<Card> askHumanPlayerToChooseCard(Card placedCard) {
        Card chosenCard;
        String message = "Choose the card from your hand to beat placed card or type \"take\" to take the card: "
                + placedCard.toString();
        getUserInterface().out(message);
        showHand();

        while (true) {
            String result = getUserInterface().in();
            if (result.equalsIgnoreCase("take")) {
                return Optional.empty();
            }
            try {
                chosenCard = cardsInHand.get((Integer.parseInt(result)-1));
            } catch (IndexOutOfBoundsException | InputMismatchException ex) {
                getUserInterface().out("Please enter the row number of card in your hand");
                continue;
            }
            return Optional.of(chosenCard);
        }
    }
}
