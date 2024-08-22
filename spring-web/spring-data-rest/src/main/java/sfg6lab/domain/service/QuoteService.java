//: sfg6lab.domain.service.QuoteService.java

package sfg6lab.domain.service;


public interface QuoteService {

    String quote(int index);

    static QuoteService buddhaQuoteService() {
        return new BuddhaQuoteService();
    }
}
