//: sfg6lab.domain.model.PatternMatchingWithSealedClass.java

package sfg6lab.domain.model;


class PatternMatchingWithSealedClass {

    static enum FishType { TROUT, BASS, TUNA}

    public static void main(String[] args) {
        System.out.println(">>> Have a " + getFishType(null) + " Fis");
        printFishType(null);
    }

    static String getFishType(final Fish fish) {
        return switch (fish) {
            case Trout tr -> "Trout";
            case Bass bs  -> "Bass";
            case null -> "Null Fish";
            // default -> "Unknown fish"; // Being Optional
        };
    }

    static void printFishType(FishType fishType) {
        switch (fishType) {
            case TROUT -> System.out.println("Trout");
            case BASS  -> System.out.println("Bass");
            case TUNA -> System.out.println("Tuna");
            case null -> System.out.println("Null Fish");
            // default -> System.out.println("Unknown fish"); // Being Optional
        }
    }

}

abstract sealed class Fish permits Trout, Bass {}

final class Trout extends Fish {}

final class Bass extends Fish {}

/// :~