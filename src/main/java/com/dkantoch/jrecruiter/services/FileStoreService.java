package com.dkantoch.jrecruiter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStoreService
{
    private final Logger logger = LoggerFactory.getLogger(FileStoreService.class);

    //@Value("${app.upload.dir:${user.home}}")
    private String saveDirectoryUrl="C:\\Users\\kanto\\IdeaProjects\\jrecruiter\\src\\main\\webapp\\src\\assets";

    public String saveFile(MultipartFile file, String username, boolean isImage)
    {
        String result = "";
        logger.warn("Trying to save "+file.getOriginalFilename());
        try
        {
            String saveLocation;
            if(isImage)
            {
                saveLocation=saveDirectoryUrl+File.separator+username+"/images";
            }
            else
            {
                saveLocation=saveDirectoryUrl+File.separator+username;
            }
            File directory = new File(saveLocation);
            if(!directory.exists())
            {
                logger.warn("Creating new directory: {}",directory.getAbsolutePath());
                boolean mkdirsResult = directory.mkdirs();
                if(!mkdirsResult)
                    logger.error("Error while creating new directory!");
            }


            Path copyLocation = Paths.get(saveLocation + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            File savedFile = new File(saveLocation+File.separator+file.getOriginalFilename());
            if(savedFile.exists())
                result = savedFile.getAbsolutePath().substring(59);
        }
        catch (Exception e)
        {
            logger.error("Error while saving new file {}. Message: {}",file.getOriginalFilename(),e.getMessage());
            result = "";
        }
        return result;
    }

    public boolean deleteOldImage(String fileName)
    {
        String fileNameFromDb = fileName.substring(7);
        File file = new File(saveDirectoryUrl+File.separator+fileNameFromDb);
        if(file.exists())
        {
            boolean operationResult = file.delete();
            return operationResult;
        }
        else
            return false;
    }
}
