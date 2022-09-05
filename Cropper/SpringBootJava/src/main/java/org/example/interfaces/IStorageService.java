package org.example.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {
    void init();
    Stream<Path> loadAll();
    Path load(String filename);
    String store(String base64);
    Resource loadAsResource(String filename);
    void deleteAll();
}