/**
 * Created by Дмитрий on 09.10.2017.
 * Класс осуществляет управление игроками и ходом игры
 */
package com.myCardGame;

public class PlayerManager {

    private Card cardForMove = new Card(5, "error");
    private Card someCard = new Card(888, "someSuit");
    Player player1;
    Player player2;

    int turn = 0;       //для будущих улучшений
    private int counter = 1;

    //Создать игроков (пока только двух)
    void createPlayers() {
        player1 = new Player(false);
        player1.fillHand();
        player2 = new Player(true);
        player2.fillHand();
    }

    //Старт игры. Цикл по-очереди передает ход двум игрокам пока карты в руке одного из них не кончатся
    void play() {
        while ((player1.hand.size()>0) || (player2.hand.size()>0)) {
            //CardDeckSingleton.getInstance().printDeck();
            if (!cardForMove.getSuit().equals("taken")) {
                cardForMove = null;
                cardForMove = player1.move(cardForMove);
                cardForMove = player2.move(cardForMove);
                player2.fillHand();
                player1.fillHand();
                System.out.println("------------------------------------конец " + counter + " хода");
                counter++;
                if (player1.hand.size() == 0) break;
            }
            else {
                cardForMove = someCard;
            }

            if (!cardForMove.getSuit().equals("taken")) {
                cardForMove = null;
                cardForMove = player2.move(cardForMove);
                cardForMove = player1.move(cardForMove);
                player1.fillHand();
                player2.fillHand();
                System.out.println("------------------------------------конец " + counter + " хода");
                counter++;
                if (player2.hand.size() == 0) break;
            }
            else {
                cardForMove = someCard;
            }
        }

        //Конец игры наступает после того как карты в колоде и в руке одного из игроков закончились
        System.out.println("Конец игры!");
        if ((player1.hand.size() == 0) && (player2.hand.size() == 0)) {
            System.out.println("Ничья.");
        }
        else if (player1.hand.size() == 0) {
            System.out.println("Ты победил. Возьми печеньку. И вот тебе миллион долларов (если бы).");
        }
        else if (player2.hand.size() == 0) {
            System.out.println("Тебе не победить искусственный интеллект! Смирись.");
        }
    }
}
