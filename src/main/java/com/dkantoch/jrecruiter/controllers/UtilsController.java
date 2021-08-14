package com.dkantoch.jrecruiter.controllers;

import com.dkantoch.jrecruiter.services.UtilsService;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/utils")
public class UtilsController
{
    private final Logger logger = LoggerFactory.getLogger(UtilsController.class);

    private final UtilsService utilsService;

    public UtilsController(UtilsService utilsService)
    {
        this.utilsService = utilsService;
    }

    @GetMapping("/version")
    public String getSoftwareVersion()
    {
        try
        {
            return utilsService.getVersion();
        }
        catch (XmlPullParserException | IOException e)
        {
            logger.error(e.getMessage());
            return "Wystąpił problem podczas pobierania wersji programu. Skontakuj się z administratorem!";
        }
    }
}
