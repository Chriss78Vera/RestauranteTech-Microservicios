FROM maven:3.9-eclipse-temurin-17
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B -q
COPY src ./src
RUN mvn package -DskipTests -B -q
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "target/svc-inventory-0.0.1-SNAPSHOT.jar"]
