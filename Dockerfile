# Build stage
FROM gradle:8.7-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle build -x test
# Runtime stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar appSecurity.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "appSecurity.jar"]


