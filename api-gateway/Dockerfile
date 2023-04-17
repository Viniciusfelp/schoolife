# Imagem base para a API Gateway
FROM maven:4.0.0-jdk-11-slim AS build

# Define a pasta de trabalho
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Faz o build da aplicação
RUN mvn clean package -DskipTests

# Define a imagem final a ser utilizada
FROM openjdk:11-jre-slim

# Copia o JAR da aplicação
COPY --from=build /app/target/*.jar /app.jar

# Expose a porta da API Gateway
EXPOSE 8080

# Define o comando para iniciar a API Gateway
CMD ["java", "-jar", "/app.jar"]
