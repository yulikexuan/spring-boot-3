//: sfg6lab.shell.commands.I18nCommand.java


package sfg6lab.shell.commands;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;
import java.util.Locale;


@ShellComponent
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class I18nCommand {

    private final MessageSourceAccessor messageSourceAccessor;

    @ShellMethod(key="today", value="Show the date of today with language code, and country code ")
    String today(String lang, String country) {

        Locale locale = new Locale(lang, country);

        return this.messageSourceAccessor.getMessage(
                "sfg6.shell.command.current-date",
                new Object[] { LocalDate.now().toString() },
                locale) ;
    }

}///:~