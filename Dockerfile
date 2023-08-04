From openjdk:17-jdk-alpine
Run apk add --no-cache maven
WORKDIR /app
copy . /app
run mvn clean install -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "./target/game-api-0.0.1-SNAPSHOT.jar"]