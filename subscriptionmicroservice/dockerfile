# Use an official OpenJDK 17 image as the base
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Add the JAR file to the container (change the JAR name if needed)
ARG JAR_FILE=target/subscriptionmicroservice-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

# Expose the port your app runs on (matches application.properties)
EXPOSE 8081

# Command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
