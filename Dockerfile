# Estágio 1: Construção (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests


# Estágio 2: Execução (Run)
FROM openjdk:17-jdk-slim
WORKDIR /app

# --- CORREÇÃO PRINCIPAL AQUI ---
# Em vez de um nome de arquivo específico, usamos um curinga (*.jar).
# Isso copia QUALQUER arquivo .jar da pasta target, tornando o build mais robusto.
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

