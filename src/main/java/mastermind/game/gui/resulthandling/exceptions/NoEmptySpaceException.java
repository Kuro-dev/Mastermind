package mastermind.game.gui.resulthandling.exceptions;

public class NoEmptySpaceException extends RuntimeException {
    public NoEmptySpaceException() {
        super();
    }

    public NoEmptySpaceException(String message) {
        super(message);
    }

    public NoEmptySpaceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEmptySpaceException(Throwable cause) {
        super(cause);
    }

    protected NoEmptySpaceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
