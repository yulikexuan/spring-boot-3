//: sfg6lab.domain.model.interfaces.Walk.java

package sfg6lab.domain.model.interfaces;


public interface Walk {
    default int getSpeed() {
        return 5;
    }
}
