//: sfg6lab.domain.model.pattern.matching.records.Zoo.java

package sfg6lab.domain.model.pattern.matching.records;


public class Zoo {

    public static void main(String[] args) {
        Object animal = new Monkey(new StringBuilder().toString(), 3);
        if (animal instanceof Monkey(CharSequence name, int age)) {
            System.out.println(">>> Hello %s! You are %d years old."
                    .formatted(name, age));
        }
    }
}

record Monkey(String name, int age) {

}

/// :~