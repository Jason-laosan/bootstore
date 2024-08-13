1. Project Tools Used: JDK 17, Maven 3.6.3
2. Project Entry Point: org.bookstore.BookstoreApplication
3. Usage Manual:
   ```shell 
   mvn clean compile package -DskipTests=true
   ```

4. Project Considerations:

> Compatibility issue between Spring Boot 3 and JDK 17 with Swagger 3. The following version can be used to resolve

 ```xml

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.0.2</version>
</dependency>
```

5. swagger access URL
```shell
http://localhost:8080/swagger-ui/index.html
```
 