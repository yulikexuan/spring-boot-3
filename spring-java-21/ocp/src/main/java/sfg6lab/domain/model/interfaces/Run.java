//: sfg6lab.domain.model.interfaces.Run.java

package sfg6lab.domain.model.interfaces;


public interface Run {
    default int getSpeed() {
        return 10;
    }
}
