//: sfg6lab.domain.model.DataSharing.java

package sfg6lab.domain.model;


class DataSharing {

    public static void main(String[] args) {

        int x = 1;
        int y = 2;

        var result = sum(x, y);

        System.out.println(">>> The result is: " + result);
    }

    private static int sum(int a, int b) {

        int s = a + b;

        return s;
    }

} ///:~