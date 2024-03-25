//: sfg6lab.domain.model.ProductDbConnectionFactory.java


package sfg6lab.domain.model;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class ProductDbConnectionFactory implements DbConnectionFactory {

    private final DbSource dbSource;

    @Override
    public void init() {
        log.debug(">>> [PRODUCT] Initializing Database Connections ...");
    }

}///:~