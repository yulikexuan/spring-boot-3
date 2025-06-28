//: sfg6lab.domain.model.enums.FragileOperation.java

package sfg6lab.domain.model.enums;


enum FragileOperation {

    PLUS, MINUS, TIMES, DIVIDE;

    double apply(double x, double y) {

        return switch (this) {
            case PLUS -> x + y;
            case MINUS -> x - y;
            case TIMES -> x * y;
            case DIVIDE -> x / y;
            default -> throw new AssertionError("Unknown OP: " + this);
        };
    }

} /// :~