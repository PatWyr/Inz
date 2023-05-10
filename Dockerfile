FROM openjdk:17-alpine
MAINTAINER patryk_wyrwich
COPY target/Inzynierka-0.0.1-SNAPSHOT.jar Inzynierka-1.0.0.jar
ENTRYPOINT ["java","-jar","/Inzynierka-1.0.0.jar"]
