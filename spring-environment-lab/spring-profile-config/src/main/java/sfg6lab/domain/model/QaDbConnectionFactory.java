//: sfg6lab.domain.model.QaDbConnectionFactory.java


package sfg6lab.domain.model;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class QaDbConnectionFactory implements DbConnectionFactory {

    private final DbSource dbSource;

    @Override
    public void init() {
        log.debug(">>> [QA] Initializing Database Connections ...");
    }

}///:~