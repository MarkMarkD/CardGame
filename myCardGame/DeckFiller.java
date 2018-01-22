/**
 * Created by Дмитрий on 03.10.2017.
 * Класс DeckFiller создает колоду карт и заполняет ее картами, затем передает классу CardDeck
 */

package com.myCardGame;

import java.util.ArrayList;

class DeckFiller {

    //Создать лист ссылок на обьект типа Card
    private ArrayList<Card> deck = new ArrayList<>(36);

    //Конструктор сразу создает колоду
    public DeckFiller() {
        createDeck();
    }
    //создать колоду
    private void createDeck() {
        int counterNum = 0;
        int counterSuit = 0;
        int n;
        String suit = "unidentified";
        //цикл проходит по каждому элементу массива, содержащему ссылку на обьект в массиве deck[] и создает объект
        //Card с указанными переменными 36 раз. В итоге получаем 36 карт.
        for (int i = 0; i < 36; i++) {

            if (i == 9 || i == 18 || i == 27) {
                counterNum = 0;
                counterSuit += 1;
            }

            if (counterSuit == 0)
                suit = "diamonds";
            if (counterSuit == 1)
                suit = "hearts";
            if (counterSuit == 2)
                suit = "clubs";
            if (counterSuit == 3)
                suit = "spades";

            n = counterNum + 6;
            deck.add(new Card(n, suit));
            counterNum += 1;
        }
    }

    //Возвращает колоду
    public ArrayList<Card> getDeck() {
        return deck;
    }
}