package br.com.paoqueclica.pao_que_clica.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // Esta anotação injeta o valor da variável de ambiente SPRING_DATASOURCE_URL
    // que configuramos no Render.
    @Value("${SPRING_DATASOURCE_URL}")
    private String databaseUrl;

    /**
     * Este Bean cria e configura a fonte de dados (DataSource) para a aplicação.
     * Ele corrige a URL do banco de dados fornecida pelo Render antes de usá-la.
     */
    @Bean
    public DataSource dataSource() {
        String correctedUrl = databaseUrl;

        // Verifica se a URL NÃO começa com "jdbc:".
        // A URL do Render começa com "postgresql://", então esta condição será verdadeira.
        if (!correctedUrl.startsWith("jdbc:")) {
            correctedUrl = "jdbc:" + correctedUrl;
        }

        // Usa o DataSourceBuilder do Spring para criar a conexão com a URL corrigida.
        return DataSourceBuilder.create()
                .url(correctedUrl)
                .build();
    }
}
