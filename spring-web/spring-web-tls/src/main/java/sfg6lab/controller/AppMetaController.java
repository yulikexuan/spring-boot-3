//: sfg6lab.controller.AppMetaController.java

package sfg6lab.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/app/meta")
@RequiredArgsConstructor
class AppMetaController {

    private final Resource appIconResource;

    /*
     * Specify that it produces SVG XML content with
     * (value = "/image", produces = MediaType.IMAGE_SVG_XML_VALUE)
     *  Encapsulates the image into a Resource object, and then returns it as a
     * InputStreamResource.
     *
     * The image should thus be placed within src/main/resources/images directory
     * The MediaType.valueOf(MediaType.IMAGE_SVG_XML_VALUE) sets the Content-Type
     * header in the HTTP response to image/svg+xml so the client browsers would
     * understand that an svg image is being returned and render it accordingly
     * Please replace mySVGImage.svg with the actual name of your SVG image file.
     * If you wish to parameterize the image file name, you can add a PathVariable
     * to the method like so:
     */
    @GetMapping("/icon")
    public ResponseEntity<InputStreamResource> getAppIcon() throws IOException {

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/svg+xml"))
                .body(new InputStreamResource(appIconResource.getInputStream()));
    }

} ///:~