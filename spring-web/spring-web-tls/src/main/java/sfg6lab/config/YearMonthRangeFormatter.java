//: sfg6lab.config.YearMonthRangeFormatter.java

package sfg6lab.config;


import org.springframework.format.Formatter;
import sfg6lab.domain.model.YearMonthRange;

import java.text.ParseException;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Pattern;


public class YearMonthRangeFormatter implements Formatter<YearMonthRange> {

    @Override
    public String print(YearMonthRange range, Locale __) {

        return range.start() + "~" + range.end();
    }

    @Override
    public YearMonthRange parse(String s, Locale __) throws ParseException {

        try {
            YearMonth[] startEnd = Pattern.compile("~").splitAsStream(s)
                    .map(YearMonth::parse)
                    .toArray(YearMonth[]::new);
            return new YearMonthRange(startEnd[0], startEnd[1]);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage(), e.getErrorIndex());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Insufficient number of values", 0);
        }
    }

} ///:~