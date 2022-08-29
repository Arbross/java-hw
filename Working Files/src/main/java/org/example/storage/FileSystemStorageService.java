package org.example.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Stream;

import org.example.storage.ImageUtils;

@Service
public class FileSystemStorageService implements org.example.storage.IStorageService {
    private final Path rootLocation;
    private final String[] sizes;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.sizes = properties.getSizes();
    }

    @Override
    public void init() {
        try {
            if(!Files.exists(rootLocation))
            {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public String store(String base64) {
        try {
            if (base64.isEmpty()) {
                throw new StorageException("Failed to store empty base64 ");
            }

            String[] charArray = base64.split(",");
            String extension;

            switch (charArray[0]) {
                case "data:image/png;base64":
                    extension = "png";
                    break;
                case "data:image/jpeg;base64":
                    extension = "jpeg";
                    break;
                default:
                    extension = "jpg";
                    break;
            }

            byte[] bytes = Base64.getDecoder().decode(charArray[1].replace("\"", ""));

            String result = "";
            for (String value : sizes)
            {
                UUID uuid = UUID.randomUUID();
                String randomFileName = uuid.toString() + "." + extension;

                BufferedImage newImg = ImageUtils.resizeImage(ImageIO.read(new ByteArrayInputStream(bytes)),
                        extension == "jpg" ? ImageUtils.IMAGE_JPEG : ImageUtils.IMAGE_PNG, Integer.parseInt(value), Integer.parseInt(value));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                ImageIO.write(newImg, extension, byteArrayOutputStream);
                bytes = byteArrayOutputStream.toByteArray();
                new FileOutputStream(rootLocation.toString() + "/" + randomFileName).write(bytes);

                result += randomFileName + ";";
            }

            return result;
        } catch (Exception e) {
            throw new StorageException("Failed to store file ", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
