# Disclaimer - this file is (mostly) copied from https://spring.io/guides/gs/spring-boot-docker/
FROM openjdk:11-jre-slim
RUN  adduser danny
USER danny
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]