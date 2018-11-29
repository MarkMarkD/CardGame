package domain;

import service.PlayerManager;
import service.PlayerService;

import java.util.InputMismatchException;
import java.util.Scanner;


public class HumanPlayer extends AbstractPlayer {

    Scanner scanner = new Scanner(System.in);

    public HumanPlayer(PlayerManager playerManager, PlayerService playerService) {
        super(playerManager, playerService);
    }

    @Override
    public Card move(Card placedCard) {
        System.out.println("trump : " + playerService.getTrump().getSuit());
        System.out.println("placedCard = " + (placedCard == null ? "no card" : placedCard.toString()));

        // choose the card for move (or to beat placed card)
        Card cardForMove = askHumanPlayerToChooseCard(placedCard);
        if (placedCard != null && cardForMove != null) {
            while (!playerService.isCardBeatsCard(cardForMove, placedCard)) {
                System.out.println("This card can't beat placed card: " + placedCard.toString());
                System.out.println("Please choose another one or type \"take\" to take the card: ");
                cardForMove = askHumanPlayerToChooseCard(placedCard);
            }
        }
        System.out.println("card for move = " + (cardForMove == null ? "no card" : cardForMove.toString()));

        // card for move hasn't been chosen, take placed card
        if (cardForMove == null) {
            takeCard(placedCard);
            setSuccessfullyDefended(false);
            return null;
        }

        fillHand();
        if (cardForMove != null && placedCard != null) {
            setSuccessfullyDefended(true);
            return null;
        }
        return cardForMove;
    }

    public Card askHumanPlayerToChooseCard(Card placedCard) {
        Card chosenCard;
        System.out.println(placedCard != null ? "Choose the card to beat placed card: " + placedCard.toString()
                : "Choose the card from your hand: ");
        showHand();

        while (true) {
            String result = scanner.nextLine();
            if (result.equalsIgnoreCase("take"))
                return null;
            try {
                chosenCard = hand.get((Integer.parseInt(result)-1));
            } catch (IndexOutOfBoundsException | InputMismatchException ex) {
                System.out.println("Please enter the row number of card in your hand");
                continue;
            }
            return chosenCard;
        }
    }

}
