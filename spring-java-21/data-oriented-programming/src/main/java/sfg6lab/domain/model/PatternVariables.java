//: sfg6lab.domain.model.PatternVariables.java

package sfg6lab.domain.model;


class PatternVariables {

    public static void main(String[] args) {

        System.out.println(numberInfo(10));
        System.out.println(numberInfo(3.14));
        System.out.println(numberInfo(Math.PI * 10));
        System.out.println(numberInfo(Math.PI * 100));
        System.out.println(numberInfo(new Object()));
        System.out.println(numberInfo((Number) null));

        System.out.println(numberInfo(new ZeroNumber()));
    }

    static String numberInfo(Object number) {

        if (number instanceof final AutoCloseable closeable) {
            return "AutoCloseable Object: " + closeable;
        } else if (number instanceof final Integer i) {
            return "Integer: " + i;
        } else if (number instanceof final Double d && d < 10) {
            return "Small Double: " + d;
        } else if (number instanceof final Double d && d >= 10 && d < 100) {
            return "Middle Double: " + d;
        } else if (number instanceof final Double d && d > 100) {
            return "Large Double: " + d;
        } else if (number instanceof final Object obj) {
            return "A plain Object: " + obj;
        }
        // else if (number instanceof String s) {} // Doesn't compile
        else {
            return "Null Number";
        }
    }

    static final class ZeroNumber extends Number {

        @Override
        public int intValue() {
            return 0;
        }

        public ZeroNumber() {
            super();
        }

        @Override
        public byte byteValue() {
            return super.byteValue();
        }

        @Override
        public short shortValue() {
            return super.shortValue();
        }

        @Override
        public long longValue() {
            return 0;
        }

        @Override
        public float floatValue() {
            return 0;
        }

        @Override
        public double doubleValue() {
            return 0;
        }
    }

} /// :~