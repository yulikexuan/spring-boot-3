//: app.aspect.AuthorizationFailedException.java

package app.aspect;


public class AuthorizationFailedException extends RuntimeException {

    public AuthorizationFailedException(String message) {
        super(message);
    }

    public String message() {
        return super.getMessage();
    }

} /// :~