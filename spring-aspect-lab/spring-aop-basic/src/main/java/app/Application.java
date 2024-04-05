package app;


import app.config.AppConfig;
import app.domain.model.Comment;
import app.service.CommentService;
// import app.service.LoggingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        CommentService cs = context.getBean(CommentService.class);

        cs.publishComment(Comment.of(
                "YUL", "New Spring Boot Book published!"));

        // LoggingService loggingService = context.getBean(LoggingService.class);
        //
        // loggingService.info(Comment.of("YUL", "Normal Info!"));
        //
        // loggingService.warning(Comment.of("YUL", "Warning Info!"));
        //
        // loggingService.error(Comment.of("YUL", "Error Info!"));
    }

}
