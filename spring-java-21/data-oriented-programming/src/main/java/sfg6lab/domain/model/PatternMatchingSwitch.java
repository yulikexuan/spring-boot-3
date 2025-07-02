//: sfg6lab.domain.model.PatternMatchingSwitch.java

package sfg6lab.domain.model;


class PatternMatchingSwitch {

    public static void main(String[] args) {
        System.out.println(caseNullSwitchVariable());
    }

    static void printDetails(Number height) {
        String message = switch (height) {
            case Integer i when (i < 0) -> "Negative Int: " + i;
            case Integer i when (i == 0) -> "ZERO";
            case Integer i -> "Rounded: " + i;
            case Double d  -> "Precise: " + d;
            case Number n  -> "Unknown: " + n;
        };
        System.out.print(message);
    }

    static void printDetails2(Number height) {
        String message = switch (height) {
            case Number n  -> "Unknown: " + n;
            // case Integer i -> "Rounded: " + i; // DOES NOT COMPILE
            // case Double d  -> "Precise: " + d; // DOES NOT COMPILE
        };
        System.out.print(message);
    }

    static String getTrainer(Number animal) {
        return switch (animal) {
            case Integer i             -> "Daniel";
            // case Integer i when i > 10 -> "Joseph"; // DOES NOT COMPILE
            case Number n             -> "Unknown";
        };
    }

    static void doNotCombineFullyExhaustiveSwitchWithDefaultCase(Number zooPatrons) {
        switch (zooPatrons) {
            case Integer count -> System.out.print("Welcome: " + count);
            case Number count  -> System.out.print("Too many people at the zoo!"); // Already Exhaustive
            // default            -> System.out.print("The zoo is closed"); // DOES NOT COMPILE
        }
    }

    static String nullSwitchVariableThrowNPE() {
        String fish = null;
        return switch (fish) {
            case "ClownFish" -> "Hello!";
            case "BlueTang" -> "Hello again!";
            default -> "Goodbye";
        };
    }

    static String caseNullSwitchVariable() {
        String fish = null;
        return switch (fish) {
            case "ClownFish" -> "Hello!";
            case "BlueTang"  -> "Hello again!";
            case null        -> "Goodbye";
            default          -> "Unknown fish";
        };
    }

} /// :~