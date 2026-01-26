# -------- BUILD STAGE --------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# -------- RUN STAGE --------
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# IMPORTANT: Don't hardcode port
EXPOSE 8080

# Use Render's PORT variable
CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT:-8080}"]
