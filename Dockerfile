FROM openjdk:8-jdk-alpine
ADD target/Annuaire-0.0.1-SNAPSHOT.jar Annuaire-0.0.1-SNAPSHOT.jar
ARG DEPENDENCY=target/dependency
ENTRYPOINT ["java", "-jar", "Annuaire-0.0.1-SNAPSHOT.jar"]