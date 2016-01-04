package github.therealbuggy.configurator.exceptions;

/**
 * Created by jonathan on 02/01/16.
 */
public class CannotGetConfigurator extends RuntimeException {
    public CannotGetConfigurator() {
        super();
    }

    public CannotGetConfigurator(String message) {
        super(message);
    }

    public CannotGetConfigurator(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotGetConfigurator(Throwable cause) {
        super(cause);
    }
}
