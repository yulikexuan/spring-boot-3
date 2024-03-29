//: sfg6lab.service.SystemInfoLoggingService.java


package sfg6lab.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@Service
@RequiredArgsConstructor
class SystemInfoLoggingService {

    private final ThreadLocalRandom random;
    private final List<String> javaInfo;

    @Scheduled( cron = "*/5 * * * * *" )
    void logSystemInfo() {

        int javaIndex = random.nextInt(0, this.javaInfo.size());

        log.info(">>> {}", javaInfo.get(javaIndex));
    }

}///:~