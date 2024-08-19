//: sfg6lab.controller.FileController.java

package sfg6lab.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sfg6lab.domain.model.FileName;


@RestController
@RequestMapping("/files")
class FileController {

    /*
     * The regex expression "[-\\w]+" matches a sequence of one or more
     * characters where each character is a hyphen, a letter (A-Z, a-z),
     * a digit (0-9), or an underscore _.
     *
     * Contains two URI variables here, and the regex expressions act as a filter
     */
    @GetMapping("/{name:[-\\w]+}.{suffix:\\w+}")
    public ResponseEntity<FileName> getFile(
            @PathVariable String name, @PathVariable String suffix) {

        return ResponseEntity.ok(new FileName(name, suffix));
    }

} ///:~