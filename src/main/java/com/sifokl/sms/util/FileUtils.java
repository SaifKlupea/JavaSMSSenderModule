package com.sifokl.sms.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class FileUtils {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public static String getFileAsResource(String fileName) throws FileNotFoundException {
        logger.info("*** FUNCTION START ***");

        //"src/main/resources/sms.config.xml"

        URL url = Thread.currentThread().getContextClassLoader().getResource( fileName );
        if( null == url ) {
            String message = "The file "+fileName+" is not a resource or does not exist";
            logger.warning(message);
            throw new FileNotFoundException(message);

        }
        String result = url.getFile();

        logger.info(fileName+" is in classpath "+result);
        logger.info("*** FUNCTION END ***");
        return result ;
    }

    public static boolean isFileExist(String path) {
        logger.info("*** FUNCTION START ***");
        File f = new File(path);
        boolean result = (f.exists() && !f.isDirectory());
        logger.info(path+" is directory ? "+result);
        logger.info("*** FUNCTION END ***");
        return result ;
    }


    public static boolean isDirectory(String path){
        logger.info("*** FUNCTION START ***");
        File f = new File(path);
        boolean result = f.exists() && f.isDirectory();
        logger.info(path+" is directory ? "+result);
        logger.info("*** FUNCTION END ***");
        return result;
    }

    public static void createFolder(String pathAsString){
        logger.info("*** FUNCTION START ***");
        Path path = Paths.get(pathAsString);

        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            logger.warning("Can't create log directory ! "+e.getMessage());
            logger.info("*** FUNCTION END with EXCEPTION THROWN ***");
        }

        logger.info("*** FUNCTION END ***");
    }


}
