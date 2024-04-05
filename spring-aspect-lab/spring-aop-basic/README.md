# Steps to Create an Aspect

## Step 1 Enable the Aspect Mechanism
- ### Annotating the Configuration Class with 
  - the  ``` @EnableAspectJAutoProxy ``` Annotation
    ``` 
    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = {"app.service", "app.aspect"})
    public class AppConfig {
    
    }
    ```

## Step 2 Create a Aspect Class 
- ### Annotating the Aspect Class with ``` @Aspect ``` Annotation
- ### Create a Bean in Configuration Class for the Aspect Class
    ```
    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = {"app.service", "app.aspect"})
    public class AppConfig {
        @Bean
        LoggingAspect loggingAspect() {
            return LoggingAspect.of();
        }
    }
    ```

## Step 3 Add Aspect Method to Hold the Aspect Logic
- ### Define a Method in the Aspect Class for the Aspect Logic 
- ### Tell Spring When and Which Methods to Intercept 
    ``` 
    @Around("execution(* app.service.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        return null;
    }
    ```
  - ### Explain ``` execution ( * services.*.*(..) ) ```
    - #### ``` execution ``` means "When the method is called ..."
    - #### The first ``` * ``` means the intercepted method may have any returned type 
    - #### ``` app.service ``` is the package name where the intercepted methods live
    - #### The second ``` * ``` means the intercepted methods can be in any class
    - #### The third ``` * ``` means the intercepted methods can have any name 
    - #### ``` (..) ``` means the intercepted methods can have parameters
  - ### Make Sure the Aspect Method Has a ``` ProceedingJoinPoint ``` Parameter

## Step 4 Implement the Aspect Logic 
  - ``` 
    @Around("execution(* app.service.*.*(..))")
    public Optional<Object> log(ProceedingJoinPoint joinPoint) throws Throwable {

        final var signature = joinPoint.getSignature();

        log.info(">>> Will execute a service call {}", signature);

        Object result = null;
        if (shouldProceed(joinPoint)) {
            result = joinPoint.proceed();
        }

        log.info(">>> After executing a service call {}", signature);

        return Optional.ofNullable(result);
    }

    boolean shouldProceed(ProceedingJoinPoint joinPoint) {
        return true;
    }
    ```