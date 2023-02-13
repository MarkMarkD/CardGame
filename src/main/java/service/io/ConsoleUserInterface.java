package service.io;

import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String in() {
        return scanner.nextLine();
    }

    @Override
    public void out(String message) {
        System.out.println(message);
    }
}
