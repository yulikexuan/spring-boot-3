//: sfg6lab.domain.service.PhotoService.java


package sfg6lab.domain.service;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import sfg6lab.domain.model.Photo;

import java.util.Optional;


@Validated
@AllArgsConstructor
public class PhotoService {

    private FileDownloadingService fileDownloadingService;

    public Optional<byte[]> download(@Valid Photo photo) {
        return this.fileDownloadingService.download(photo.name());
    }

}///:~