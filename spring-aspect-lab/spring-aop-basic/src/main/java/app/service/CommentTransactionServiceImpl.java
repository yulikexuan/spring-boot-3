//: app.service.CommentServiceImpl.java


package app.service;


import app.aspect.CommentTransaction;
import app.domain.model.Comment;
import app.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Can only update, delete, or read the latest comment
 * The length of the Author's name must be at least 4 charactors long
 */
@Slf4j
@Service
public class CommentTransactionServiceImpl implements CommentTransactionService {

    @Override
    @CommentTransaction
    public String publish(Comment comment) {
        log.info(">>> Processing Comment {} ... ", comment);
        CommentRepository.INSTANCE.save(comment);
        return "SUCCESS";
    }

    @Override
    @CommentTransaction
    public String update(String author, String newText) {
        CommentRepository.INSTANCE.update(author, newText);
        return "SUCCESS";
    }

    @Override
    @CommentTransaction
    public String delete(String author) {
        CommentRepository.INSTANCE.delete(author);
        return "SUCCESS";
    }

    @Override
    @CommentTransaction
    public Optional<Comment> read(String author) {
        return CommentRepository.INSTANCE.read(author);
    }

}///:~