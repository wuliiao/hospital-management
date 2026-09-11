FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY main-service ./main-service
COPY report-service ./report-service
COPY gateway-service ./gateway-service
ARG MODULE
RUN mvn -q -pl ${MODULE} -am -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
ARG MODULE
COPY --from=build /app/${MODULE}/target/${MODULE}-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
