//: app.service.LoggingService.java


package app.service;


import app.aspect.ToLogging;
import app.domain.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class LoggingService {

    public void info(Comment comment) {
        log.info(">>> Publishing INFO: {}", comment.text());
    }

    @ToLogging
    public void error(Comment comment) {
        log.info(">>> Publishing ERROR: {}", comment.text());
    }

    public void warning(Comment comment) {
        log.warn(">>> Publishing WARNING: {}", comment.text());
    }

}///:~