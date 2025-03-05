//: sfg6lab.config.TestingCondition.java

package sfg6lab.config;


import lombok.NonNull;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Optional;


class TestingCondition implements Condition {

    @Override
    public boolean matches(
            @NonNull final ConditionContext context,
            AnnotatedTypeMetadata metadata) {

        return Optional.ofNullable(context.getEnvironment()
                        .getProperty("spirng.profiles.active"))
                .map(pv -> pv.contains("test"))
                .orElse(false);
    }

} /// :~