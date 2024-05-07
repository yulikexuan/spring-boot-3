//: sfg6lab.domain.model.DataRace.java

package sfg6lab.domain.model;


class DataRace {

    public static final int LOOP_COUNT = Integer.MAX_VALUE / 4;

    public static void main(String[] args) {

        final var sharedData = new SharedData();

        Thread incrementingThread = new Thread(() -> {
            for (int i = 0; i < LOOP_COUNT; i++) {
                sharedData.increment();
            }
        });

        Thread checkingThread = new Thread(() -> {
            for (int i = 0; i < LOOP_COUNT; i++) {
                sharedData.checkDataRace();
            }
        });

        incrementingThread.start();
        checkingThread.start();
    }

    static class SharedData {

        private volatile int x;
        private volatile int y;

        void increment() {

            this.x++;
            this.y++;
        }

        void checkDataRace() {

            if (y > x) {
                System.out.println(
                        ">>> Data race is detected! x = %d, y = %d"
                                .formatted(x, y));
            }
        }

    }

} ///:~