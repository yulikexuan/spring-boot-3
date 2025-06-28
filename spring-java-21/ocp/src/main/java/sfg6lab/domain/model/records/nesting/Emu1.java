//: sfg6lab.domain.model.records.nesting.Emu1.java

package sfg6lab.domain.model.records.nesting;


class Emu1 {

    String name = "Emmy";

    static Feathers createFeathers() {
        return new Feathers("grey");
    }

    record Feathers(String color) {
        void fly() {
            System.out.println(">>> %s is flying with color %s."
                    .formatted(new Emu1().name, color));
            // System.out.println(">>> %s is flying.".formatted(name));
        }
    }
}

class Emu2 {

    String name = "Emmy";

    static Feathers createFeathers() {
        return new Emu2().new Feathers();
        // return new Feathers("grey");
    }

    class Feathers {
        void fly() {
            System.out.println(">>> %s is flying.".formatted(name));
        }
    }
}

/// :~