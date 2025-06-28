//: sfg6lab.domain.model.sealing.Fish.java

package sfg6lab.domain.model.sealing;


sealed class Fish permits Trout, Bass {

    static String showFish(Fish fish) {
        return switch (fish) {
            case Trout t -> "Trout!";
            case Bass b -> "Bass!";
            case Fish f -> "Fish!";
        };
    }

    public static void main(String[] args) {
        System.out.println(showFish(new Trout()));
        System.out.println(showFish(new Bass()));
        System.out.println(showFish(new Fish()));
    }

} /// :~