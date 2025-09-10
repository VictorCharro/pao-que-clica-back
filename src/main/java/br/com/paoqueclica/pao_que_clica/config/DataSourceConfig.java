package br.com.paoqueclica.pao_que_clica.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Value;
import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DataSourceConfig {

    @Value("${SPRING_DATASOURCE_URL:#{null}}")
    private String databaseUrl;

    @Bean
    @Primary
    public DataSource dataSource() {
        try {
            // Verifica se a variável de ambiente está definida
            if (databaseUrl == null || databaseUrl.isEmpty()) {
                throw new RuntimeException("SPRING_DATASOURCE_URL não está definida");
            }

            System.out.println("Database URL recebida: " + databaseUrl);

            // Parse da URL
            URI dbUri = new URI(databaseUrl);

            // Validações
            if (dbUri.getUserInfo() == null) {
                throw new RuntimeException("UserInfo não encontrado na URL");
            }

            String[] userInfo = dbUri.getUserInfo().split(":");
            if (userInfo.length != 2) {
                throw new RuntimeException("Formato de UserInfo inválido");
            }

            String username = userInfo[0];
            String password = userInfo[1];

            // Constrói a URL JDBC
            String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();

            System.out.println("JDBC URL construída: " + jdbcUrl);
            System.out.println("Username: " + username);

            return DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .username(username)
                    .password(password)
                    .driverClassName("org.postgresql.Driver")
                    .build();

        } catch (URISyntaxException e) {
            System.err.println("Erro ao fazer parse da URL: " + e.getMessage());
            throw new RuntimeException("URL do banco de dados inválida", e);
        } catch (Exception e) {
            System.err.println("Erro na configuração do DataSource: " + e.getMessage());
            throw new RuntimeException("Falha ao configurar DataSource", e);
        }
    }
}