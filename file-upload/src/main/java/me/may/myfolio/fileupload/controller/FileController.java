package me.may.myfolio.fileupload.controller;

import me.may.myfolio.fileupload.service.impl.FileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("api/file")
public class FileController {

    private final FileServiceImpl service;
    public FileController(FileServiceImpl service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<String> uploadTextFile(@RequestPart String contents, @RequestPart String filename) {
        Optional<URI> uri = service.upload(contents, filename);
        return uri.isPresent() ? ResponseEntity.created(uri.get()).build()
                : ResponseEntity.badRequest().build();
    }
}
