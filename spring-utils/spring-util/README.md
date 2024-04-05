# The Basic of Spring Util

## To Show The Environment Properties

### [Package org.springframework.util](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/package-summary.html)

### Configuration ` application.yml `

``` 
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

### Access ` http://localhost:8080/actuator/env `

``` 
{
  "activeProfiles": [],
  "propertySources": [
    {
      "name": "server.ports",
      "properties": {
        "local.server.port": {
          "value": "******"
        }
      }
    },
    {
      "name": "servletContextInitParams",
      "properties": {}
    },
    {
      "name": "systemProperties",
      "properties": {
        "java.specification.version": {
          "value": "******"
        },
        "sun.cpu.isalist": {
          "value": "******"
        },
        "sun.jnu.encoding": {
          "value": "******"
        },
        "java.class.path": {
          "value": "******"
        },
        "java.vm.vendor": {
          "value": "******"
        },
        "sun.arch.data.model": {
          "value": "******"
        },
        "user.variant": {
          "value": "******"
        },
        "java.vendor.url": {
          "value": "******"
        },
        "catalina.useNaming": {
          "value": "******"
        },
        "user.timezone": {
          "value": "******"
        },
        "java.vm.specification.version": {
          "value": "******"
        },
        "os.name": {
          "value": "******"
        },
        "sun.java.launcher": {
          "value": "******"
        },
        "user.country": {
          "value": "******"
        },
        "sun.boot.library.path": {
          "value": "******"
        },
        "spring.application.admin.enabled": {
          "value": "******"
        },
        "sun.java.command": {
          "value": "******"
        },
        "com.sun.management.jmxremote": {
          "value": "******"
        },
        "jdk.debug": {
          "value": "******"
        },
        "spring.liveBeansView.mbeanDomain": {
          "value": "******"
        },
        "sun.cpu.endian": {
          "value": "******"
        },
        "user.home": {
          "value": "******"
        },
        "user.language": {
          "value": "******"
        },
        "java.specification.vendor": {
          "value": "******"
        },
        "java.version.date": {
          "value": "******"
        },
        "java.home": {
          "value": "******"
        },
        "spring.output.ansi.enabled": {
          "value": "******"
        },
        "file.separator": {
          "value": "******"
        },
        "java.vm.compressedOopsMode": {
          "value": "******"
        },
        "line.separator": {
          "value": "******"
        },
        "java.vm.specification.vendor": {
          "value": "******"
        },
        "java.specification.name": {
          "value": "******"
        },
        "intellij.debug.agent": {
          "value": "******"
        },
        "FILE_LOG_CHARSET": {
          "value": "******"
        },
        "java.awt.headless": {
          "value": "******"
        },
        "user.script": {
          "value": "******"
        },
        "sun.management.compiler": {
          "value": "******"
        },
        "java.runtime.version": {
          "value": "******"
        },
        "user.name": {
          "value": "******"
        },
        "spring.jmx.enabled": {
          "value": "******"
        },
        "path.separator": {
          "value": "******"
        },
        "management.endpoints.jmx.exposure.include": {
          "value": "******"
        },
        "os.version": {
          "value": "******"
        },
        "java.runtime.name": {
          "value": "******"
        },
        "file.encoding": {
          "value": "******"
        },
        "java.vm.name": {
          "value": "******"
        },
        "java.vendor.url.bug": {
          "value": "******"
        },
        "java.io.tmpdir": {
          "value": "******"
        },
        "catalina.home": {
          "value": "******"
        },
        "java.version": {
          "value": "******"
        },
        "jboss.modules.system.pkgs": {
          "value": "******"
        },
        "user.dir": {
          "value": "******"
        },
        "os.arch": {
          "value": "******"
        },
        "java.vm.specification.name": {
          "value": "******"
        },
        "PID": {
          "value": "******"
        },
        "sun.os.patch.level": {
          "value": "******"
        },
        "CONSOLE_LOG_CHARSET": {
          "value": "******"
        },
        "catalina.base": {
          "value": "******"
        },
        "native.encoding": {
          "value": "******"
        },
        "java.library.path": {
          "value": "******"
        },
        "java.vm.info": {
          "value": "******"
        },
        "java.vendor": {
          "value": "******"
        },
        "java.vm.version": {
          "value": "******"
        },
        "java.rmi.server.randomIDs": {
          "value": "******"
        },
        "sun.io.unicode.encoding": {
          "value": "******"
        },
        "java.class.version": {
          "value": "******"
        }
      }
    },
    {
      "name": "systemEnvironment",
      "properties": {
        "USERDOMAIN_ROAMINGPROFILE": {
          "value": "******",
          "origin": "System Environment Property \"USERDOMAIN_ROAMINGPROFILE\""
        },
        "GIT_HOME": {
          "value": "******",
          "origin": "System Environment Property \"GIT_HOME\""
        },
        "PROCESSOR_LEVEL": {
          "value": "******",
          "origin": "System Environment Property \"PROCESSOR_LEVEL\""
        },
        "SESSIONNAME": {
          "value": "******",
          "origin": "System Environment Property \"SESSIONNAME\""
        },
        "ALLUSERSPROFILE": {
          "value": "******",
          "origin": "System Environment Property \"ALLUSERSPROFILE\""
        },
        "PROCESSOR_ARCHITECTURE": {
          "value": "******",
          "origin": "System Environment Property \"PROCESSOR_ARCHITECTURE\""
        },
        "PSModulePath": {
          "value": "******",
          "origin": "System Environment Property \"PSModulePath\""
        },
        "SystemDrive": {
          "value": "******",
          "origin": "System Environment Property \"SystemDrive\""
        },
        "MAVEN_HOME": {
          "value": "******",
          "origin": "System Environment Property \"MAVEN_HOME\""
        },
        "USERNAME": {
          "value": "******",
          "origin": "System Environment Property \"USERNAME\""
        },
        "USERDNSDOMAIN": {
          "value": "******",
          "origin": "System Environment Property \"USERDNSDOMAIN\""
        },
        "ProgramFiles(x86)": {
          "value": "******",
          "origin": "System Environment Property \"ProgramFiles(x86)\""
        },
        "CMDER_ROOT": {
          "value": "******",
          "origin": "System Environment Property \"CMDER_ROOT\""
        },
        "FPS_BROWSER_USER_PROFILE_STRING": {
          "value": "******",
          "origin": "System Environment Property \"FPS_BROWSER_USER_PROFILE_STRING\""
        },
        "PATHEXT": {
          "value": "******",
          "origin": "System Environment Property \"PATHEXT\""
        },
        "DriverData": {
          "value": "******",
          "origin": "System Environment Property \"DriverData\""
        },
        "HOMESHARE": {
          "value": "******",
          "origin": "System Environment Property \"HOMESHARE\""
        },
        "ProgramData": {
          "value": "******",
          "origin": "System Environment Property \"ProgramData\""
        },
        "ProgramW6432": {
          "value": "******",
          "origin": "System Environment Property \"ProgramW6432\""
        },
        "HOMEPATH": {
          "value": "******",
          "origin": "System Environment Property \"HOMEPATH\""
        },
        "SPRING_HOME": {
          "value": "******",
          "origin": "System Environment Property \"SPRING_HOME\""
        },
        "PROCESSOR_IDENTIFIER": {
          "value": "******",
          "origin": "System Environment Property \"PROCESSOR_IDENTIFIER\""
        },
        "M2_HOME": {
          "value": "******",
          "origin": "System Environment Property \"M2_HOME\""
        },
        "ProgramFiles": {
          "value": "******",
          "origin": "System Environment Property \"ProgramFiles\""
        },
        "PUBLIC": {
          "value": "******",
          "origin": "System Environment Property \"PUBLIC\""
        },
        "windir": {
          "value": "******",
          "origin": "System Environment Property \"windir\""
        },
        "=::": {
          "value": "******",
          "origin": "System Environment Property \"=::\""
        },
        "ZES_ENABLE_SYSMAN": {
          "value": "******",
          "origin": "System Environment Property \"ZES_ENABLE_SYSMAN\""
        },
        "OPENSSL_CONF": {
          "value": "******",
          "origin": "System Environment Property \"OPENSSL_CONF\""
        },
        "LOCALAPPDATA": {
          "value": "******",
          "origin": "System Environment Property \"LOCALAPPDATA\""
        },
        "IntelliJ IDEA": {
          "value": "******",
          "origin": "System Environment Property \"IntelliJ IDEA\""
        },
        "USERDOMAIN": {
          "value": "******",
          "origin": "System Environment Property \"USERDOMAIN\""
        },
        "FPS_BROWSER_APP_PROFILE_STRING": {
          "value": "******",
          "origin": "System Environment Property \"FPS_BROWSER_APP_PROFILE_STRING\""
        },
        "LOGONSERVER": {
          "value": "******",
          "origin": "System Environment Property \"LOGONSERVER\""
        },
        "JAVA_HOME": {
          "value": "******",
          "origin": "System Environment Property \"JAVA_HOME\""
        },
        "OneDrive": {
          "value": "******",
          "origin": "System Environment Property \"OneDrive\""
        },
        "APPDATA": {
          "value": "******",
          "origin": "System Environment Property \"APPDATA\""
        },
        "CommonProgramFiles": {
          "value": "******",
          "origin": "System Environment Property \"CommonProgramFiles\""
        },
        "Path": {
          "value": "******",
          "origin": "System Environment Property \"Path\""
        },
        "OS": {
          "value": "******",
          "origin": "System Environment Property \"OS\""
        },
        "COMPUTERNAME": {
          "value": "******",
          "origin": "System Environment Property \"COMPUTERNAME\""
        },
        "CURL_HOME": {
          "value": "******",
          "origin": "System Environment Property \"CURL_HOME\""
        },
        "PROCESSOR_REVISION": {
          "value": "******",
          "origin": "System Environment Property \"PROCESSOR_REVISION\""
        },
        "CommonProgramW6432": {
          "value": "******",
          "origin": "System Environment Property \"CommonProgramW6432\""
        },
        "MAVEN_DAEMON_HOME": {
          "value": "******",
          "origin": "System Environment Property \"MAVEN_DAEMON_HOME\""
        },
        "ComSpec": {
          "value": "******",
          "origin": "System Environment Property \"ComSpec\""
        },
        "SystemRoot": {
          "value": "******",
          "origin": "System Environment Property \"SystemRoot\""
        },
        "TEMP": {
          "value": "******",
          "origin": "System Environment Property \"TEMP\""
        },
        "HOMEDRIVE": {
          "value": "******",
          "origin": "System Environment Property \"HOMEDRIVE\""
        },
        "USERPROFILE": {
          "value": "******",
          "origin": "System Environment Property \"USERPROFILE\""
        },
        "TMP": {
          "value": "******",
          "origin": "System Environment Property \"TMP\""
        },
        "CommonProgramFiles(x86)": {
          "value": "******",
          "origin": "System Environment Property \"CommonProgramFiles(x86)\""
        },
        "NUMBER_OF_PROCESSORS": {
          "value": "******",
          "origin": "System Environment Property \"NUMBER_OF_PROCESSORS\""
        },
        "IDEA_INITIAL_DIRECTORY": {
          "value": "******",
          "origin": "System Environment Property \"IDEA_INITIAL_DIRECTORY\""
        },
        "HOME": {
          "value": "******",
          "origin": "System Environment Property \"HOME\""
        },
        "POSTGRESQL_HOME": {
          "value": "******",
          "origin": "System Environment Property \"POSTGRESQL_HOME\""
        }
      }
    },
    {
      "name": "Config resource 'class path resource [application.yml]' via location 'optional:classpath:/'",
      "properties": {
        "management.endpoints.web.exposure.include": {
          "value": "******",
          "origin": "class path resource [application.yml] - 5:18"
        }
      }
    }
  ]
}
```