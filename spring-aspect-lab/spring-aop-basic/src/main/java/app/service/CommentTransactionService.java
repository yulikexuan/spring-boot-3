//: app.service.CommentService


package app.service;


import app.domain.model.Comment;

import java.util.Optional;


/**
 * Can only update, delete, or read the latest comment
 * The length of the Author's name must be at least 4 charactors long
 */
public interface CommentTransactionService {

    String publish(Comment comment);

    String update(String author, String newText);

    String delete(String author);

    Optional<Comment> read(String author);

}///:~