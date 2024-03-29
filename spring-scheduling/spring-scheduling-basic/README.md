# The Basic of Spring Scheduling 

[Cron Expressions](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronExpression.html)

### Spring is designed as an integration framework 
- Meaning it provides a unified platform for integrating various technologies 
    - rather than trying to offer a comprehensive solution for every feature in its own library 

### Scheduling requires more than just a simple annotation
- it involves state and transaction management, among other things 
- As a result, many developers turn to third-party libraries such as [Quartz](https://www.quartz-scheduler.org/documentation/) 
  for more robust scheduling functionality 
  - Quartz is an open-source library for scheduling jobs in Java 
    - which is widely used in enterprise applications and is backed by Software AG