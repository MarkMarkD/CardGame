package exception;

public class PlayerNumberException extends RuntimeException {

    private static final String MESSAGE = "Total number of players must be between 2 and 6";

    public PlayerNumberException() {
        super(MESSAGE);
    }
}
