FROM openjdk:21
EXPOSE 8880
ADD target/projectconf-0.0.1-SNAPSHOT.jar projectconf.jar
ENTRYPOINT ["java","-jar","projectconf.jar"]
