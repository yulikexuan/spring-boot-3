//: sfg6lab.command.TestDrivenController.java


package sfg6lab.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import sfg6lab.domain.model.Profile;
import sfg6lab.domain.service.MvnLocalRepositoryService;


@Service
@RequiredArgsConstructor
public class TestDrivenMvnService {

    private final Profile profile;
    private final MvnLocalRepositoryService mvnLocalRepositoryService;

    public String mvnHome() {
        return mvnLocalRepositoryService.root().toString();
    }

    public String freeDiskSize() {
        long mb = DataSize.ofBytes(this.mvnLocalRepositoryService.freeDiskSpace())
                .toGigabytes();
        return ">>> The free disk size is %d GB".formatted(mb);
    }

    private Profile profile() {
        return this.profile;
    }

    String username() {
        return this.profile.username();
    }

}///:~