package com.ifire.webservice.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifire.webservice.configuration.iFireProperties;

@Service
public class FileService {

    @Autowired
    iFireProperties iFireProperties;

    public String saveBase64StringFile(String image) {
        String fileName = UUID.randomUUID().toString();

        Path path = getProfileImagePath(fileName);

        try {
            OutputStream outputStream = new FileOutputStream(path.toFile());

            byte[] base64decoded = Base64.getDecoder().decode(image.split(",")[1]);

            outputStream.write(base64decoded);

            outputStream.close();

            return fileName;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public void deleteProfileImage(String image) {
        if (image == null)
            return;
        Path path = getProfileImagePath(image);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Path getProfileImagePath(String fileName) {
        return Paths.get(iFireProperties.getStorage().getRoot(), iFireProperties.getStorage().getProfile(),
                fileName);
    }

}
