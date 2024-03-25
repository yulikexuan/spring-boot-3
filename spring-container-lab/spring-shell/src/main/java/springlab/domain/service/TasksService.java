//: springlab.domain.service.TasksService.java


package springlab.domain.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import springlab.domain.model.Task;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
public class TasksService {

    private final List<Task> tasks;

    public void processTasks() {
        log.info(">>> We are processing tasks {} ", this.tasks);
    }

}///:~