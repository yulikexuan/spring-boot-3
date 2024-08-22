//: sfg6lab.domain.service.Quotes.java

package sfg6lab.domain.service;


import org.springframework.stereotype.Service;


final class BuddhaQuoteService implements QuoteService {

    private static final String[] QUOTES = {
            "Peace comes from within. Do not seek it without.",
            "Better than a thousand hollow words is one word that brings peace.",
            "Hatred does not cease by hatred, but only by love; this is the eternal rule.",
            "Those who are free of resentful thoughts surely find peace.",
            "If you truly loved yourself, you could never hurt another.",
            "The greatest prayer is patience.",
            "Peace comes from understanding and accepting that which is." };

    @Override
    public String quote(int index) {
        return QUOTES[index];
    }

} ///:~