//: sfg6lab.domain.model.IntParser.java


package sfg6lab.domain.model;


public class IntParser {

    public int parseInt(String input) {
        return parseInt(input, 10);
    }

    public int parseInt(@Trim String input, int radix) {
        return Integer.parseInt(input, radix);
    }

}///:~