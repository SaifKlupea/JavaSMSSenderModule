package com.sifokl.sms.logging;



import com.sifokl.sms.util.FileUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class CustomLogger {

    private static String levelName = "ALL"; //can be injected by spring
    private static  String consoleLevelName = "ALL"; //can be injected by spring
    private static  String fileLevelName = "ALL";  //can be injected by spring
    private static String logDirectoryPath = "logs/";  //can be injected by spring


    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

     public static void setup()  {

         //level = Level.parse("ALL");



        // suppress the logging output to the console
        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);
        }


        //console handler
        StreamHandler consoleHandler = createConsoleHandler();
        logger.addHandler(consoleHandler);

        //file handler
        StreamHandler fileHandler = createFileHandler();
        logger.addHandler(fileHandler);

        logger.setLevel(Level.parse(levelName));

    }

    private static StreamHandler createConsoleHandler(){

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new CustomLogFormatter());
        Level lev = Level.parse(consoleLevelName);
        consoleHandler.setLevel(lev);


        return consoleHandler;
    }

    private static  StreamHandler createFileHandler(){
        StreamHandler fileHandler = new ConsoleHandler();
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMdd-HH_mm_ss");
            String suffName = df.format(new Date());

            //create log directory if not exist
            if(!FileUtils.isDirectory(logDirectoryPath)) FileUtils.createFolder(logDirectoryPath);

            String logFileRelativepath=logDirectoryPath+"/logs_" + suffName + ".log";
            logger.info("log file relative path is is : "+logFileRelativepath);
            fileHandler = new FileHandler(logFileRelativepath);
            fileHandler.setFormatter(new CustomLogFormatter());
            fileHandler.setLevel(Level.parse(fileLevelName));
        }catch(Exception e){
            logger.severe("ERROR : CAN'T CONFIGURE FILE LOGGING ! "+e.getMessage());
        }finally {
            return fileHandler;
        }

        //return fileHandler;
    }


    public  String getConsoleLevelName() {
        return consoleLevelName;
    }

    public  void setConsoleLevelName(String consoleLevelName) {
        this.consoleLevelName = consoleLevelName;
    }

    public  String getFileLevelName() {
        return this.fileLevelName;
    }

    public  void setFileLevelName(String fileLevelName) {
        this.fileLevelName = fileLevelName;
    }

    public static String getLogDirectoryPath() {
        return logDirectoryPath;
    }

    public static void setLogDirectoryPath(String logDirectoryPath) {
        CustomLogger.logDirectoryPath = logDirectoryPath;
    }

    public static void setLevelName(String levelName){
         CustomLogger.levelName = levelName;
    }

    public static String getLevelName(){
         return levelName;
    }

}
