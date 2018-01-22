/**
 * Created by Дмитрий on 14.01.2018.
 * Класс CardDeckSingleton содержит в себе колоду карт, которая доступна из любого места в программе
 * Паттерн Синглтон позволяет создать колоду (экземпляр класса) в единственном числе
 * Колода заполняется в классе DeckFiller, он ее передает сюда
 */
package com.myCardGame;

import java.util.ArrayList;
import java.util.Random;

public class CardDeckSingleton {
    private static CardDeckSingleton instance = null;
    ArrayList<Card> deck;   //колода
    private Card trumpCard;  //козырь
    int num;

    //Конструктор
    private CardDeckSingleton() {
        createDeck();
        setTrumpCard();
    }

    public static CardDeckSingleton getInstance() {
        if (instance == null) {
            instance = new CardDeckSingleton();
        }
        return instance;
    }

    //Создать (принять от DeckFiller) колоду
    private void createDeck() {
        DeckFiller filler = new DeckFiller();
        deck = filler.getDeck();
        setTrumpCard();
    }

    //Вывести на печать список карт в колоде
    public void printDeck() {
        for (int i = 0; i < deck.size(); i++) {
            System.out.println((i + 1) + ": " + deck.get(i).getFullCardName());
        }
    }

    //возвращает рандомную карту из колоды
    public Card getRandomCard() {
        Random rand = new Random();
        Card tempCard;
        if (deck.size() > 1) {
            num = rand.nextInt(deck.size() - 1);
        } else {
            num = rand.nextInt(deck.size());
        }
        tempCard = (deck.get(num));
        deck.remove(num);
        return tempCard;

    }

    //определить козырь
    private void setTrumpCard() {
        Random rand = new Random();
        Card tempCard;
        int num = rand.nextInt(deck.size());
        tempCard = (deck.get(num));
        deck.remove(num);
        deck.add(tempCard);
        trumpCard = tempCard;
    }

    //возвращает козырь
    public Card getTrump() {
        return (trumpCard);
    }
}
