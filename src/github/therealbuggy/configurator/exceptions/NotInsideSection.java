package github.therealbuggy.configurator.exceptions;

/**
 * Created by jonathan on 02/01/16.
 */
public class NotInsideSection extends RuntimeException {
    public NotInsideSection() {
        super();
    }

    public NotInsideSection(String message) {
        super(message);
    }

    public NotInsideSection(String message, Throwable cause) {
        super(message, cause);
    }

    public NotInsideSection(Throwable cause) {
        super(cause);
    }
}
