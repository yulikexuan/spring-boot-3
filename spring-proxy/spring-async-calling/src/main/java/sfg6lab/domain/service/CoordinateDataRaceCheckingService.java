package sfg6lab.domain.service;


import lombok.RequiredArgsConstructor;
import sfg6lab.domain.model.Coordinate;


@RequiredArgsConstructor(staticName = "of")
public class CoordinateDataRaceCheckingService {

    static final int LOOP_COUNT = Integer.MAX_VALUE / 10;

    static int count;

    private final Coordinate coordinate;

    public void checkDataRace() {
        for (int i = 0; i < LOOP_COUNT; i++) {
            count += this.coordinate.hasDataRace() ? 1 : 0;
        }
    }

    static int count() {
        return count;
    }

}