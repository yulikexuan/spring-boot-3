//: sfg6lab.config.Sfg6AppCfgTest.java

package sfg6lab.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.support.AbstractInterruptibleBatchPreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.IntStream;

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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParamJdbcTemplate;

    @BeforeEach
    void setUp() {
    }

    @Nested
    @DisplayName("Test JdbcTemplate - ")
    class JdbcTemplateTest {

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
            assertThat(lengths).isNotEmpty();
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
        static record NicknameLastseen(String nickname, LocalDateTime lastseen) {}
        static record NameLastSeen(String name, LocalDateTime lastSeen) {}

    }

    @Nested
    @DisplayName("Test NamedParameterJdbcTemplate - ")
    class NamedParameterJdbcTemplateTest {
        private String sql;


        @Test
        void able_To_Use_Map_To_Set_Parameter_Value() {

            // Given
            sql = "SELECT manelength FROM Profile WHERE nickname = :name";

            // When
            Integer manelength = namedParamJdbcTemplate.queryForObject(
                    sql,
                    Map.ofEntries(entry("name", "FillmoreFat")),
                    Integer.class);

            // Then
            assertThat(manelength).isGreaterThan(0);
        }

        @Test
        void able_To_Use_Bean_To_Set_Parameter_Value() {

            // Given
            sql = "SELECT manelength FROM Profile WHERE nickname = :name";
            SqlParameterSource params = new BeanPropertySqlParameterSource(
                    new NicknameParameterSource());

            // When
            Integer manelength = namedParamJdbcTemplate.queryForObject(
                    sql, params, Integer.class);

            // Then
            assertThat(manelength).isGreaterThan(0);
        }

        @Test
        void should_Be_Able_To_Access_Connection_With_Callback() {

            // Given

            // When
            var dbProductName = namedParamJdbcTemplate.getJdbcTemplate()
                    .execute(new ConnectionPropertys());

            // Then
            assertThat(dbProductName).isEqualTo("PostgreSQL");
        }

        static class NicknameParameterSource {
            public String getName() {
                return "FillmoreFat";
            }
        }

        static class ConnectionPropertys implements ConnectionCallback<Object> {

            @Override
            public Object doInConnection(@NonNull final Connection connection)
                    throws SQLException, DataAccessException {

                return connection.getMetaData().getDatabaseProductName();
            }

        }
    }

    @Nested
    @DisplayName("Test Batch Operations of JdbcTemplate")
    class BatchOperationsTest {

        private String sql;

        private int maneLengthChange;

        private List<ManeLengthUpdate> maneLengthUpdates;

        @BeforeEach
        void setUp() {
            sql = "UPDATE profile SET manelength = manelength + ? WHERE id = ?";
        }

        @Test
        void update_In_Batch_With_BatchPreparedStatementSetter_Implementation() {

            // Given
            maneLengthChange = 5;
            maneLengthUpdates = IntStream.rangeClosed(1, 5)
                            .mapToObj(i -> new ManeLengthUpdate(i, maneLengthChange))
                            .toList();

            final var maneLengthuUpdate = new BatchPreparedStatementSetter() {
                @Override
                public void setValues(
                        @NonNull final PreparedStatement ps,
                        final int i) throws SQLException {

                    ps.setInt(1, maneLengthUpdates.get(i).manelength());
                    ps.setInt(2, maneLengthUpdates.get(i).id());
                }
                @Override
                public int getBatchSize() {
                    return maneLengthUpdates.size();
                }
            };

            // When
            int[] updated = jdbcTemplate.batchUpdate(sql, maneLengthuUpdate);

            // Then
            assertThat(updated).hasSize(maneLengthUpdates.size());
        }

        @Test
        void update_In_Batch_With_AbstractInterruptibleBatchPreparedStatementSetter() {

            // Given
            maneLengthChange = -5;
            maneLengthUpdates = IntStream.rangeClosed(1, 5)
                            .mapToObj(i -> new ManeLengthUpdate(i, maneLengthChange))
                            .toList();

            final var bpss = new AbstractInterruptibleBatchPreparedStatementSetter() {
                @Override
                protected boolean setValuesIfAvailable(
                        @NonNull final PreparedStatement ps, final int index)
                        throws SQLException {

                    if (index >= maneLengthUpdates.size()) {
                        return false;
                    }

                    ps.setInt(1, maneLengthUpdates.get(index).manelength());
                    ps.setInt(2, maneLengthUpdates.get(index).id());

                    return true;
                }
            };

            // When
            int[] updated = jdbcTemplate.batchUpdate(sql, bpss);

            // Then
            assertThat(updated).hasSize(maneLengthUpdates.size());
        }

        @Test
        void udate_In_Batch_With_Implementing_ParameterizedPreparedStatementSetter() {

            // Given
            maneLengthChange = 5;
            maneLengthUpdates = IntStream.rangeClosed(1, 5)
                            .mapToObj(i -> new ManeLengthUpdate(i, maneLengthChange))
                            .toList();

            final var paramPreparedStatementSetter =
                    new ParameterizedPreparedStatementSetter<ManeLengthUpdate>() {
                        @Override
                        public void setValues(
                                @NonNull final PreparedStatement ps,
                                @NonNull final ManeLengthUpdate data) throws SQLException {

                            ps.setInt(1, data.id());
                            ps.setInt(2, data.manelength());
                        }
                    };

            // When
            var updated = jdbcTemplate.batchUpdate(
                    sql,
                    maneLengthUpdates,
                    maneLengthUpdates.size(),
                    paramPreparedStatementSetter);

            // Then
            assertThat(updated).hasDimensions(1, 5);
        }

        @Test
        void update_In_Batch_With_NamedParameterJdbcTemplate_And_MapSqlParameterSource() {

            // Given
            sql = "UPDATE profile SET manelength = manelength + :manelength WHERE id = :id";

            maneLengthChange = 5;
            maneLengthUpdates = IntStream.rangeClosed(1, 5)
                    .mapToObj(i -> new ManeLengthUpdate(i, maneLengthChange))
                    .toList();

            var args = maneLengthUpdates.stream()
                    .map(u -> new MapSqlParameterSource()
                            .addValue("manelength", u.manelength())
                            .addValue("id", u.id()))
                    .toArray(MapSqlParameterSource[]::new);

            // When
            var updated = namedParamJdbcTemplate.batchUpdate(sql, args);

            // Then
            assertThat(updated).hasSize(5);
        }

        @Test
        void update_In_Batch_With_NamedParameterJdbcTemplate_And_SqlParameterSourceUtil() {

            // Given
            sql = "UPDATE profile SET manelength = manelength + :manelength WHERE id = :id";

            maneLengthChange = -5;
            maneLengthUpdates = IntStream.rangeClosed(1, 5)
                    .mapToObj(i -> new ManeLengthUpdate(i, maneLengthChange))
                    .toList();

            var args =
                    SqlParameterSourceUtils.createBatch(maneLengthUpdates);

            // When
            var updated = namedParamJdbcTemplate.batchUpdate(sql, args);

            // Then
            assertThat(updated).hasSize(5);
        }

        static record ManeLengthUpdate(int id, int manelength) {}
    }

}///:~