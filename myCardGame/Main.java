/**
 * Created by Дмитрий on 03.10.2017.
 * Main class
 */

package com.myCardGame;

public class Main {

    public static void main(String[] args) {

        PlayerManager manager = new PlayerManager();
        System.out.println("Привет, игрок! Тебе предстоит героическое сражение с бездушной машиной");
        System.out.println("Да начнется баттл!");
        manager.createPlayers();
        manager.play();
    }
}
