//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.*;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;
import sfg6lab.service.SpringUtilDemo;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Slf4j
@EnableScheduling
@EnableAspectJAutoProxy
@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({ Controller.class, Service.class }),
        basePackages = { "sfg6lab.service", "sfg6lab.domain.service"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class Sfg6AppCfg {

    @Bean
    SpringUtilDemo utilDemo() {
        return new SpringUtilDemo();
    }

    @Bean
    @NonNull
    CommandLineRunner demo(@NonNull final SpringUtilDemo utilDemo) {

        return args -> {

            Assert.notNull(args, ">>> args should not be null.");
            Assert.notNull(utilDemo.data(),
                    ">>> The data of the demo should not be null.");

            beanUtils(utilDemo);
            classUtils(utilDemo);
            systemPropertyUtils();
            fileCopyUtils();
            aop(utilDemo);
            reflection();
        };
    }

    private void reflection() {

        System.out.println(".................................................");

        ReflectionUtils.doWithFields(
                SpringUtilDemo.class,
                field -> log.info(">>> field = {}", field.toString()));
        ReflectionUtils.doWithMethods(
                SpringUtilDemo.class,
                method -> log.info(">>> method : {}", method.toString()));

        Field field = ReflectionUtils.findField(SpringUtilDemo.class, "data");
        Assert.notNull(field, "Field must not be null");
        ResolvableType resolvableType = ResolvableType.forField(field);
        log.info(">>> resolvableType of field data is : {}", resolvableType);
    }

    private void aop(@NonNull final SpringUtilDemo utilDemo) {
        Class<?> targetClazz = AopUtils.getTargetClass(utilDemo);
        log.info(">>> The target class is {}", targetClazz);
        boolean beingAopProxy = AopUtils.isAopProxy(utilDemo);
        log.info(">>> Being AOP Proxy? {}", beingAopProxy);
        boolean beingCglibProxy = AopUtils.isCglibProxy(utilDemo);
        log.info(">>> Being CGlib Proxy? {}", beingCglibProxy);
    }

    @Aspect
    @Component
    static class SimpleBeforeAspect {

        @Before("execution(* sfg6lab.service.*.*(..))")
        public Object log(JoinPoint joinPoint) throws Throwable {
            log.info("------- Aspects Intercepting ------- ");
            log.info(">>> Method Intercepted {}", joinPoint.getSignature());
            return joinPoint.getClass();
        }

    }

    @RestController
    static class SpringUtilController {

        @GetMapping("/util")
        String greeting(HttpServletRequest request) {

            int ageValue = ServletRequestUtils.getIntParameter(
                    request, "age", -1);

            var tempPath = WebUtils.getTempDir(request.getServletContext()).toPath();

            WebApplicationContext ctx =
                    RequestContextUtils.findWebApplicationContext(request);
            Environment envBean = ctx.getBean(Environment.class);
            var userHome = envBean.getProperty("user.home");

            return ">>> Request Param 'age' " +
                    ageValue +
                    "\n" +
                    ">>> Servlet Temp is " +
                    tempPath +
                    "\n" +
                    ">>> WebContext resolved user home is " +
                    userHome;
        }

    }

    private void fileCopyUtils() throws IOException {

        String userHome = SystemPropertyUtils.resolvePlaceholders("${user.home}");
        Path filePath = Paths.get(userHome, "/.m2/settings.xml");

        String tempPath = SystemPropertyUtils.resolvePlaceholders("${TEMP}");
        Path targetFilePath = Paths.get(tempPath, "/settings.xml");

        FileCopyUtils.copy(filePath.toFile(), targetFilePath.toFile());

        log.info(">>> Maven Settings {} exists? {}",
                filePath, Files.exists(filePath));
        log.info(">>> Copied to {}, exists? {}",
                targetFilePath, Files.exists(targetFilePath));

        FileSystemUtils.deleteRecursively(targetFilePath);

        log.info(">>> Still exists? {}", Files.exists(targetFilePath));
    }

    private void systemPropertyUtils() {
        var homeDirInfo = SystemPropertyUtils.resolvePlaceholders(
                "The current home directory is ${user.home}");
        log.info(">>> The home dir: {}", homeDirInfo);
        //java.vm.vendor
        var jvmVendorInfo = SystemPropertyUtils.resolvePlaceholders(
                "${java.vm.vendor}");
        log.info(">>> The JVM Vendor: {}", jvmVendorInfo);
    }

    private void classUtils(@NonNull final SpringUtilDemo utilDemo) {

        var constructor = ClassUtils.getConstructorIfAvailable(utilDemo.getClass());
        log.info(">>> The constructor: {}", constructor);

        try {
            var demo = constructor.newInstance();
            log.info(">>> New instance of demo: {}", demo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void beanUtils(@NonNull final SpringUtilDemo utilDemo) {
        var propertyDescriptors =
                BeanUtils.getPropertyDescriptors(utilDemo.getClass());
        for (PropertyDescriptor pd : propertyDescriptors) {
            log.info(">>> PD {} : {}",
                    pd.getPropertyType().getTypeName(),
                    pd.getPropertyType().getName());
            log.info(">>> PD::readMethod: {}", pd.getReadMethod().getName());
        }
    }

}///:~
