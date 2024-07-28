//: sfg6lab.config.MemberEmbededMailIT.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import sfg6lab.domain.model.Mail;
import sfg6lab.domain.model.Member;
import sfg6lab.repository.MemberEmbededMailRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
class MemberEmbededMailIT extends Sfg6SpringDataJdbcIT {

    private static final int EXPECTED_NUMBER_OF_RECORDS = 10;
    private static final String DEFAULT_TEAM = "itopia-app";

    @Autowired
    private MemberEmbededMailRepository repository;

    @BeforeAll
    void beforeAll() {
        repository.saveAll(initializeDb());
    }

    @AfterAll
    void afterAll() {
        repository.deleteAll();
    }

    @Test
    void able_To_Create_Multi_Members_At_Once() {
        assertThat(repository.count()).isEqualTo(EXPECTED_NUMBER_OF_RECORDS);
    }

    @ParameterizedTest
    @CsvSource({
            "'mike@somedomain.com', '2 5th Avenue', 'New York'",
            "'katie@somedomain.com', '4 5th Avenue', 'New York'",
            "'stephanie@somedomain.com', '9 5th Avenue', 'New York'"})
    void one_Member_Has_Only_One_Mail_Address(String email, String street, String city) {

        // Given

        // When
        Optional<Member> memberOpt = repository.findByEmail(email);

        // Then
        assertThat(memberOpt).isPresent();

        // When
        Mail mailAddress = memberOpt.get().mail();
        Member member = memberOpt.get();
        LocalDateTime createdAt = member.created();

        // Then
        assertThat(mailAddress).extracting("street", "city")
                .containsExactly(street, city);

        // When
        String team = member.team();

        // Then
        assertThat(team).isEqualTo(DEFAULT_TEAM);

        // Given
        LocalDateTime newCreatedTime = LocalDateTime.of(
                2024, Month.JULY, 28, 0, 0);
        Member newMember = Member.of(member, newCreatedTime);

        // When
        repository.save(newMember);

        // Then
        assertThat(repository.findByEmail(email).get())
                .extracting("created")
                .as(">>> The created filed is insert only, not able to be modified.")
                .isEqualTo(createdAt);
    }

    private List<Member> initializeDb() {

        return List.of(
                Member.of(null, // team cannot be persisted
                        "john",
                        "john@somedomain.com",
                        1,
                        true,
                        // created time can be persisted but not updated
                        LocalDateTime.of(2020, Month.APRIL, 13, 0, 0),
                        "1 5th Avenue","New York"),
                Member.of(null, // team,
                        "mike",
                        "mike@somedomain.com",
                        3,
                        true,
                        LocalDateTime.of(2020, Month.JANUARY, 18, 0, 0),
                        "2 5th Avenue",
                        "New York"),
                Member.of(null, // team,
                        "james",
                        "james@somedomain.com",
                        3,
                        false,
                        LocalDateTime.of(2020, Month.MARCH, 11, 0, 0),
                        "3 5th Avenue",
                        "New York"),
                Member.of(null, // team,
                        "katie",
                        "katie@somedomain.com",
                        5,
                        true,
                        LocalDateTime.of(2021, Month.JANUARY, 5, 0, 0),
                        "4 5th Avenue",
                        "New York"),
                Member.of(null, // team,
                        "beth",
                        "beth@somedomain.com",
                        2,
                        true,
                        LocalDateTime.of(2020, Month.AUGUST, 3, 0, 0),
                        "5 5th Avenue",
                        "New York"),
                Member.of(null, // team,
                        "julius",
                        "julius@somedomain.com",
                        4,
                        true,
                        LocalDateTime.of(2021, Month.FEBRUARY, 9, 0, 0),
                        "6 5th Avenue",
                        "New York"),
                Member.of(null, // team,
                        "darren",
                        "darren@somedomain.com",
                        2,
                        true,
                        LocalDateTime.of(2020, Month.DECEMBER, 11, 0, 0),
                        "7 5th Avenue",
                        "New York"),
                Member.of(null, // team,
                        "marion",
                        "marion@somedomain.com",
                        2,
                        false,
                        LocalDateTime.of(2020, Month.SEPTEMBER, 23, 0, 0),
                        "8 5th Avenue",
                        "New York"),
                Member.of(null, // team,
                        "stephanie",
                        "stephanie@somedomain.com",
                        4,
                        true,
                        LocalDateTime.of(2020, Month.JANUARY, 18, 0, 0),
                        "9 5th Avenue",
                        "New York"),
                Member.of(null, // team,
                        "burk",
                        "burk@somedomain.com",
                        1,
                        true,
                        LocalDateTime.of(2020, Month.NOVEMBER, 28, 0, 0),
                        "10 5th Avenue",
                        "New York"));
    }

} ///:~