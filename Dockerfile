# Estágio 1: Construção (Build)
# Usamos uma imagem oficial do Maven com o JDK 17 para compilar nosso projeto.
FROM maven:3.8.5-openjdk-17 AS build

# Define o diretório de trabalho dentro do container.
WORKDIR /app

# Copia os arquivos de configuração do Maven.
COPY pom.xml .

# Baixa as dependências. Isso é feito separadamente para aproveitar o cache do Docker.
RUN mvn dependency:go-offline

# Copia o resto do código-fonte do nosso projeto.
COPY src ./src

# Executa o build do Maven para gerar o arquivo .jar.
RUN mvn package -DskipTests


# Estágio 2: Execução (Run)
# Usamos uma imagem leve, apenas com o Java 17 necessário para rodar.
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho.
WORKDIR /app

# Copia o arquivo .jar que foi gerado no estágio de build.
# Verifique o nome exato do seu .jar na pasta 'target' do seu projeto.
COPY --from=build /app/target/pao_que_clica-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080 para que o Render possa se comunicar com nossa aplicação.
EXPOSE 8080

# Comando que será executado quando o container iniciar.
ENTRYPOINT ["java", "-jar", "app.jar"]
