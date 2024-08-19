//: sfg6lab.config.Sfg6AppCfgTransactionTemplateTest.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.UseMainMethod.WHEN_AVAILABLE;


// @formatter:off
@Slf4j
@DisplayName("Test TransactionTemplate - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(classes = { Sfg6AppCfg.class }, useMainMethod = WHEN_AVAILABLE)
class Sfg6AppCfgTransactionTemplateTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String sql;

    @Test
    void able_To_Execute_Transaction_With_Transaction_Tempalte() {

        // Given
        sql = """
              UPDATE profile SET manelength = manelength + :manelength
              WHERE nickname = :name
              """;

        // When
        Integer numOfUpdated = transactionTemplate.execute(txnStatus -> {
            int rowsUpdated = 0;
            rowsUpdated += namedParameterJdbcTemplate.update(
                    sql,
                    new MapSqlParameterSource()
                            .addValue("manelength", 1)
                            .addValue("name", "FillmoreFat"));
            rowsUpdated += namedParameterJdbcTemplate.update(
                    sql,
                    new MapSqlParameterSource()
                            .addValue("manelength", 1)
                            .addValue("name", "AdamZapel"));
            return rowsUpdated;
        });

        // Then
        assertThat(numOfUpdated).isEqualTo(2);
    }

    @Test
    void verify_Transaction_Continuation() {

        // Given
        int hashCode_0 = connHashCode();
        int hashCode_1 = connHashCode();

        // When
        var connHashCodes = transactionTemplate.execute(
                txnStatus -> {
                    Connection conn = DataSourceUtils.getConnection(dataSource);
                    int hashCode_2_0 = System.identityHashCode(conn);
                    int hashCode_2_1 = connHashCode();
                    int hashCode_2_2 = connHashCode();
                    DataSourceUtils.releaseConnection(conn, dataSource);
                    return List.of(hashCode_2_0, hashCode_2_1, hashCode_2_2);
                });

        // Then
        assertThat(hashCode_0).isNotEqualTo(hashCode_1);
        assertThat(hashCode_1).isNotEqualTo(connHashCodes.getFirst());
        assertThat(Set.copyOf(connHashCodes)).hasSize(1);
    }

    private Integer connHashCode() {
        return transactionTemplate.execute(txnStatus -> {
            Connection conn = DataSourceUtils.getConnection(dataSource);
            int h = System.identityHashCode(conn);
            DataSourceUtils.releaseConnection(conn, dataSource);
            return h;
        });
    }

} ///:~