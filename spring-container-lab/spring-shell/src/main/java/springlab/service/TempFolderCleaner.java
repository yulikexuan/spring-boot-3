//: springlab.service.TempFolderCleaner.java


package springlab.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@ConditionalOnLowDiskFreeSpace
public class TempFolderCleaner {

    TempFolderCleaner() {
        log.info(">>> Cleaning TEMP directory to acquire more free diskspace ... ");
    }

}///:~