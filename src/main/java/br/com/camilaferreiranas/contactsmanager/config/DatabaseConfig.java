package br.com.camilaferreiranas.contactsmanager.config;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private Gson gson = new Gson();


    @Bean
    public DataSource dataSource() {
        SecretValue secretValue = getSecretValue();

        // Check if the information was retrieved
        if (secretValue == null || secretValue.getHost() == null || secretValue.getPort() == null
                || secretValue.getDbname() == null || secretValue.getUsername() == null || secretValue.getPassword() == null) {
            throw new IllegalStateException("Informações essenciais não foram recuperadas do AWS Secrets Manager.");
        }



        // Build JDBC url
        String jdbcUrl = String.format("jdbc:postgresql://%s:%s/%s", secretValue.getHost(),
                secretValue.getPort(), secretValue.getDbname());





        // Create datasource
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .password(secretValue.getPassword())
                .username(secretValue.getUsername())
                .url(jdbcUrl)
                .build();
    }

    private SecretValue getSecretValue() {

        String secretName = "***"; //secretName
        String region = "***"; //region

        // Create a Secrets Manager client
        AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();

        String secret;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw e;
        }

        if (getSecretValueResult.getSecretString() != null) {
            secret = getSecretValueResult.getSecretString();
            return gson.fromJson(secret, SecretValue.class);
        }
        return null;
    }
}
