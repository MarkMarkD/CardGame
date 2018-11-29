import service.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    In this small project I used Java core technologies and OOP concepts like encapsulation, abstraction, inheritance,
    polymorphism, Java 8 streams and lambdas, a little bit of collections and exception handling
 */

public class Main {
    public static void main(String[] args) {

        int humanPlayers = 0;
        int aiPlayers = 0;
        List <String> names = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Greetings!");
        System.out.println("Today you gonna challenge against the computer in this awesome card game");
        System.out.println("Total number of players must be 2-6");

        do {
            System.out.println("Please enter the number of human players (0-6):");
            try {
                humanPlayers = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Cannot read the number");
            }
        } while (humanPlayers < 0 || humanPlayers > 6);

        if(humanPlayers != 0) {
            for (int i = 0; i < humanPlayers; i++) {
                System.out.println("Please enter the name of " + ++i + " human player:");
                names.add(scanner.nextLine());
            }
        }

        do {
            System.out.println("Please enter the number of AI players (0-6):");
            try {
                aiPlayers = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Cannot read the number");
            }
        } while (aiPlayers < 0 || aiPlayers > (6-humanPlayers));

        if (humanPlayers + aiPlayers < 2 || humanPlayers + aiPlayers > 6)
            System.exit(0);
        PlayerManager manager = new PlayerManager(humanPlayers, aiPlayers, names);
        manager.play();

    }
}
