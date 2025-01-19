//: app.controller.ApiExceptionHandler.java

package app.controller;


import app.aspect.AuthorizationFailedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(basePackageClasses = CommentController.class)
class ApiExceptionHandler {

    @ExceptionHandler(AuthorizationFailedException.class)
    public ResponseEntity<?> handleAuthorizationFailedException(
            AuthorizationFailedException ex) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("!!! You are accessing an unavailable resource! !!!");
    }

} /// :~