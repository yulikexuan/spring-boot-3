//: sfg6lab.domain.model.Member.java

package sfg6lab.domain.model;


import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.InsertOnlyProperty;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Table("members")
public record Member(
        @Id Long id,
        @ReadOnlyProperty String team, // not be persisted
        @NonNull String name,
        @NonNull String email,
        int level,
        boolean active,
        @NonNull @InsertOnlyProperty LocalDateTime created,
        @Embedded.Nullable
        Mail mail) {

    public static Member of(String team,
                            @NonNull String name,
                            @NonNull String email,
                            int level,
                            boolean active,
                            @NonNull LocalDateTime created,
                            String street,
                            String city) {

        return new Member(null,
                team, name, email, level, active, created,
                Mail.of(street, city));
    }

    public static Member of(@NonNull final Member member,
                            @NonNull final LocalDateTime created) {
        return new Member(
                member.id,
                member.team,
                member.name,
                member.email,
                member.level,
                member.active,
                created,
                member.mail);
    }
}
