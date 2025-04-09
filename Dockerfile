FROM openjdk:21
EXPOSE 8089
ADD target/APIGATEWAY-0.0.1-SNAPSHOT.jar api.jar
ENTRYPOINT ["java","-jar","api.jar"]
