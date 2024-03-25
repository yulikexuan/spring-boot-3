//: sfg6lab.shell.commands.ConfigPropertiesCommand.java


package sfg6lab.shell.commands;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import sfg6lab.config.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Slf4j
@ShellComponent
@AllArgsConstructor
class Sfg6ConfigPropertiesCommand {

    private final Sfg6EmailConfigProperties sfg6EmailConfigProperties;
    private final Sfg6ConfigProperties sfg6ConfigProperties;
    private final ObjectMapper objMapper;

    @ShellMethod(key="email", value = "Show email config")
    public Email email() {
        return sfg6EmailConfigProperties.email();
    }

    @ShellMethod(key = "app", value = "Show App")
    public App app() {
        return this.sfg6ConfigProperties.app();
    }

    @ShellMethod(key = "server", value = "Show server configuration")
    public Server server() {
        return this.sfg6ConfigProperties.server();
    }

    @ShellMethod(key = "author", value = "Show the author")
    public Author author() {
        return this.sfg6ConfigProperties.author();
    }

    @ShellMethod(key = "author-json", value = "Show author configuration with JSON format")
    public String authorInJson() {
        return toJson(this.sfg6ConfigProperties.author());
    }

    @ShellMethod(key = "server-json", value = "Show server configuration with JSON format")
    public String serverInJson() {
        return toJson(this.sfg6ConfigProperties.server());
    }

    @ShellMethod(key = "locations", value = "Show the locations of server")
    public List<Location> locations() {
        return this.sfg6ConfigProperties.server().locations();
    }

    @ShellMethod(key = "sfg6", value = "Show sfg6 application properties")
    public Sfg6ConfigProperties sfg6Properties() {
        return this.sfg6ConfigProperties;
    }

    @ShellMethod(key = "tp", value = "Show ports for testing")
    public List<Integer> serverPortsToTest() {
        return this.sfg6ConfigProperties.server().portsToTest();
    }

    @ShellMethod(key = "tu", value = "Show time unit for repetition")
    public TimeUnit repetitionUnit() {
        return this.sfg6ConfigProperties.server().repetitionUnit();
    }

    @ShellMethod(key = "xfs", value = "Show the maximum file size can be uploaded")
    public String maxFileSize() {
        return this.sfg6ConfigProperties.server().maxFileSize().toKilobytes() + "KB";
    }

    @ShellMethod(key = "stom", value = "Show session timeout in minutes")
    public String sessionTimeoutInMinutes() {
        return this.sfg6ConfigProperties.server().sessionTimeout().toMinutes() +
                " Minutes";
    }

    @ShellMethod(key = "spd", value = "Show statistic period in days")
    public String statisticPeriod() {
        return this.sfg6ConfigProperties.server().statisticPeriod().getDays() + " Days";
    }

    String toJson(Object obj) {

        String json = "";

        try {
            json = objMapper
                    //.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);

        } catch (Exception e) {
            log.error(">>> Failed to do JSON Conversion", e);
        }

        return json;
    }

}///:~