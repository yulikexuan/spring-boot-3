//: sfg6lab.service.InvalidDbConnectionUrlException.java


package sfg6lab.service;


public class InvalidDbConnectionUrlException extends RuntimeException {

    public InvalidDbConnectionUrlException() {
        super();
    }

    public InvalidDbConnectionUrlException(String msg) {
        super(msg);
    }

    public InvalidDbConnectionUrlException(String msg, Throwable cause) {
        super(msg, cause);
    }

}///:~