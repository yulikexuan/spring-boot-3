//: springlab.web.controller.ContextsController.java


package springlab.web.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import springlab.domain.model.Parent;


@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class ContextsController {

    private final Parent parent;

    public Parent parent() {
        return parent;
    }

}///:~