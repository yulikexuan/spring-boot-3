//: sfg6lab.domain.model.Coordinate.java

package sfg6lab.domain.model;


public class Coordinate {

    private volatile int x;
    private volatile int y;

    public void increment() {
        this.x++;
        this.y++;
    }

    public boolean hasDataRace() {
        return y > x;
    }

} ///:~