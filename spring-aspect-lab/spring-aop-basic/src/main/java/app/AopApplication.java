package app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {

        SpringApplication.run(AopApplication.class, args);

        // var context = new AnnotationConfigApplicationContext(AppConfig.class);
        // CommentService cs = context.getBean(CommentService.class);
        // cs.publishComment(Comment.of("YUL", "New Spring Boot Book published!"));

        // LoggingService loggingService = context.getBean(LoggingService.class);
        //
        // loggingService.info(Comment.of("YUL", "Normal Info!"));
        //
        // loggingService.warning(Comment.of("YUL", "Warning Info!"));
        //
        // loggingService.error(Comment.of("YUL", "Error Info!"));
    }

}
