//: sfg6lab.controller.FileUploadingController.java

package sfg6lab.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
class FileUploadingController {

    static final String MSG_TEMPLATE = """
            { "size": %d }
            """;

    @PostMapping("/data/files")
    public ResponseEntity<?> receiveFiles(@RequestParam MultipartFile file1) {
        try {
            long size = file1.getBytes().length;
            return ResponseEntity.ok(MSG_TEMPLATE.formatted(size));
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

} ///:~