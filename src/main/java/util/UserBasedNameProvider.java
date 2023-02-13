package util;

import service.io.UserInterface;

import java.util.Scanner;

public class UserBasedNameProvider implements NameProvider {

    private final Scanner scanner = new Scanner(System.in);
    private final UserInterface userInterface;

    public UserBasedNameProvider(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public String provideName() {
        userInterface.out("Please enter the name of the player: ");
        return scanner.nextLine();
    }
}
