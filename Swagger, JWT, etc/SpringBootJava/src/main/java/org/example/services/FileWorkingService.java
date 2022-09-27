package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.interfaces.IFileWorkingService;
import org.example.storage.IStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class FileWorkingService implements IFileWorkingService {
    private final IStorageService storageService;

    public ResponseEntity<Resource> getFile(String filename) throws Exception {
        Resource file = storageService.loadAsResource(filename);
        String urlFileName =  URLEncoder.encode("default.jpg", StandardCharsets.UTF_8.toString());
        return ResponseEntity.ok()
                // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
                .contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION,"filename=\""+urlFileName+"\"")
                .body(file);
    }

    public String uploadFile(String base64)
    {
        return storageService.store(base64);
    }
}
