FROM openjdk:17

COPY target/oauth-service.jar oauth-service.jar

ENTRYPOINT ["java","-jar","oauth-service.jar"]

EXPOSE 8080
