package com.sifokl.sms.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sifokl.sms.util.FileUtils;
import com.sifokl.sms.values.Values;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class ConfigJSON extends Config {

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void load() {
        logger.info("*** START load() ***");
        load(Values.DEFAULT_CONFIG_SMS_FILE_NAME_JSON);
        logger.info("*** END load() ***");
    }

    @Override
    public void load(String fileName) {
        logger.info("*** END ***");
        Config conf = jsonToConfig(fileName);
        logger.info(conf.toString());

        this.ACCOUNT_SID = conf.getACCOUNT_SID();
        this.AUTH_TOKEN = conf.getAUTH_TOKEN();
        this.TO_NUMBER = conf.getTO_NUMBER();
        this.TRIAL_NUMBER = conf.getTRIAL_NUMBER();

        logger.info("*** END ***");

    }

    private Config jsonToConfig(String fileName){
        logger.info("*** START ***");
        ConfigJSON conf = null;

        try {

            String fileEffectiveName = FileUtils.getFileAsResource(fileName);
            File file = new File(fileEffectiveName);
            logger.info(file.toString());
            // JSON file to Java object
            ObjectMapper mapper = new ObjectMapper();
            //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            conf = mapper.readValue(file, ConfigJSON.class);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("*** END ***");
        return conf;

    }
}
