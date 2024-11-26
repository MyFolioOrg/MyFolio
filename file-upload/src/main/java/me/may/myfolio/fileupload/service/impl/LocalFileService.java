package me.may.myfolio.fileupload.service.impl;

import me.may.myfolio.fileupload.service.FileService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@Profile("local")
public class LocalFileService implements FileService {
    private static final String BASE_PATH = "jmeter/locally_uploaded/";
    @Override
    public Optional<URI> upload(String data, String filename) {

        try {
            Files.createDirectories(Paths.get(BASE_PATH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fullPath = BASE_PATH + filename;
        try (FileOutputStream fos = new FileOutputStream(fullPath)) {
            System.out.println("Writing file locally...");
            fos.write(data.getBytes());

            System.out.println("File saved at " + fullPath);
            return Optional.empty();
        } catch (IOException e) {
            System.out.println("Failed saving file" + e);
            return Optional.empty();
        }
    }
}
