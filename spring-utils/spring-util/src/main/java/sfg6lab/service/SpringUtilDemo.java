//: sfg6lab.domain.model.SpringUtilDemo.java


package sfg6lab.service;


import com.google.common.collect.Lists;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Slf4j
@Data
@Service
@NoArgsConstructor
@Accessors(fluent = true)
public class SpringUtilDemo {

    private final List<Map<String, Object>> data = Lists.newArrayList();

    @PostConstruct
    public void begin() {
        log.info(">>> The beginning after running the constructor of {}",
                this.getClass());
    }

}///:~