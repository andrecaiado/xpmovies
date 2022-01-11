FROM maven:3.8.1-jdk-11-slim as BUILD

WORKDIR /build/

COPY pom.xml /build/

RUN mvn dependency:resolve

COPY src /build/src

RUN mvn -Dmaven.test.skip clean package

FROM openjdk:11-jre

COPY --from=BUILD /build/target/*.jar /app.jar

EXPOSE 8080 8080

ENTRYPOINT ["java","-jar","app.jar"]