package me.may.myfolio.fileupload.service;

import java.net.URI;
import java.util.Optional;

public interface FileService {
    Optional<URI> upload(String data, String filename);
}
