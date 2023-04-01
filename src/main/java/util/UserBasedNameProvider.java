package util;

import service.io.UserInterface;

public class UserBasedNameProvider implements NameProvider {

    private final UserInterface userInterface;

    public UserBasedNameProvider(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @Override
    public String provideName() {
        userInterface.out("Please enter the name of the player: ");
        return userInterface.in();
    }
}
