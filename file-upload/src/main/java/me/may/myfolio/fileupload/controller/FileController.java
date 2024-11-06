package me.may.myfolio.fileupload.controller;

import me.may.myfolio.fileupload.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("api/file")
public class FileController {

    private final FileService service;
    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<String> uploadTextFile(@RequestPart String contents, @RequestPart String filename) {
        Optional<URI> uri = service.upload(contents, filename);
        return uri.isPresent() ? ResponseEntity.created(uri.get()).build()
                : ResponseEntity.badRequest().build();
    }
}
