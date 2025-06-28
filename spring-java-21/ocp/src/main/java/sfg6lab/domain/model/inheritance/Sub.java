//: sfg6lab.domain.model.inheritance.Sub.java

package sfg6lab.domain.model.inheritance;


import java.time.Instant;


final class Sub extends Super {

    private final Instant instant;

    public Sub() {
        System.out.println("[Sub] >>> Constructing Sub ... ");
        this.instant = Instant.now();
    }

    @Override
    public void overrideMe() {
        System.out.println("[Sub] >>> The instant of Sub is " + instant);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }

} /// :~