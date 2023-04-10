# Utilize a imagem oficial do OpenJDK como base
FROM openjdk:11

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR gerado pelo projeto para o container
COPY target/academic-service-0.0.1-SNAPSHOT.jar /app/app.jar

# Expõe a porta do seu aplicativo (substitua pela porta correta)
EXPOSE 8080

# Comando para executar o aplicativo Java
CMD ["java", "-jar", "/app/app.jar"]
