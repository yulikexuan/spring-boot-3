//: springlab.service.ExpressionParsingService.java


package springlab.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
public class ExpressionParsingService {

    private final ExpressionParser expressionParser;

    public String evaluate(final String expression) {

        if (StringUtils.isBlank(expression)) {
            return ">>> There is no any Expression to evaluate. ";
        }

        return this.expressionParser.parseExpression(expression).getValue().toString();
    }

}///:~