package domain;

import service.PlayerManager;
import service.PlayerService;


public class AiPlayer extends AbstractPlayer {

    public AiPlayer(PlayerManager playerManager, PlayerService playerService) {
        super(playerManager, playerService);
    }

    @Override
    public Card move(Card placedCard) {
        System.out.println("trump : " + playerService.getTrump().getSuit());
        System.out.println("placedCard = " + (placedCard == null ? "no card" : placedCard.toString()));

        Card cardForMove = playerService.getCardForMove(placedCard, hand);
        System.out.println("card for move = " + (cardForMove == null ? "no card" : cardForMove.toString()));

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

}
