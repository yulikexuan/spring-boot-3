//: sfg6lab.shell.commands.ProfileCommands.java


package sfg6lab.shell.commands;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import sfg6lab.config.Sfg6ServerConfigurationProperties;
import sfg6lab.domain.model.DbConnectionFactory;

import java.util.List;


@ShellComponent
@RequiredArgsConstructor
class ProfileCommands {

    private final List<DbConnectionFactory> dbConnectionFactories;
    private final Sfg6ServerConfigurationProperties sfg6ServerConfigurationProperties;

    @ShellMethod(key = "max-file-size", value = "Show the maximum file size of uploading")
    String maxFileSize() {
        long size = this.sfg6ServerConfigurationProperties.server()
                .maxFileSize()
                .toMegabytes();

        return ">>> The maximum file size for uploading is %d MB".formatted(size);
    }

    @ShellMethod(key = "log-appender", value = "Show logger appender")
    String loggerAppender() {
        return this.sfg6ServerConfigurationProperties.logger().appender();
    }

    @ShellMethod(key = "db-url", value="Show URL of Data Source")
    String dbUrl() {
        return this.sfg6ServerConfigurationProperties.dbSource().url();
    }

    @ShellMethod(key = "db-init", value="Initialize Database Connections")
    void initDbConnections() {
        this.dbConnectionFactories.forEach(DbConnectionFactory::init);
    }

}///:~