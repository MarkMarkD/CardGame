/**
 * Created by Дмитрий on 09.10.2017.
 * Класс Player - это игрок. Игрок умеет брать карты из колоды, делать ход и отбиваться.
 */

package com.myCardGame;

import java.util.ArrayList;

public class Player {
    ArrayList<Card> hand = new ArrayList<>();   //список карт в руке игрока
    private Card trumpCard = CardDeckSingleton.getInstance().getTrump();    //козырь
    private Card taken = new Card (6, "taken");
    private boolean ai;     //управляется игроком или компьютером

    public Player (boolean ai) {
        this.ai = ai;
    }

    //взять необходимое количество карт из колоды
    public void fillHand() {
        while (hand.size() < 6) {
            if (CardDeckSingleton.getInstance().deck.size() > 0) {
                hand.add(CardDeckSingleton.getInstance().getRandomCard());
            }
            else {
                System.out.println("В колоде больше не осталось карт");
                break;
            }
        }
    }

    //Выбрать какой картой походить если игрок ходит первым
    private Card getCardForMove() {
        //method returns the smallest card from the hand except trumps
        //or method returns smallest trump
        ArrayList<Card> trumpCards = new ArrayList<>();
        ArrayList<Card> notTrumpCards = new ArrayList<>();

        for (int j = 0; j < hand.size(); j++) {
            if (hand.get(j).getSuit().equals(trumpCard.getSuit())) {
                trumpCards.add(hand.get(j));
            }
        }

        for (int j = 0; j < hand.size(); j++) {
            if (!(hand.get(j).getSuit().equals(trumpCard.getSuit()))) {
                notTrumpCards.add(hand.get(j));
            }
        }

        if (notTrumpCards.size() > 0) {
            Card minCard = notTrumpCards.get(0);
            for (int i = 0; i < notTrumpCards.size(); i++) {
                if (notTrumpCards.get(i).getNum() < minCard.getNum()) {
                    minCard = notTrumpCards.get(i);
                }
            }
            hand.remove(minCard);
            return (minCard);
        }
        else {
            Card minCard = trumpCards.get(0);
            for (int i = 0; i < trumpCards.size(); i++) {
                if (trumpCards.get(i).getNum() < minCard.getNum()) {
                    minCard = trumpCards.get(i);
                }
            }
            hand.remove(minCard);
            return (minCard);
        }

    }

    //Выбрать какой картой отбиться если игрок не ходит первым
    private Card getCardForMove(Card placedCard) {
        //method returns the smallest card from the hand except trumps that beats placed card
        //or method returns smallest trump that beats placed card
        ArrayList<Card> cardsThatCanBeatPlacedCard = new ArrayList<>();
        ArrayList<Card> trumpCards = new ArrayList<>();
        boolean foundCard = false;
        boolean foundTrump = false;
        Card minTrump = new Card(666, "unexpected error in class Player, method getCardForMove(), minCard");
        Card minCard = new Card(777, "error here supposed to be min Trump in class Player, getCardForMove()");
        for (int j = 0; j < hand.size(); j++) {
            if ((hand.get(j).getNum() > placedCard.getNum()) && (hand.get(j).getSuit().equals(placedCard.getSuit()))) {
                cardsThatCanBeatPlacedCard.add(hand.get(j));
                //System.out.println("cardsThatCanBeatPlacedCard");     //для отладки
                //System.out.println(hand.get(j).getFullCardName());    //для отладки
            }
        }

        if (cardsThatCanBeatPlacedCard.size() > 0) {
            foundCard = true;
            for (int i = 0; i < cardsThatCanBeatPlacedCard.size(); i++) {
                if (cardsThatCanBeatPlacedCard.get(i).getNum() < minCard.getNum()) {
                    minCard = cardsThatCanBeatPlacedCard.get(i);
                }
            }
        }

        if (foundCard) {
            hand.remove(minCard);
            return minCard;
        }

        if (cardsThatCanBeatPlacedCard.size() == 0) {
            for (int j = 0; j < hand.size(); j++) {
                if (hand.get(j).getSuit().equals(trumpCard.getSuit())) {
                    trumpCards.add(hand.get(j));
                }
            }

            if (trumpCards.size() > 0) {
                boolean checker = true;
                Card temp;
                // сортировка массива пузырьковым методом
                while (checker) {
                    checker = false;
                    for (int k = 0; k < trumpCards.size()-1; k++) {
                        if (trumpCards.get(k).getNum() > trumpCards.get(k+1).getNum()) {
                            temp = trumpCards.get(k);
                            trumpCards.set(k, trumpCards.get(k+1));
                            trumpCards.set(k+1, temp);
                            checker = true;
                        }

                    }
                }
                //выбор минимально подходящего козыря который бьет выложенную карту
                if (!(trumpCard.getSuit()).equals(placedCard.getSuit())) {
                    minTrump = trumpCards.get(0);
                    foundTrump = true;
                }
                else {
                    for (int k = 0; k < trumpCards.size(); k++) {
                        if (trumpCards.get(k).getNum() > placedCard.getNum()) {
                            minTrump = trumpCards.get(k);
                            foundTrump = true;
                        }
                    }
                }
            }
        }

        if (foundTrump) {
            hand.remove(minTrump);
            return (minTrump);
        }
        if ((foundCard == false) && (foundTrump == false)) {
            takeCard(placedCard);
            return null;
        }
        Card errorCard = new Card (000, "error card, returns unexpected card");
        return (errorCard);
    }

    //взять карту, которой походил другой игрок
    private void takeCard(Card placedCard) {
        hand.add(placedCard);
        System.out.println("Беру карту");
    }

    //сделать ход когда ходишь первым
    public Card move() {
        Card cardForMove;
        if (ai) {
            cardForMove = getCardForMove();
            System.out.println("Компьютер ходит картой " + cardForMove.getFullCardName());
            return cardForMove;
        }
        else {
            System.out.println();
            System.out.println("Карты в вашей руке:");
            printHand();
            boolean switcher = false;
            while (switcher == false) {
                System.out.println("Ваш ход, введите карту");
                CardScanner scanner = new CardScanner();
                cardForMove = scanner.getDefinedCard();
                if (cardInHand(cardForMove)) {
                    System.out.println("Ваша карта " + cardForMove.getFullCardName());

                    for (int i = 0; i < hand.size(); i++){
                        if ((hand.get(i).getNum() == cardForMove.getNum()) && (hand.get(i).getSuit().equals(cardForMove.getSuit()))) {
                        cardForMove = hand.get(i);
                        }
                    }
                    hand.remove(cardForMove);
                    return cardForMove;
                }
                else {
                    System.out.println("Этой карты нет у вас в руке");
                }
            }
        }
        return(null);
    }

    //Сделать ход когда нужно отбиваться
    public Card move(Card placedCard) {
        if (placedCard == null) {
            return move();
        }
        Card cardForMove;
        if (ai) {
            cardForMove = getCardForMove(placedCard);
            if (cardForMove == null) {
                System.out.println("Компьютер взял карту");
                return taken;
            }
            else {
                System.out.println("Компьютер отбивается картой " + cardForMove.getFullCardName());
                hand.remove(cardForMove);
                return cardForMove;
            }
        }
        else {
            System.out.println();
            System.out.println("Карты в вашей руке:");
            printHand();
            boolean switcher = false;
            while (switcher == false) {
                System.out.println("Ваш ход, отбивайтесь, введите карту");
                System.out.println("Введите take чтобы взять карту");
                CardScanner scanner = new CardScanner();
                cardForMove = scanner.getDefinedCard();
                if (cardForMove == null) {
                    cardForMove = taken;
                    takeCard(placedCard);
                    System.out.println("Вы взяли карту");
                    return cardForMove;
                }
                if ((cardInHand(cardForMove)) == false) {
                    System.out.println("Этой карты нет у вас в руке");
                }
                if (cardBeatsCard(cardForMove, placedCard) == false) {
                    System.out.println("Вы не можете отбиться этой картой");
                }
                if ((cardInHand(cardForMove)) && (cardBeatsCard(cardForMove, placedCard))) {
                    System.out.println("Ваша карта " + cardForMove.getFullCardName());

                    for (int i = 0; i < hand.size(); i++){
                        if ((hand.get(i).getNum() == cardForMove.getNum()) && (hand.get(i).getSuit().equals(cardForMove.getSuit()))) {
                            cardForMove = hand.get(i);
                        }
                    }
                    hand.remove(cardForMove);
                    return cardForMove;
                }
            }
        }
        return(null);
    }

    //вывести на экран все карты в руке
    public void printHand() {
        System.out.println();
        //System.out.println("Printing hand");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ": " + hand.get(i).getFullCardName());
        }
        System.out.println("Козырь: " + trumpCard.getFullCardName());
        System.out.println();
    }

    //проверить, есть ли карта, которую ввел пользователь, в списке карт в руке
    private boolean cardInHand(Card cardForMove) {
        boolean switcher1 = false;
        boolean switcher2 = false;
        for (int j = 0; j < hand.size(); j ++) {
            if (hand.get(j).getNum() == cardForMove.getNum()){
                switcher1 = true;
            }

            if (hand.get(j).getSuit().equals(cardForMove.getSuit())){
                switcher2 = true;
            }
        }
        if ((switcher1) && (switcher2)) {
            return true;
        }
        else {
            return false;
        }
    }

    //Проверить, может ли введенная пользователем карта побить карту, которой походил другой игрок
    private boolean cardBeatsCard(Card cardForMove, Card placedCard) {
        boolean switcher1 = false;
        boolean switcher2 = false;
        if (cardForMove.getNum() > placedCard.getNum()) {
            switcher1 = true;
        }
        if ((cardForMove.getSuit().equals(trumpCard.getSuit())) && !(placedCard.getSuit().equals(trumpCard.getSuit()))) {
            switcher1 = true;
            switcher2 = true;
        }
        if (cardForMove.getSuit().equals(placedCard.getSuit())) {
            switcher2 = true;
        }

        if ((switcher1) && (switcher2)) {
            return true;
        }
        else {
            return false;
        }
    }
}
