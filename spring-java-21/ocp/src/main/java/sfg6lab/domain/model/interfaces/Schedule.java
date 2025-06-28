//: sfg6lab.domain.model.interfaces.Schedule.java

package sfg6lab.domain.model.interfaces;


public interface Schedule {

    default void wakeUp() {
        checkTime(7);
    }

    private void haveBreakfast() {
        checkTime(9);
    }

    static void workOut() {
        checkTime(18);
    }

    private static void checkTime(int hour) {
        if (hour > 17) {
            System.out.println(">>> You are late!");
        } else {
            System.out.println(">>> You have %d hours left to make the appointment!"
                    .formatted(17 - hour));
        }
    }

}
