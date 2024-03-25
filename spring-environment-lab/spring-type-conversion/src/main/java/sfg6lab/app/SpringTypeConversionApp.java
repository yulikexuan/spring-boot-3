package sfg6lab.app;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.validation.DataBinder;
import org.springframework.validation.ObjectError;
import sfg6lab.config.SpringEnvCfg;
import sfg6lab.domain.model.Profile;
import sfg6lab.domain.service.StringToPathConverter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;


@Slf4j
public class SpringTypeConversionApp {

    static final Function<ApplicationConversionService, String> DATE_PRINTER =
            converter -> converter.convert(LocalDate.now(), String.class);

    public static void main(String[] args) {

        final var ctx = new SpringApplicationBuilder(SpringEnvCfg.class)
                .headless(true)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                // PID file will be deleted automatically after the app ends
                // normally
                .listeners(new ApplicationPidFileWriter())
                .build(args)
                .run(args);

        var converter = ctx.getBean(
                "conversionService", ApplicationConversionService.class);
        converter.addConverter(ctx.getBean(StringToPathConverter.class));

//        converter.toString().lines().skip(1)
//                .forEach(line -> log.debug(">>> Converter {}", line));

        var properties = converter.convert("username=yul", Properties.class);

        String username = (String) properties.get("username");
        username = username == null ? "UNKNOWN" : username;
        log.debug(">>> The property value of username is {}", username);

        var date = converter.convert(LocalDateTime.now(), LocalDate.class);
        log.debug(">>> Today is {}", date);

        var numbers = converter.convert("1, 2, 3, 4, 5, 6, 7", int[].class);
        log.debug(">>> Numbers are {}", Arrays.toString(numbers));

        List<Integer> input = Lists.newArrayList(7, 6, 5, 4, 3, 2, 1);

        @SuppressWarnings("unchecked")
        List<String> strOfNums = (List<String>) converter.convert(
                input,
                TypeDescriptor.forObject(input),
                TypeDescriptor.collection(List.class,
                        TypeDescriptor.valueOf(String.class)));

        log.debug(">>> String of numbers: {}", strOfNums);

        Currency uC = converter.convert("EUR", Currency.class);
        log.debug(">>> The currency of Europe is {}", uC);

        Locale tw = converter.convert("Taiwan", Locale.class);
        log.debug(">>> The locale of Taiwan is {}", tw);

        // Using stringToPathConverter internally

        String location = "C:/dev/projects/spring-boot-3";
        Path path = converter.convert(location, Path.class);
        boolean exists = path != null && Files.exists(path);
        log.debug(">>>> Path to '{}' {} exist ", path, exists ? "does" : "does not");

        // Formatters

        // Sets the current language as a thread-local object
        LocaleContextHolder.setLocale(Locale.TAIWAN); // 16.06.24
        log.debug(">>> Taiwan Date is {}", DATE_PRINTER.apply(converter));

        LocaleContextHolder.setLocale(Locale.GERMANY); // 16/06/2024
        log.debug(">>> Germany Date is {}", DATE_PRINTER.apply(converter));

        LocaleContextHolder.setLocale(Locale.FRANCE); // 16/06/2024
        log.debug(">>> France Date is {}", DATE_PRINTER.apply(converter));

        LocaleContextHolder.setLocale(Locale.US); // 16/06/2024
        log.debug(">>> America Date is {}", DATE_PRINTER.apply(converter));

        // Data Binders
        var propertyValues = new MutablePropertyValues();

        propertyValues.add("id", ctx.getBean(
                "profileId", UUID.class));
        propertyValues.add("username", ctx.getBean(
                "profileUsername", String.class));
        propertyValues.add("secretLevel", ctx.getBean(
                "profileSecretLevel", Integer.class));

        var profile = new Profile();

        DataBinder profileBinder = new DataBinder(profile);

        // Converter instance can be registered and perform arbitrary conversions
        profileBinder.setConversionService(converter);
        profileBinder.bind(propertyValues);

        log.debug(">>> {} has id {}", profile.getUsername(), profile.getId());

        log.debug(">>> {} has secret level {}",
                profile.getUsername(), profile.getSecretLevel());

        // Log Data Binders Errors
        var data = Map.of("id", UUID.randomUUID().toString(),
                "username", "yul",
                "secretLevel", "THREE");
        var profileValues = new MutablePropertyValues(data);

        var profile2 = new Profile();
        DataBinder profileBinder2 = new DataBinder(profile2);

        profileBinder2.setConversionService(converter);
        profileBinder2.bind(profileValues);

        log.debug(">>> {} has id {}", profile2.getUsername(), profile2.getId());
        log.debug(">>> {} has secret level {}",
                profile2.getUsername(), profile2.getSecretLevel());

        List<ObjectError> errors = profileBinder2.getBindingResult().getAllErrors();
        errors.forEach(err -> log.error(">>> Binding Error: {}", err));

        System.out.println(">>> Press Enter Key to Continue ... ");
        new Scanner(System.in).nextLine();
        System.exit(SpringApplication.exit(ctx));
    }

}
