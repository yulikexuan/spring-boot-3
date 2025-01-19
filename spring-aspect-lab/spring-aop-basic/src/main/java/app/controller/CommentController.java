//: app.controller.CommentController.java

package app.controller;


import app.domain.model.Comment;
import app.domain.model.PublishResult;
import app.service.CommentTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/aop/comments")
@RequiredArgsConstructor
class CommentController {

    private final CommentTransactionService commentTransactionService;

    @PostMapping("/publish")
    ResponseEntity<PublishResult> publishComment(@RequestBody Comment comment) {

        var result = commentTransactionService.publish(comment);
        var pr = new PublishResult(result);
        return  (pr.result().equals("SUCCESS")) ?
                ResponseEntity.ok(pr) :
                ResponseEntity.unprocessableEntity().body(pr);
    }

} /// :~