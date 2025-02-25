
FROM gradle:jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build --x test

#
# Package stage
FROM openjdk:17-slim
ENV NAME_APP="BiblioSpring.web.jar"

WORKDIR /home/biblioapp
COPY --from=build /app/BiblioSpring.web/build/tmp/dist ./



EXPOSE 8080

ENTRYPOINT SPRING_PROFILES_ACTIVE=prod java -jar $NAME_APP