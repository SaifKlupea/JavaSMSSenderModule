package com.sifokl.sms.config;

import com.sifokl.sms.logging.CustomLogFormatter;
import com.sifokl.sms.logging.CustomLogger;
import com.sifokl.sms.util.FileUtils;
import com.sifokl.sms.values.Values;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigTypeFactoryTest {



    private String TEST_RESOURCES_PATH = "src/test/resources/";
    private String XML_DEFAULT_CONFIG_PATH_FILE = TEST_RESOURCES_PATH + Values.DEFAULT_CONFIG_SMS_FILE_NAME_XML;
    private String JSON_DEFAULT_CONFIG_PATH_FILE= TEST_RESOURCES_PATH + Values.DEFAULT_CONFIG_SMS_FILE_NAME_XML;
    private String RENAMED_XML_DEFAULT_CONFIG_PATH_FILE = TEST_RESOURCES_PATH + "RENAMED_"+Values.DEFAULT_CONFIG_SMS_FILE_NAME_XML;
    private String RENAMED_JSON_DEFAULT_CONFIG_PATH_FILE= TEST_RESOURCES_PATH + "RENAMED_"+Values.DEFAULT_CONFIG_SMS_FILE_NAME_XML;

    private String[] files = {
            XML_DEFAULT_CONFIG_PATH_FILE,
            JSON_DEFAULT_CONFIG_PATH_FILE};

    private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    private void renameFiles(String ... filesName) {

        if(filesName.length % 2 > 0) {
            String message = "Can't be called with odd size of arguments list ! 1st arg is old name file#1 , 2 nd arg is new name for file#1 ... etc";
            log.warning(message);
            fail(message);
        }

        for(int i = 0 ; i < files.length ; i=i+2){
            try {
                FileUtils.renameFile(filesName[i], filesName[i + 1]);
            }catch (IOException e){
                log.severe(e.getCause().toString());
                log.severe(e.getMessage());
                fail("can't rename files because : "+e.getMessage());
            }
        }

    }

    private void assertFilesExists(String[] files){
        for(String file : files){
            assertTrue(FileUtils.isFileExist(file), "before assumptions : check that the file "+file+" exists");
        }
    }

    @BeforeAll
    static void setup() {
        log.info("Set UP Logger");
        CustomLogger.setup();
        log.info("@BeforeAll - executes once before all test methods in this class");
    }



    @BeforeEach
    void init() {
        log.info("\n-----------------------------------------------\n");

        log.info("@BeforeEach - executes before each test method in this class");
        log.info("verify that all config tests files exist and have the initial names (could be changed during tests in purpose");

        assertFilesExists(files);

    }

    @AfterEach
    void afterEach(){

        log.info("@AfterEach - executes after each test method in this class");
        log.info("verify that all config tests files exist and have the initial names (could be changed during tests in purpose");

        if(FileUtils.isFileExist(RENAMED_XML_DEFAULT_CONFIG_PATH_FILE))
            renameFiles(RENAMED_XML_DEFAULT_CONFIG_PATH_FILE, XML_DEFAULT_CONFIG_PATH_FILE);
        if(FileUtils.isFileExist(RENAMED_JSON_DEFAULT_CONFIG_PATH_FILE))
            renameFiles(RENAMED_JSON_DEFAULT_CONFIG_PATH_FILE, JSON_DEFAULT_CONFIG_PATH_FILE);

        assertFilesExists(files);

        log.info("\n-----------------------------------------------\n");
    }


    @DisplayName("test load default config xml ")
    @Test
    void getConfig_XML_DEFAULT() {
        log.info("**********   START **********");

        Optional<Config> optConf = ConfigTypeFactory.getConfig();

        assertTrue(optConf.isPresent(), "Optional is present");

        Config conf = optConf.get();

        assertEquals(ConfigEnumType.XML,conf.getConfigType() , "check that config type is XML");
        assertEquals("val 1",conf.getACCOUNT_SID(), "check account sid");
        assertEquals("val 2", conf.getAUTH_TOKEN(), "check account token");
        assertEquals("val 3",conf.getTRIAL_NUMBER(), "check trial number");
        assertEquals("val 4", conf.getTO_NUMBER(), "check to number");

        log.info("**********   END WITH SUCCESS **********");
    }

    @Disabled
    @DisplayName("test load default config json ")
    @Test
    void getConfig_JSON_DEFAULT() {


        log.info("**********   START **********");

        //rename the xml file (thus it will be ignored as default config)
        renameFiles(XML_DEFAULT_CONFIG_PATH_FILE, RENAMED_XML_DEFAULT_CONFIG_PATH_FILE);//RENAMED_XML_DEFAULT_CONFIG_PATH_FILE);

        Optional<Config> optConf = ConfigTypeFactory.getConfig();

        assertTrue(optConf.isPresent(), "Optional is present");

        Config conf = optConf.get();

        assertEquals(ConfigEnumType.JSON,conf.getConfigType() , "check that config type is JSON");
        assertEquals("json sid",conf.getACCOUNT_SID(), "check account sid");
        assertEquals("json token", conf.getAUTH_TOKEN(), "check account token");
        assertEquals("json trial number",conf.getTRIAL_NUMBER(), "check trial number");
        assertEquals("json to number", conf.getTO_NUMBER(), "check to number");

        log.info("**********   END WITH SUCCESS **********");
    }



}
