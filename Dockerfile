# Use the official Amazon Corretto 17 image
FROM amazoncorretto:17-alpine

# Set working directory
WORKDIR /app

# Copy the built jar file
COPY target/live-betting-app-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for Spring Boot
EXPOSE 8080

# Define entrypoint to run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]