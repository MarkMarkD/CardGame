package exception;

public class CardValidationException extends RuntimeException {

    private static final String MSG = "This card can't beat placed card. ";

    public CardValidationException(String message) {
        super(MSG + message);
    }

    public CardValidationException() {
        super(MSG);
    }
}
