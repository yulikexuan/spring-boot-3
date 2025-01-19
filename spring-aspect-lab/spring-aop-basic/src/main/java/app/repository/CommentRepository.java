//: app.repository.CommentRepository.java

package app.repository;


import app.domain.model.Comment;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public enum CommentRepository {

    INSTANCE;

    private final ConcurrentHashMap<String, Comment> comments =
            new ConcurrentHashMap<>();

    CommentRepository() {}

    public void save(Comment comment) {
        comments.put(comment.author(), comment);
    }

    public Optional<Comment> read(String author) {
        return Optional.ofNullable(comments.get(author));
    }

    public void update(String author, String newText) {
        comments.put(author, Comment.of(author, newText));
    }

    public void delete(String author) {
        comments.remove(author);
    }
}
