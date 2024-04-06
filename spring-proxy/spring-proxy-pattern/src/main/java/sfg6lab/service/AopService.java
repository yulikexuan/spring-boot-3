//: sfg6lab.service.AopService


package sfg6lab.service;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


public interface AopService {

    void serveAop();

    static AopService instance() {
        return AopServiceImpl.of();
    }
}


@Slf4j
@NoArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class AopServiceImpl implements AopService {

    @Override
    public void serveAop() {
        log.debug(">>> Calling serveAop in {}", this.getClass().getName());
    }

}

///:~