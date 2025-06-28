//: sfg6lab.domain.model.interfaces.Bunny.java

package sfg6lab.domain.model.interfaces;


class Bunny implements Hop {

    void printDetails() {
        System.out.println(">>> Jump Height is : %d cm".formatted(
                Hop.getJumpHeight()));
    }

} /// :~