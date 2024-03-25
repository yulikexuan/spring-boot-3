//: sfg6lab.shell.commands.SpringEnvCommands.java


package sfg6lab.shell.commands;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Arrays;


@ShellComponent
@RequiredArgsConstructor
class EnvironmentPropertiesCommands {

    static final String USER_HOME_KEY = "user.home";
    static final String JAVA_VERSION_KEY = "java.version";
    static final String MAX_HTTP_HEADER_SIZE = "sfg6.server.max-http-request-header-size";

    private final Environment environment;;

    @Value("${user.home}")
    private final String userHome;

    @Value("${java.version}")
    private final String javaVersion;

    @Value("${sfg6.server.max-http-request-header-size}")
    private int headerSize;

    @Value("${sfg6.server.port:8088}")
    private String serverPort;

    @Value("${string-with-blank-default:}")
    private String stringWithBlankDefault;

    @Value("${boolean-with-default:true}")
    private boolean booleanWithDefault;

    @Value("${string-array-with-default:one,two,three}")
    private String[] stringArrayWithDefaults;

    @Value("${int-array-with-default:1,2,3}")
    private int[] intArrayWithDefaults;

    @Value("${string-or-default-null:#{null}}")
    private String stringOrDefaultNull;

    static final String DEFAULT_VALUES_TEMPLATE =
            "String with blank default: %s%n" +
                    "Boolean with default: %b%n" +
                    "String array with defaults: %s%n" +
                    "Integer array with defaults: %s%n" +
                    "String or default null: %s";

    @ShellMethod(key = "default", value = "Show default values")
    public String defaultPropertyValues() {
        return DEFAULT_VALUES_TEMPLATE.formatted(
                "'" + this.stringWithBlankDefault + "'",
                this.booleanWithDefault,
                Arrays.stream(this.stringArrayWithDefaults).toList().toString(),
                Arrays.stream(this.intArrayWithDefaults)
                        .boxed().toList().toString(),
                this.stringOrDefaultNull);
    }

    @ShellMethod(key = "hello", value = "Show Greeting Message")
    public String hello() {
        return ">>> Hi, Welcome to the Spring-6-Environment Lab!";
    }

    @ShellMethod(key = "home", value = "Show the value of system property user.home")
    public String userHome() {
        return this.userHome;
        // return this.environment.getProperty(USER_HOME_KEY);
    }

    @ShellMethod(key = "jdkv", value = "Show the value of system property java.version")
    public String javaVersion() {
        // return this.environment.getProperty(JAVA_VERSION_KEY);
        return javaVersion;
    }

    @ShellMethod(key = "mhs", value = "Show the maximum value of http request header size")
    public int maxHttpHeaderSize() {
        // return this.environment.getRequiredProperty(
        // MAX_HTTP_HEADER_SIZE, Integer.class);

        return this.headerSize;
    }

    @ShellMethod(key = "port", value = "Show the server port number")
    public String portNumber() {
        return this.serverPort;
    }

}///:~