package springlab.service;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Conditional(LowDiskSpaceCondition.class)
public @interface ConditionalOnLowDiskFreeSpace {
}
