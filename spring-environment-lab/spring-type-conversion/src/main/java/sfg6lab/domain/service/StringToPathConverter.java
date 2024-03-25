//: sfg6lab.domain.service.StringToPathConverter.java


package sfg6lab.domain.service;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.nio.file.Path;


public class StringToPathConverter implements Converter<String, Path> {

    @Override
    public Path convert(@NonNull final String location) {
        return Path.of(location);
    }

}///:~