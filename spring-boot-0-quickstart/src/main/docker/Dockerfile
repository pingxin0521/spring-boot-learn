FROM openjdk:8-jdk-alpine
MAINTAINER hyp
LABEL app="myApp" version="0.0.1" by="hyp"
COPY spring-boot-0-quickstart.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]