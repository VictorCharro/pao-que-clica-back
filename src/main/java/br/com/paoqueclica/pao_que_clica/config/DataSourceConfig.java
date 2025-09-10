package br.com.paoqueclica.pao_que_clica.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Value;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${SPRING_DATASOURCE_URL:${DATABASE_URL:#{null}}}")
    private String databaseUrl;

    @Bean
    @Primary
    public DataSource dataSource() {
        try {
            if (databaseUrl == null || databaseUrl.isEmpty()) {
                throw new RuntimeException("Database URL não está definida");
            }

            System.out.println("Database URL recebida: " + databaseUrl);

            // Para Render, construir manualmente baseado na URL fornecida
            // postgresql://pao_db_user:IjK8KP2zCXSAdkMDruV4uHAHkXeUVaBc@dpg-d30vo8h5pdvs73edpi00-a/pao_db

            // Extrair informações manualmente
            String url = databaseUrl;
            url = url.replace("postgresql://", "").replace("postgres://", "");

            String[] parts = url.split("@");
            String userInfo = parts[0]; // username:password
            String hostAndDb = parts[1]; // host/database

            String[] userParts = userInfo.split(":");
            String username = userParts[0];
            String password = userParts[1];

            String[] hostParts = hostAndDb.split("/");
            String host = hostParts[0];
            String database = hostParts[1];

            // Construir URL JDBC para Render
            String jdbcUrl = "jdbc:postgresql://" + host + ".oregon-postgres.render.com:5432/" + database;

            System.out.println("JDBC URL: " + jdbcUrl);
            System.out.println("Username: " + username);
            System.out.println("Database: " + database);

            return DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .username(username)
                    .password(password)
                    .driverClassName("org.postgresql.Driver")
                    .build();

        } catch (Exception e) {
            System.err.println("Erro na configuração do DataSource: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Falha ao configurar DataSource", e);
        }
    }
}