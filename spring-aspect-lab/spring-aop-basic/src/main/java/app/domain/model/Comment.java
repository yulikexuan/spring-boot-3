//: app.domain.model.Comment.java


package app.domain.model;


import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;


@Value
@Accessors(fluent = true)
@AllArgsConstructor(staticName = "of")
public class Comment {

    String author;

    String text;

}///:~