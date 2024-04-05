//: sfg6lab.domain.model.AssertTest.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.*;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


@DisplayName("Test Util Assert Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AssertTest {

    private Car car;
    private ThreadLocalRandom random;

    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.random = ThreadLocalRandom.current();
    }

    @Test
    void has_Logical_Assertions() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> car.drive(this.random.nextInt(-10, -1)))
                .withMessage(Car.ERR_MSG_SPEED);
    }

    @Test
    void has_Object_And_Type_Assertions() {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> car.changeOil(null))
                .withMessage(Car.ERR_MSG_OIL);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> car.changeBattery(new Battery(50)))
                .withMessage(Car.ERR_MSG_BATTERY);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> car.changeEngine(new HondaEngine()))
                .withMessageStartingWith(Car.ERR_MSG_ENGINE);

        Assert.isAssignable(Engine.class, ToyotaEngine.class);
        Assert.isAssignable(Engine.class, HondaEngine.class);
    }

    @Test
    void has_Text_Assertions() {

        // Given
        String errMsgNullOrEmpty = ">>> Message's length must not be 0";
        String errMsgBlank = ">>> Message must not be blank";
        String errMsgWithNumber = ">>> Message must not contain numbers";

        // Assert that the given String is not empty; that is,
        // it must not be null and not the empty String.
        Assert.hasLength(Car.ERR_MSG_BATTERY, errMsgNullOrEmpty);

        String empty = "";
        String blank = "    ";
        String withNumbers = ">>> Today is 2024-04-01";

        // When & Then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Assert.hasLength(empty, errMsgNullOrEmpty))
                .withMessage(errMsgNullOrEmpty);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Assert.hasLength(null, errMsgNullOrEmpty))
                .withMessage(errMsgNullOrEmpty);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Assert.hasText(blank, errMsgBlank))
                .withMessage(errMsgBlank);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Assert.doesNotContain(
                        withNumbers,
                        "2024-04-01",
                        () -> errMsgWithNumber))
                .withMessage(errMsgWithNumber);
    }

    @Test
    void has_Array_And_Collection_And_Map_Assertions() {

        // Assert::notEmpty -> assert that a collection contains elements
        // that is it must not be null and must contain at least one element

        // Given
        String errMsgEmptyNote = "Note must not be null or empty";
        String errMsgEmptyToDo = "The ToDo list must not be null or empty";
        String errMsgNullElement = "The list must not contain null element";

        List<String> emptyNote = List.of();

        Map<LocalDate, String> toDoList = Map.ofEntries(
                Map.entry(LocalDate.now(), "REST"),
                Map.entry(LocalDate.now().plusDays(1), "WASH")
        );

        Map<LocalDate, String> emptyToDoList = Map.of();

        String nullName = null;
        List<String> nullElementList = Lists.newArrayList(nullName, "name");

        // When
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Assert.notEmpty((List<String>)null, () -> errMsgEmptyNote))
                .withMessage(errMsgEmptyNote);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Assert.notEmpty(emptyNote, () -> errMsgEmptyNote))
                .withMessage(errMsgEmptyNote);

        Assert.notEmpty(toDoList, () -> errMsgEmptyToDo);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Assert.notEmpty((Map<LocalDate, String>)null,
                        () -> errMsgEmptyToDo))
                .withMessage(errMsgEmptyToDo);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Assert.notEmpty(emptyToDoList, () -> errMsgEmptyToDo))
                .withMessage(errMsgEmptyToDo);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Assert.noNullElements(nullElementList,
                        ()-> errMsgNullElement))
                .withMessage(errMsgNullElement);

    }

    /**************************************************************************/

    static class Car {

        static final String ERR_MSG_SPEED = ">>> Car speed must be positive.";

        static final String ERR_MSG_OIL = ">>> Car oil must not be null.";

        static final String ERR_MSG_BATTERY = ">>> New Battery should not be charged";

        static final String ERR_MSG_ENGINE = ">>> New Engine must be Toyota";

        private String state = "stop";

        void drive(int speed) {
            Assert.isTrue(speed > 0, ERR_MSG_SPEED);
            this.state = "drive";
        }

        void changeOil(String oil) {
            Assert.notNull(oil, ERR_MSG_OIL);
        }

        String state() {
            return state;
        }

        void changeBattery(Battery battery) {
            Assert.isNull(battery.charge(), ERR_MSG_BATTERY);
        }

        void changeEngine(Engine engine) {
            Assert.isInstanceOf(ToyotaEngine.class, engine, ERR_MSG_ENGINE);
        }
        
    }

    static class Battery {

        public enum ChargeLevel {
            FULL, HALF
        }
        private int percentLeft = 100;

        Battery(int percentLeft) {
            this.percentLeft = percentLeft;
        }

        public int percentLeft() {
            return this.percentLeft;
        }

        public ChargeLevel charge() {
            return this.percentLeft >= 85 ? null : ChargeLevel.HALF;
        }

    }

    static interface Engine {
        void start();
        void stop();
    }

    @Slf4j
    static class ToyotaEngine implements Engine {

        @Override
        public void start() {
            log.info(">>> Start Toyota Engine ... ");
        }

        @Override
        public void stop() {
            log.info(">>> Stop Toyota Engine ... ");
        }

    }

    @Slf4j
    static class HondaEngine implements Engine {

        @Override
        public void start() {
            log.info(">>> Start Honda Engine ... ");
        }

        @Override
        public void stop() {
            log.info(">>> Stop Honda Engine ... ");
        }

    }

}///:~