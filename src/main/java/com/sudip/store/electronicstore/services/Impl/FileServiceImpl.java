package com.sudip.store.electronicstore.services.Impl;

import com.sudip.store.electronicstore.exception.BadRequestException;
import com.sudip.store.electronicstore.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String uploadFile(MultipartFile file, String path) throws BadRequestException {
        String originalFileName = file.getOriginalFilename();
        logger.info("Original file name is {}", originalFileName);
        String randomString = UUID.randomUUID().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExtension = randomString.concat(extension);
        String fullPathWithFileName = path.concat(fileNameWithExtension);
        logger.info("Full Path with file name {}", fullPathWithFileName);

        if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg")) {
            File folder = new File(path);

            if (!folder.exists()) {
                //create the folder with given path
                folder.mkdirs();
                logger.info("File object is {}", folder.getName());

            }
            try {
                Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
                return fileNameWithExtension;
            } catch (IOException e) {
                throw new BadRequestException("File upload failed");
            }

        } else {
            throw new BadRequestException("File format " + extension + " is not accepted");
        }


    }

    @Override
    public InputStream getResource(String path, String filename) {
        String fullPath = path.concat(filename);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fullPath);
        } catch (FileNotFoundException e) {
            throw new BadRequestException("File not found");
        }
        return inputStream;
    }
}
