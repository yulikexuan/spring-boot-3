//: sfg6lab.domain.model.NumberFormatter.java

package sfg6lab.domain.model;


import lombok.NonNull;

import java.text.NumberFormat;
import java.util.Locale;


class NumberFormatter {

    private final NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);

    String formatInteger(@NonNull final String numStr) {
        long num = Long.parseLong(numStr);
        var result = formatter.format(num);
        return result;
    }

} ///:~