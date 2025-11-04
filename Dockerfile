# Use official Java 8 image
FROM openjdk:8-jdk-alpine

# Set working directory
WORKDIR /app

# Copy pom.xml and Maven wrapper files first
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Give permission to mvnw
RUN chmod +x mvnw

# Download dependencies (this speeds up future builds)
RUN mvnw dependency:go-offline

# Copy the rest of the project files
COPY src src

# Build the project
RUN mvnw clean package -DskipTests

# Run the Spring Boot jar
CMD ["java", "-jar", "target/demo-project-0.0.1-SNAPSHOT.jar"]
