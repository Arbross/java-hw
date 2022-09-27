package org.example.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.example.interfaces.IFileWorkingService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/file")
@Api(tags = "File")
public class FileController {
    private final IFileWorkingService fileService;

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
        return fileService.getFile(filename);
    }
}
