# Use OpenJDK image
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN apt-get update && apt-get install -y maven && mvn dependency:go-offline

# Copy all project files
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Run the built JAR file
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
