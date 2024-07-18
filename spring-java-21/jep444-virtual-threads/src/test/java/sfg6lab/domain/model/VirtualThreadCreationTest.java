//: sfg6lab.domain.model.VirtualThreadCreationTest.java

package sfg6lab.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.*;

import java.util.concurrent.ThreadFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Virtual Thread Creation - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VirtualThreadCreationTest {

    static final String VIRTUAL_THREAD_NAME_1 = "Virtual-Thread-1";

    private VtPackage vtPackage;

    @BeforeEach
    void setUp() {
        this.vtPackage = new VtPackage(false);
    }

    @Test
    void create_A_Virtual_Thread_With_Of_Virtual() throws Exception {

        // Given
        String runningInfo = ">>> %s is running ... ".formatted(
                VIRTUAL_THREAD_NAME_1);
        assertThat(vtPackage.done()).isFalse();

        // When
        Thread vt = Thread.ofVirtual().name(VIRTUAL_THREAD_NAME_1)
                .start(() -> vtPackage.done(true));

        // Then
        assertThat(vt.isVirtual()).isTrue();

        vt.join();
        assertThat(vtPackage.done()).isTrue();
        assertThat(vt.isDaemon()).isTrue();
    }

    @Test
    void start_Virtual_Thread_Directly_With_Runnable_Task() throws Exception {

        // Given
        assertThat(vtPackage.done()).isFalse();

        // When
        var vt = Thread.startVirtualThread(() -> vtPackage.done(true));

        // Then
        assertThat(vt.isVirtual()).isTrue();

        vt.join();
        assertThat(vtPackage.done()).isTrue();
        assertThat(vt.isDaemon()).isTrue();
    }

    @Test
    void create_Virtual_Thread_With_Thread_Factory() throws Exception {

        // Given
        ThreadFactory tf = Thread.ofVirtual().factory();
        assertThat(vtPackage.done()).isFalse();

        // When
        var vt = tf.newThread(() -> vtPackage.done(true));
        vt.start();

        // Then
        assertThat(vt.isVirtual()).isTrue();

        vt.join();
        assertThat(vtPackage.done()).isTrue();
        assertThat(vt.isDaemon()).isTrue();
    }

    @Test
    void virtual_Thread_Has_Empty_Name_By_Default() throws Exception {

        // Given
        Thread vt = Thread.ofVirtual().start(() -> vtPackage.done(true));

        // When
        vt.join();

        // Then
        assertThat(vt.getName()).isEmpty();
        assertThat(vt.isDaemon()).isTrue();
    }

    @Test
    void virtual_Threads_Are_Always_Daemon_Threads() throws Exception {

        // Given
        Thread vt = Thread.ofVirtual().start(() -> vtPackage.done(true));

        // When
        vt.join();

        // Then
        assertThat(vt.isDaemon()).isTrue();
    }

    @Test
    void not_Able_To_Make_A_Virtual_Thread_Non_Daemon() {

        // Given
        var vt = Thread.ofVirtual().unstarted(() -> vtPackage.done(true));

        // When
        // Then
        assertThatThrownBy(() -> vt.setDaemon(false))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Data
    @AllArgsConstructor
    @Accessors(fluent = true)
    static class VtPackage {
        boolean done;
    }

} ///:~