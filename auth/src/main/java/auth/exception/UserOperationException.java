package auth.exception;

public class UserOperationException extends RuntimeException {
    private static final long serialVersionUID = 8934110222539192856L;

    public UserOperationException(String msg) {
        super(msg);
    }
}
