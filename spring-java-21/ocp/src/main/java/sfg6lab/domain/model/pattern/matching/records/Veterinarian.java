//: sfg6lab.domain.model.pattern.matching.records.Veterinarian.java

package sfg6lab.domain.model.pattern.matching.records;


public class Veterinarian {

    public static void main(String[] args) {

        Fish f1 = new Fish("Nemo");
        Fish f2 = new Fish(Integer.valueOf(1));

        if (f1 instanceof Fish(Object object)) {
            System.out.print("Match1-");
        }

        if (f1 instanceof Fish(String object)) {
            System.out.print("Match2-");
        }

        if (f1 instanceof Fish(Integer object)) {
            System.out.print("Match3-");
        }

        if (f2 instanceof Fish(String object)) {
            System.out.print("Match4-");
        }

        if (f2 instanceof Fish(Number x)) {
            System.out.println("Match5");
        }
    }

    static record Fish(Object type) { }
    // static record Fish(Integer type) { }

} /// :~