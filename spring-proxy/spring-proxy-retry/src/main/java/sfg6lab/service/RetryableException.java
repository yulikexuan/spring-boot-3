//: sfg6lab.service.RetryableException.java


package sfg6lab.service;


public class RetryableException extends RuntimeException {

    public RetryableException(String message) {
        super(message);
    }

    public RetryableException(Throwable cause) {
        super(cause);
    }

    public RetryableException(String message, Throwable cause) {
        super(message, cause);
    }

}///:~