package ru.itis.kpfu.cloudlabwebapp.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobListDetails;
import com.azure.storage.blob.models.ListBlobsOptions;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@AllArgsConstructor
@Configuration
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class AzureConfig {

    private final Environment environment;

    @Bean
    public BlobServiceClient blobContainerClient() {

        var sysEnvironmentValue = System.getenv("AZURE_CONNECTION_STRING");

        if (sysEnvironmentValue == null) {
            return new BlobServiceClientBuilder()
                    .connectionString(environment.getProperty("azure.service.connection.string"))
                    .buildClient();
        } else {
            return new BlobServiceClientBuilder()
                    .connectionString(sysEnvironmentValue)
                    .buildClient();
        }
    }

    @Bean
    public ListBlobsOptions listBlobsOptions() {
        return new ListBlobsOptions()
                .setDetails(new BlobListDetails().setRetrieveMetadata(true));
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Thread-");
        executor.initialize();
        return executor;
    }
}
