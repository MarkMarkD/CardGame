package domain;

import exception.CardValidationException;
import service.CardDeck;
import service.io.UserInterface;

import java.util.InputMismatchException;
import java.util.Optional;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name, CardDeck cardDeck, UserInterface ui) {
        super(name, cardDeck, ui);
    }

    @Override
    protected Result tryToBeatCard(Card placedCard) {
        Optional<Card> defendCard;
        while (true) {
            defendCard = askHumanPlayerToSelectCard(placedCard);
            if (defendCard.isEmpty()) {
                cardsInHand.add(placedCard);
                return new Result(ResultType.TOOK_PLACED_CARD);
            }
            try {
                validateSelected(defendCard.get(), placedCard);
                cardsInHand.remove(defendCard.get());
                return new Result(ResultType.DEFENDED, defendCard.get());
            } catch (CardValidationException ex) {
                getUserInterface().out(ex.getMessage());
                getUserInterface().out("Please select another card or type \"take\" to take the card: ");
            }
        }
    }

    @Override
    protected Card getCardForMove() {
        Card cardForMove = askHumanPlayerToSelectCard();
        cardsInHand.remove(cardForMove);
        return cardForMove;
    }

    private void validateSelected(Card selectedCard, Card placedCard) {
        if (!getCardsThatCanBeatPlacedCard(placedCard).contains(selectedCard)) {
            throw new CardValidationException("Selected card cannot beat placed card. ");
        }
    }

    private Card askHumanPlayerToSelectCard() {
        Card chosenCard;
        String message = "Please select card for move from your hand: ";
        getUserInterface().out(message);
        showHand();
        while (true) {
            String result = getUserInterface().in();
            try {
                chosenCard = cardsInHand.get((Integer.parseInt(result)-1));
                return chosenCard;
            } catch (IndexOutOfBoundsException | InputMismatchException ex) {
                getUserInterface().out("Not a valid number. Please enter the row number of card in your hand");
            }
        }
    }

    private Optional<Card> askHumanPlayerToSelectCard(Card placedCard) {
        Card selectedCard;
        String message = String
                .format("Select the card from your hand to beat placed card %s or type \"take\" to take the card: ",
                        placedCard.toString());
        getUserInterface().out(message);
        showHand();

        while (true) {
            String result = getUserInterface().in();
            if (result.trim().equalsIgnoreCase("take")) {
                return Optional.empty();
            }
            try {
                selectedCard = cardsInHand.get((Integer.parseInt(result)-1));
                return Optional.of(selectedCard);
            } catch (IndexOutOfBoundsException | InputMismatchException ex) {
                getUserInterface().out("Not a valid number. Please enter the row number of card in your hand");
            }
        }
    }
}
