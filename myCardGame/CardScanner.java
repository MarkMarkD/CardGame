/**
 * Created by Дмитрий on 13.10.2017.
 * Класс нужен для того чтобы считывать с клавиатуры текст
 */

package com.myCardGame;
import java.util.Scanner;

public class CardScanner {

    Scanner reader = new Scanner(System.in);
    int cardNum;
    String temp;
    String cardSuit;
    Card definedCard;
    String readedString;
    int firstSpace =0;
    int secondSpace =0;

    //Работа со строкой, введенной пользователем
    private void scanCard() {
        firstSpace = 0;
        secondSpace = 0;
        System.out.println("Введите название карты (например: queen of spades):");
        readedString = reader.nextLine();
        //работа со строкой readedString, выделяем номер и масть в две отдельные строки
        if (readedString.equals("take")) {
            temp = "take";
            cardSuit = "take";
        } else {
            for (int i = 0; i < readedString.length(); i++) {
                if ((readedString.charAt(i) == ' ') && (firstSpace == 0)) {
                    firstSpace = i;
                }
                if ((readedString.charAt(i) == ' ') && (firstSpace != 0)) {
                    secondSpace = i;
                }
            }

            temp = readedString.substring(0, firstSpace);
            cardSuit = readedString.substring(secondSpace + 1, readedString.length());
        }
    }

    //метод определяет какую карту ввел пользователь
    private void defineCard() {
        boolean switcher1 = false;
        boolean switcher2 = false;
        while (!switcher1) {
            if (temp.equals("take")) {
                System.out.println("Вы взяли карту");
                cardNum = 999;
                switcher1 = true;
            } else if (temp.equals("jack")) {
                cardNum = 11;
                switcher1 = true;
            } else if (temp.equals("queen")) {
                cardNum = 12;
                switcher1 = true;
            } else if (temp.equals("king")) {
                cardNum = 13;
                switcher1 = true;
            } else if (temp.equals("ace")) {
                cardNum = 14;
                switcher1 = true;
            } else if ((temp.equals("6")) || (temp.equals("7")) || (temp.equals("8")) || (temp.equals("9")) ||
                    (temp.equals("10"))) {
                cardNum = Integer.parseInt(temp);
                switcher1 = true;
            } else {
                System.out.println("Ошибка распознавания названия карты, введите еще раз");
                scanCard();
            }

        }
        while (!switcher2) {
            if ((cardSuit.equals("diamonds")) || (cardSuit.equals("clubs")) || (cardSuit.equals("spades")) ||
                    (cardSuit.equals("hearts")) || (cardSuit.equals("take"))) {
                switcher2 = true;
            }
            else {
                System.out.println("Ошибка распознавания масти, введите полностью название карты еще раз");
                scanCard();
            }
        }
        definedCard = new Card(cardNum, cardSuit);
    }

    //Возвращает объект-карту, соответвтвующий введенной пользователем карте
    public Card getDefinedCard() {
        scanCard();
        defineCard();
        if ((definedCard.getNum() == 999) || (definedCard.getSuit().equals("take"))) {
            return null;
        }
        else {
            return (definedCard);
        }
    }
}
