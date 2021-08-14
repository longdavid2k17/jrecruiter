package com.dkantoch.jrecruiter.services;

import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

@Service
public class UtilsService
{
    private final Logger logger = LoggerFactory.getLogger(UtilsService.class);

    public String getVersion() throws XmlPullParserException, IOException
    {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model;
        if ((new File("pom.xml")).exists())
            model = reader.read(new FileReader("pom.xml"));
        else
            model = reader.read(
                    new InputStreamReader(
                            Objects.requireNonNull(ObjectMetaData.Application.class.getResourceAsStream(
                                    "/META-INF/maven/polsl/pbl-server-app/pom.xml"
                            ))
                    )
            );
        logger.info("Software version {}",model.getVersion());
        return model.getVersion();
    }
}
