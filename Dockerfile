FROM openjdk:21
EXPOSE 8085
ADD target/train-0.0.1-SNAPSHOT.jar train.jar
ENTRYPOINT ["java", "-jar", "train.jar"]
