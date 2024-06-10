//: sfg6lab.config.SfgAppCfgJdbcTransactionIT.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import javax.sql.DataSource;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.UseMainMethod.WHEN_AVAILABLE;


@Slf4j
@DisplayName("Test TransactionManager - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(classes = { Sfg6AppCfg.class }, useMainMethod = WHEN_AVAILABLE)
class SfgAppCfgTransactionManagerIT {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private TransactionStatus transactionStatus;

    private int hashCode1;
    private int hashCode2;

    @BeforeEach
    void setUp() {

        // Initialize Transaction
        // Establishes a connection
        // Sets the auto-commit feature to false
        // The Connection for the transaction is associated with the thread
        // that invokes getTransaction()
        transactionStatus = transactionManager.getTransaction(null);

        hashCode1 = -1;
        hashCode2 = -1;
    }

    @Test
    void should_Always_Have_A_New_Connection_From_DataSource() {

        // Given

        // When
        try {
            Connection con1 = this.dataSource.getConnection();
            hashCode1 = System.identityHashCode(con1);
            JdbcUtils.closeConnection(con1);

            Connection con2 = this.dataSource.getConnection();
            hashCode2 = System.identityHashCode(con2);
            JdbcUtils.closeConnection(con2);

            transactionManager.commit(transactionStatus);
        } catch (Throwable  e) {
            transactionManager.rollback(transactionStatus);
        }

        // Then
        assertThat(hashCode1).isNotEqualTo(hashCode2);
    }

    /*
     * When the DataSourceUtils.getConnection() method is called,
     * it retrieves the Connection from the thread-local variable
     *
     * Allowing DataSourceUtils.getConnection() and DataSourceTransactionManager
     * to share the Connection
     */
    @Test
    void should_Always_Have_An_Existing_Connection_From_DataSourceUtil() {

        // Given

        // When
        try {
            Connection con1 = DataSourceUtils.getConnection(dataSource);
            hashCode1 = System.identityHashCode(con1);
            DataSourceUtils.releaseConnection(con1, dataSource); // Not Closed

            Connection con2 = DataSourceUtils.getConnection(dataSource);
            hashCode2 = System.identityHashCode(con2);
            DataSourceUtils.releaseConnection(con2, dataSource); // Not Closed

            transactionManager.commit(transactionStatus);
        } catch (Throwable  e) {
            transactionManager.rollback(transactionStatus);
        }

        // Then
        assertThat(hashCode1).isEqualTo(hashCode2);
    }

} ///:~