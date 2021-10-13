FROM openjdk:16-alpine3.13
WORKDIR /app
COPY out/artifacts/RestService_jar/bookish.jar bookish.jar
CMD ["java", "-jar", "bookish.jar"]