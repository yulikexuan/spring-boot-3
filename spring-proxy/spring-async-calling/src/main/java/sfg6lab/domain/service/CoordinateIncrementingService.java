package sfg6lab.domain.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import sfg6lab.domain.model.Coordinate;


@RequiredArgsConstructor(staticName = "of")
public class CoordinateIncrementingService {

    static final int LOOP_COUNT = Integer.MAX_VALUE / 10;

    private final Coordinate coordinate;

    public void increment() {
        for (int i = 0; i < LOOP_COUNT; i++) {
            coordinate.increment();
        }
    }

}