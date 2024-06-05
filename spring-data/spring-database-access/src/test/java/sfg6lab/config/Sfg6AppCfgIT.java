//: sfg6lab.config.Sfg6AppCfgTest.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.springframework.boot.test.context.SpringBootTest.UseMainMethod.WHEN_AVAILABLE;


@Slf4j
@DisplayName("Test DateSource and JdbcTemplate - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
@SpringBootTest(classes = { Sfg6AppCfg.class }, useMainMethod = WHEN_AVAILABLE)
class Sfg6AppCfgIT {

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("itopiaDataSource")
    private DataSource itopiaDataSource;

    @BeforeEach
    void setUp() {
    }

    @Nested
    @DisplayName("Test JdbcTemplate - ")
    class JdbcTemplateTest {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Test
        void jdbcTemplate_Has_DataSource() {

            // Given

            //When
            var actualDataSource = jdbcTemplate.getDataSource();

            // Then
            assertThat(actualDataSource).isSameAs(dataSource);
            assertThat(itopiaDataSource).isNotNull();
        }

        @Test
        void jdbc_Template_Search() {

            // Given
            String sql = "SELECT manelength FROM profile WHERE nickname = ?";
            String nickname = "FillmoreFat";

            // When
            var lengths = jdbcTemplate.queryForList(
                    sql, Integer.class, nickname);

            // Then
            assertThat(lengths).containsExactly(3);
        }

        @Test
        void jdbc_Template_With_Itopia_DataSource() {

            // Given
            String sql = "SELECT COUNT(*) FROM md_user";
            JdbcTemplate itopiaJdbcTemplate = new JdbcTemplate(itopiaDataSource);

            // When
            var counts = itopiaJdbcTemplate.queryForList(sql, Integer.class);

            // Then
            assertThat(counts.get(0)).isGreaterThan(500);
        }

        @Test
        void jdbc_Template_Can_Query_For_Map_Based_On_Column_Names() {

            // Given
            String sql = "SELECT nickname, lastseen FROM profile WHERE id = ?";

            // When
            Map<String, Object> result = jdbcTemplate.queryForMap( sql, 17 );

            // Then
            assertThat(result).hasSize(2).contains(
                    entry("nickname", "ConnieLingus"),
                    entry("lastseen", Timestamp.valueOf("2021-08-23 04:57:58.0")));
        }

        @Test
        void jdbc_Template_Query_Multi_Rows_But_One_Column() {

            // Given
            var sql = """
                SELECT DISTINCT birthdate
                FROM profile
                ORDER BY birthdate
                LIMIT ?
                """;

            LocalDate[] expectedDates = {
                    LocalDate.parse("1971-07-17"),
                    LocalDate.parse("1971-07-31"),
                    LocalDate.parse("1974-09-12"),
                    LocalDate.parse("1974-09-27"),
                    LocalDate.parse("1975-04-05"),
                    LocalDate.parse("1979-07-17"),
                    LocalDate.parse("1980-08-01"),
                    LocalDate.parse("1980-08-16"),
                    LocalDate.parse("1984-12-17"),
                    LocalDate.parse("1987-05-06"),
            };

            // When
            List<LocalDate> dates = jdbcTemplate.queryForList(sql, LocalDate.class, 10 );

            // Then
            assertThat(dates).containsExactly(expectedDates);
        }

        @Test
        void jdbc_Template_Query_Multi_Rows_With_Multi_Columns() {

            // Given
            var date = LocalDate.parse("2021-09-01");
            var sql = """
                SELECT nickname, lastseen
                FROM profile
                WHERE lastseen > ?
                ORDER BY lastseen;
                """;
            var expectedRows = 8;

            // When
            var nicknames = jdbcTemplate.queryForList(sql, date).stream()
                    .map(col ->  new NicknameLastseen(
                            (String) col.get("nickname"),
                            ((Timestamp) col.get("lastseen")).toLocalDateTime()))
                    .toList();

            // Then
            assertThat(nicknames).hasSize(expectedRows);
        }

        @Test
        void jdbc_Template_Query_With_RowMapper() {

            // Given
            var date = LocalDate.parse("2021-09-01");
            var sql = """
                SELECT nickname, lastseen
                FROM profile
                WHERE lastseen > ?
                ORDER BY lastseen;
                """;
            var expectedRows = 8;

            // When
            List<NicknameLastseen> nicknames =
                    jdbcTemplate.query(
                            sql,
                            (rs, __) -> new NicknameLastseen(
                                    rs.getString("nickname"),
                                    rs.getTimestamp("lastseen")
                                            .toLocalDateTime()),
                            date);

            // Then
            assertThat(nicknames).hasSize(expectedRows);
        }

        @Test
        void jdbc_Template_Query_With_DataClassRowMapper() {

            // Given
            var date = LocalDate.parse("2021-09-01");
            var sql = """
                SELECT nickname, lastseen
                FROM profile
                WHERE lastseen > ?
                ORDER BY lastseen;
                """;
            var expectedRows = 8;

            final var nicknameLastseenRowMapper =
                    new DataClassRowMapper<>(NicknameLastseen.class);

            // When
            List<NicknameLastseen> nicknames = jdbcTemplate.query(
                    sql, nicknameLastseenRowMapper, date);

            // Then
            assertThat(nicknames).hasSize(expectedRows);
        }

        @Test
        void jdbc_Template_Query_With_DataClassRowMapper_Not_Match_Col_Names() {

            // Given
            var date = LocalDate.parse("2021-09-01");
            var sql = """
                SELECT nickname AS name, lastseen AS lastSeen
                FROM profile
                WHERE lastSeen > ?
                ORDER BY lastSeen;
                """;
            var expectedRows = 8;

            final var nicknameLastseenRowMapper =
                    new DataClassRowMapper<>(NameLastSeen.class);

            // When
            List<NameLastSeen> nicknames = jdbcTemplate.query(
                    sql, nicknameLastseenRowMapper, date);

            // Then
            assertThat(nicknames).hasSize(expectedRows);
        }

        @Test
        void jdbc_Query_For_Stream() {

            // Given
            var date = LocalDate.parse("2021-09-01");
            var sql = """
                SELECT nickname, lastseen
                FROM profile
                WHERE lastseen > ?
                ORDER BY lastseen;
                """;
            var expectedRows = 8;

            var nicknameLastseenRowMapper = new DataClassRowMapper<>(
                    NicknameLastseen.class);

            // When
            try (var names = jdbcTemplate.queryForStream(
                    sql, nicknameLastseenRowMapper, date)) {

                assertThat(names).hasSize(expectedRows);
            }
        }

        @Test
        void jdbc_Template_Query_With_Row_Callback_Handler() {

            // Given
            var date = LocalDate.parse("2021-09-01");
            var sql = """
                SELECT nickname 
                FROM profile
                ORDER BY nickname LIMIT ?;
                """;
            var expectedRows = 8;

            final var nameHandler = new StringJoiner(
                    ", ", ">>> Meet ", " and so many more!");

            /*
             * RowCallbackHandler used by JdbcTemplate and NamedParameterJdbcTemplate
             * for processing rows of a ResultSet on a Per-Row Basis
             */
            RowCallbackHandler rowHandler = rs -> {
                var names = nameHandler.add(rs.getString("nickname"));
            };

            // When
            jdbcTemplate.query(sql, rowHandler, expectedRows);

            // Then
            assertThat(nameHandler.toString()).isEqualTo(
                    ">>> Meet AdamZapel, AliGaither, AmyStake, CandyKane, " +
                            "CariMysack, ChrisCross, ConnieLingus, DanSing and so many more!");
        }

        @Test
        void jdbc_Template_Insert_With_PreparedStatementCreator_And_KeyHolder() {

            // Given
            var sql = """
            INSERT INTO photo (name, is_profile_photo, created, profile_fk)
            VALUES(?, ?, ?, ?);""";

            var profileFK = 12;
            var name = "unicorn001";
            var isProfilePhoto = false;
            var created = LocalDateTime.now();

            PreparedStatementCreator preparedStatementCreator =
                    connection -> {
                        PreparedStatement pstmp = connection.prepareStatement(
                                sql, Statement.RETURN_GENERATED_KEYS);
                        pstmp.setString(1, name);
                        pstmp.setBoolean(2, isProfilePhoto);
                        pstmp.setTimestamp(3, Timestamp.valueOf(created));
                        pstmp.setInt(4, profileFK);

                        return pstmp;
                    };

            KeyHolder keyHolder = new GeneratedKeyHolder();

            // When
            jdbcTemplate.update(preparedStatementCreator, keyHolder);
            // Number key = keyHolder.getKey(); // Does not work with Postgres

            var keys = keyHolder.getKeys();

            // Then
            System.out.println(keys);
        }

    }

    static record NicknameLastseen(String nickname, LocalDateTime lastseen) {}

    static record NameLastSeen(String name, LocalDateTime lastSeen) {}

}///:~