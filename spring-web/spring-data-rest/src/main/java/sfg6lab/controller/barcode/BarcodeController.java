//: sfg6lab.controller.barcode.BarcodeController.java

package sfg6lab.controller.barcode;


import jakarta.servlet.http.HttpServletResponse;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;


@RestController
class BarcodeController {

    @GetMapping("/barcode")
    ResponseEntity<byte[]> createBarcode(
            @RequestParam String code,
            HttpServletResponse response) throws Exception {

        Code39Bean codeBean = new Code39Bean();
        codeBean.setWideFactor(3);
        codeBean.setIntercharGapWidth(0.2d);
        codeBean.setModuleWidth(UnitConv.in2mm(1.0f / 150));
        codeBean.setBarHeight(15);
        codeBean.setMsgPosition(HumanReadablePlacement.HRP_BOTTOM);
        codeBean.doQuietZone(false);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out,
                    "image/jpeg",
                    150,
                    BufferedImage.TYPE_BYTE_BINARY,
                    false,
                    0);
            codeBean.generateBarcode(canvas, code);
            canvas.finish();

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "image/jpeg");

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

} /// :~