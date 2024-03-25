//: sfg6lab.domain.model.DevDbConnectionFactory.java


package sfg6lab.domain.model;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class DevDbConnectionFactory implements DbConnectionFactory {

    private final DbSource dbSource;

    @Override
    public void init() {
        log.debug(">>> [DEV] Initializing Database Connections ...");
    }

}///:~