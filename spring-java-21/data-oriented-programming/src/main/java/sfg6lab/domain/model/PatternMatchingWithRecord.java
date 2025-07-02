//: sfg6lab.domain.model.PatternMatchingWithRecord.java

package sfg6lab.domain.model;


import java.util.ArrayList;
import java.util.List;


class PatternMatchingWithRecord {

    public static void main(String[] args) {
        // printAnimalInfo(new Monkey("Joseph", 10));
        Veterinarian.checkFishes();
    }

    static void printAnimalInfo(Object animal) {
        // Defines a pattern that is compatible with the Monkey record
        if (animal instanceof Monkey(final String name, final int age)) {
            System.out.println(">>> Hello monkey %s! You are %d years old now."
                    .formatted(name, age));
        }
    }

}

record Monkey(String name, int age) {
    public Monkey {
        if (age < 0) {
            throw new IllegalArgumentException(">>> Age must be non-negative.");
        }
    }
}

record HomeFish(Object type) {}
class Veterinarian {

    static <T> void checkFishes() {

        HomeFish hf1 = new HomeFish("Nemo");
        HomeFish hf2 = new HomeFish(Integer.valueOf(1));

        if (hf1 instanceof HomeFish(Object t)) {
            System.out.print(">>> Match1-");
        }

        if (hf1 instanceof HomeFish(String t)) {
            System.out.print(">>> Match2-");
        }

        if (hf1 instanceof HomeFish(Integer t)) {
            System.out.print(">>> Match3-");
        }

        if (hf2 instanceof HomeFish(String t)) {
            System.out.print(">>> Match4-");
        }

        if (hf2 instanceof HomeFish(Integer x)) {
            System.out.print(">>> Match5");
        }

        System.out.println();
    }
}

record Bear(String name, List<String> favoriteThings) {}
record Couple(Bear a, Bear b) {}

class Bears {

    void matchBears() {

        var c = new Couple(
                new Bear("Yogi", List.of("PicnicBaskets")),
                new Bear("Fozzie", List.of("BadJokes")));

        if (c instanceof Couple(Bear a, Bear b)) {
            System.out.print(a.name() + " " + b.name());
        }
        if (c instanceof Couple(
                Bear(String firstName, List<String> f), Bear b)) {
            System.out.print(firstName + " " + b.name());
        }

        if (c instanceof Couple(
                Bear(String name, List<String> f1),
                // Bear(String name, List<String> f2) // DOES NOT COMPILE
                Bear(String name2, List<String> f2))) {

            System.out.print(name + " " + name);
        }
    }

    void matchVarBears() {
        var c = new Couple(new Bear("Yogi", List.of("PicnicBaskets")),
                new Bear("Fozzie", List.of("BadJokes")));
        if (c instanceof Couple(var a, var b)) {
            System.out.print(a.name() + " " + b.name());
        }
        if (c instanceof Couple(Bear(var firstName, List<String> f), var b)) {
            System.out.print(firstName + " " + b.name());
        }
    }

    void matchGenericBears() {

        var c = new Couple(new Bear("Yogi", List.of("PicnicBaskets")),
                new Bear("Fozzie", List.of("BadJokes")));

        if (c instanceof Couple(Bear(var n, Object f),                       var b)) {}
        if (c instanceof Couple(Bear(var n, List f),                         var b)) {}
        if (c instanceof Couple(Bear(var n, List<?> f),                      var b)) {}
        if (c instanceof Couple(Bear(var n, List<? extends Object> f),       var b)) {}
        if (c instanceof Couple(Bear(var n, List<? extends CharSequence> f), var b)) {}
        if (c instanceof Couple(Bear(var n, ArrayList<String> f),            var b)) {}

        // if(c instanceof Couple(Bear(var n, List<> f),       var b)) {} // DOES NOT COMPILE
        // if(c instanceof Couple(Bear(var n, List<Object> f), var b)) {} // DOES NOT COMPILE

        if (c instanceof Couple(Bear(var n, List f), var b)
                // && f.getFirst().toLowerCase().contains("p") // DOES NOT COMPILE
          ) { System.out.print("Yummy"); }
    }

}

record CuteSnake(Object data) {}

class MatchingCuteSnake {

    long showCuteSnake(CuteSnake snake) {
        return switch(snake) {
            case CuteSnake(Long hiss)      -> hiss + 1;
            case CuteSnake(Integer nagina) -> nagina + 10;
            case CuteSnake(Number crowley) -> crowley.intValue() + 100;
            case CuteSnake(Object kaa)     -> -1;
        };
    }

    long showSimpleCuteSnake(CuteSnake snake) {
        return switch(snake) {
            // case CuteSnake(Object kaa) when kaa.doubleValue() > 0 -> -1; // DOES NOT COMPILE
            default -> 1_000; // It's Optional
        };
    }
}



/// :~