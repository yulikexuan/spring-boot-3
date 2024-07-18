//: sfg6lab.domain.model.Signal.java

package sfg6lab.domain.model;


public sealed interface Signal permits TrafficLight {
}

enum TrafficLight implements Signal {

    RED,
    GREEN,
    YELLOW;

}
