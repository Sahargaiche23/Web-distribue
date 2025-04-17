FROM openjdk:21
EXPOSE 8089
ADD target/APIGATEWAY-0.0.1-SNAPSHOT.jar appifinal.jar
ENTRYPOINT ["java","-jar","appifinal.jar"]
