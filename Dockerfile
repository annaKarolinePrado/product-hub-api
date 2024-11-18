# Usar uma imagem do JDK 17
FROM openjdk:17-jdk-slim

# Instalar Maven
RUN apt-get update && apt-get install -y maven

# Criar um diretório para a aplicação
WORKDIR /app

# Copiar o arquivo pom.xml e o Maven Wrapper e baixar as dependências
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn
RUN chmod +x mvnw && ./mvnw dependency:resolve

# Copiar o código-fonte para o contêiner
COPY src ./src

# Compilar e empacotar a aplicação
RUN ./mvnw package -DskipTests

# Executar a aplicação
CMD ["java", "-jar", "target/product-hub-api-0.0.1-SNAPSHOT.jar"]
