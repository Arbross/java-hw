package org.example.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface IFileWorkingService {
    ResponseEntity<Resource> getFile(String filename) throws Exception;
    String uploadFile(String base64);
}
