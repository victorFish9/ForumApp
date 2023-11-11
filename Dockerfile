#FROM maven:3.8.5-openjdk-17 AS build
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
#COPY . .
#RUN mvn clean package -DskipTests

#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/Forum.jar Forum.jar
COPY target/*.jar forum.jar
ENTRYPOINT ["java", "-jar", "/forum.jar"]
EXPOSE 8080
