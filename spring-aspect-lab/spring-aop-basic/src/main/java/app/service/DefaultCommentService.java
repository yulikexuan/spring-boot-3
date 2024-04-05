//: app.service.CommentServiceImpl.java


package app.service;


import app.domain.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
final class DefaultCommentService implements CommentService {

    @Override
    public String publishComment(Comment comment) {
        log.info(">>> Publishing Completed! {} ", comment);
        return "SUCCESS";
    }

}///:~