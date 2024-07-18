//: sfg6lab.domain.model.ThreadLocalWithVirtualThreadTest.java

package sfg6lab.domain.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Virtual Thread Creation - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ThreadLocalWithVirtualThreadTest {

    private int rootVal;
    private int parentVal;
    private InheritableThreadLocal<Integer> itl;

    private ThreadLocalRandom rand;

    @BeforeEach
    void setUp() {
        rand = ThreadLocalRandom.current();
        rootVal = rand.nextInt(1000, 9999);
        parentVal = rand.nextInt(10000, 99999);
        itl = new InheritableThreadLocal<>();
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void able_To_Inherite_Thread_Local(final boolean valInheritable)
            throws InterruptedException {

        itl.set(rootVal);
        Thread.ofVirtual()
                .name("parent")
                .start(() -> this.parentTask(valInheritable))
                .join();
    }

    private void parentTask(boolean valInheritable) {
        assertThat(itl.get()).isEqualTo(rootVal);
        parentVal = rand.nextInt(10000, 99999);
        itl.set(parentVal);
        try {
            Thread.ofVirtual()
                    .name("Child")
                    .inheritInheritableThreadLocals(valInheritable)
                    .start(() -> this.childTask(valInheritable))
                    .join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void childTask(boolean valInheritable) {
        Integer expectedInheritableVal =
                valInheritable ? parentVal : null;
        assertThat(itl.get()).isEqualTo(expectedInheritableVal);
    }

} ///:~