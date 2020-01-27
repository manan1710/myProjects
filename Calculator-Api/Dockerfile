FROM openjdk:8
ADD target/CalculatorApi-spring-boot.jar calculatorapi.jar
EXPOSE 8080
ENTRYPOINT [“java”,”-Dspring.data.mongodb.uri=mongodb://mongo/testDB”, “-Djava.security.egd=file:/dev/./urandom”,”-jar”,”/app.jar”]