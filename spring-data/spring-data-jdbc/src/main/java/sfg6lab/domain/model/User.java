//: sfg6lab.domain.model.User.java

package sfg6lab.domain.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table("users")
@NoArgsConstructor
public class User {

    @Id
    @Column("id")
    private Long id;

    @Column("username")
    private String usrename;

    @Column("created")
    private LocalDateTime created;

} ///:~