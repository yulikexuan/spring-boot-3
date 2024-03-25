# The Lab of Spring Environment

## Set Up Properties with Command Line
- ` $ java -jar -Dspring.application.json='{"sfg6":{"author": {"username":"yu.li","email":"yu.li@tecsys.com","address":"1000 St-Louis","phones":{"work":{"areaCode":"514","number":"123-4567"},"home":{"areaCode":"436","number":"765-4321
  "}}}}}' target/spring-external-config-3.2.3.jar `

``` 
spring-env-lab:> author-json
author-json
{"username":"yu.li","email":"yu.li@tecsys.com","address":"1000 St-Louis","phones":{"work":{"areaCode":"514","number":"123-4567"},"home":{"areaCode":"436","number":"765-4321"}}}
```