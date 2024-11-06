package me.may.myfolio.fileupload.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GoogleCloudConfig {

    @Bean
    public Storage googleStorage() throws IOException {
        // Load the credentials file from the classpath
        InputStream credentialsStream = getClass().getResourceAsStream("/kringloop2-55f8112a55c0.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

        // Initialize the Storage client with the credentials
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
