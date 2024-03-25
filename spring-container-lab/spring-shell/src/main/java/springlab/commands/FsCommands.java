//: springlab.commands.FsCommand.java


package springlab.commands;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import springlab.domain.model.CoffeeBrewingType;
import springlab.domain.model.CoffeeOrder;
import springlab.domain.model.Location;
import springlab.domain.service.TasksService;
import springlab.service.CoffeeOrdersService;
import springlab.service.CoffeeService;
import springlab.service.ExpressionParsingService;
import springlab.service.FileSystemService;

import java.util.List;
import java.util.UUID;
import java.util.function.IntSupplier;
import java.util.function.Supplier;


@Slf4j
@ShellComponent
@DependsOn("locationRegistration")
@AllArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class FsCommands {

    private final FileSystemService fileSystemService;
    private final CoffeeService coffeeService;
    private final TasksService tasksService;
    private final CoffeeOrdersService coffeeOrdersService;
    private final ExpressionParsingService expressionParsingService;
    private final Location location;

    private final IntSupplier randomIntSupplier;
    private final Supplier<UUID> uuidSupplier;

    @ShellMethod("Show Greeting!")
    public String hi() {
        return ">>> Hello, Welcome to Spring Lab!";
    }

    @ShellMethod(key = "fds", value = "Show required free disk space")
    public String minimumFreeDiskSpace() {
        return ">>> The minimum free disk space is %d GB".formatted(
                this.fileSystemService.getFreeDiskSpaceInGb());
    }

    @ShellMethod(key = "slc", value = "Show input string as lowercase")
    public String toLowercase(String input) {
        return input.toLowerCase();
    }


    @ShellMethod(key = "ln", value = "Show a long integer number")
    public String longNumber() {
        return ">>> Here you go, a long number %d".formatted(
                this.fileSystemService.randomLong());
    }

    @ShellMethod(key = "coffee",
            value = "Brew coffee and serve ... \n\t1. Pour Over \n\t2. French Press")
    public String coffeeMenu(String brewCode) {

        CoffeeBrewingType type = brewCode.equals("1") ?
                CoffeeBrewingType.POUR_OVER :
                CoffeeBrewingType.FRENCH_PRESS;

        return ">>> Today we serve %s".formatted(
                this.coffeeService.serve(type));
    }

    @ShellMethod(key = "tasks", value = "Processing tasks in order")
    public void requestTaskProcessing() {
        this.tasksService.processTasks();
    }

    @ShellMethod(key = "coffee-orders", value = "Show the current coffee orders")
    public List<CoffeeOrder> coffeeOrders() {
        return this.coffeeOrdersService.coffeeOrders();
    }

    @ShellMethod(key = "in", value = "Show a integer number")
    public int intRandomNumber() {
        return randomIntSupplier.getAsInt();
    }

    @ShellMethod(key = "uuid", value = "Show a new UUID string")
    public String newUuid() {
        return this.uuidSupplier.get().toString();
    }

    @ShellMethod(key = "spel", value = "Evaluates SpEL expression")
    public String parseExpression(String expression) {
        return this.expressionParsingService.evaluate(expression);
    }

    @PostConstruct
    void checkDiskSpaceBeforeRunning() {
        log.info(">>> Your location is {}", this.location);
        log.info(">>> The free disk space is {} GB",
                this.fileSystemService.getFreeDiskSpaceInGb());
    }

    @PreDestroy
    void showHowMuchDiskSpaceLeft() {
        log.info(">>> You have {} GB free space left.",
                this.fileSystemService.getFreeDiskSpaceInGb());
    }

}///:~