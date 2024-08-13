1. 项目使用工具  jdk17 maven3.6.3 
2. 项目入口为 **org.bookstrore.BookstoreApplication**
3. 使用手册
```shell
mvn clean compile  package -DskipTests=true
```
4. 项目注意点
> springboot3 jdk17 与swagger3 的兼容问题 使用一下版本进行解决
```xml
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.0.2</version>
        </dependency>
```
5. swagger访问地址
```shell
http://localhost:8080/swagger-ui/index.html
```


