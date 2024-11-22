package me.may.myfolio.fileupload.service.impl;

import com.google.cloud.spring.storage.GoogleStorageResource;
import com.google.cloud.storage.Storage;
import me.may.myfolio.fileupload.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    private final String gcsBucketName;
    private final Storage storage;

    public FileServiceImpl(Storage storage, @Value("${gcs.resource.bucket}") String gcsBucketName) {
        this.gcsBucketName = gcsBucketName;
        this.storage = storage;
    }

    public Optional<URI> upload(String data, String filename) {
        logger.info("Uploading file: {}", filename);
        try {
            Resource resource = fetchResource(filename);
            updateResource(resource, data);
            logger.info("File uploaded at {}", resource.getURI());
            return Optional.of(resource.getURI());
        } catch (IOException e) {
            logger.error("Failed to upload file: {}", filename, e);
            return Optional.empty();
        }
    }

    private void updateResource(Resource resource, String data) throws IOException {
        try (OutputStream os = ((WritableResource) resource).getOutputStream()) {
            os.write(data.getBytes());
            os.flush();
        }
    }

    private GoogleStorageResource fetchResource(String filename) {
        return new GoogleStorageResource(
                this.storage, String.format("gs://%s/%s", this.gcsBucketName, filename));
    }
}
