//: springlab.service.LowDiskSpaceCondition.java


package springlab.service;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.unit.DataSize;

import java.io.File;


class LowDiskSpaceCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context,
                           AnnotatedTypeMetadata metadata) {

        return DataSize.ofBytes(new File("C:\\").getFreeSpace())
                .toGigabytes() < 200;
    }

}///:~