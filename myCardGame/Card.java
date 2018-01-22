/**
 * Created by Дмитрий on 03.10.2017.
 * Класс Card содержит описание объекта типа карта. У карты есть такие поля данных как масть и номер, (а также уникаль-
 * ный id (id не реализован, т.к. нафиг он не нужен), сеттеры и геттеры
 */

package com.myCardGame;

public class Card {

    private String suit;        //масть
    private int num;            //номер карты от 6 до 14 (6, 7, 8, 9, 10, jack, queen, king, ace)
    private int id;

    public Card(int num, String suit) {
        this.num = num;
        this.suit = suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSuit() {
        return (suit);
    }

    public int getNum() {
        return (num);
    }

    public String getFullCardName() {
        if (num == 11)
            return ("jack of " + suit);
        else if (num == 12)
            return ("queen of " + suit);
        else if (num == 13)
            return ("king of " + suit);
        else if (num == 14)
            return ("ace of " + suit);
        else return (num + " of " + suit);

    }


}
