
FROM gradle:jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build --x test

#
# Package stage
FROM openjdk:17-slim
ENV NAME_APP="BiblioSpring.web-0.0.1-SNAPSHOT.jar"

WORKDIR /home/biblioapp
COPY --from=build /app/BiblioSpring.web/build/libs ./



EXPOSE 8080

ENTRYPOINT SPRING_PROFILES_ACTIVE=prod java -jar $NAME_APP