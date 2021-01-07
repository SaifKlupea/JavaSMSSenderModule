package com.sifokl.sms.config;

import com.sifokl.sms.util.FileUtils;
import com.sifokl.sms.values.Values;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.net.URL;
import java.util.logging.Logger;

@XmlRootElement(name = "config")
public class ConfigXML extends Config{

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    @Override
    public void load() {
        logger.info("*** START load() ***");
        load(Values.DEFAULT_CONFIG_SMS_FILE_NAME);
        logger.info("*** END load() ***");
    }

    @Override
    public void load(String fileName) {
        logger.info("*** END ***");
        Config conf = xmlToConfig(fileName);

        this.ACCOUNT_SID = conf.getACCOUNT_SID();
        this.AUTH_TOKEN = conf.getAUTH_TOKEN();
        this.TO_NUMBER = conf.getTO_NUMBER();
        this.TRIAL_NUMBER = conf.getTRIAL_NUMBER();

        logger.info("*** END ***");
    }


    private Config xmlToConfig(String fileName){

        ConfigXML conf ;

        try {

            String fileEffectiveName= FileUtils.getFileAsResource(fileName);
            File file = new File(fileEffectiveName);
            //boolean isFileExist =  FileUtils.isFileExist("src/main/resources/sms.config.xml");
            //logger.info("isFileExist : "+isFileExist);
            JAXBContext jaxbContext = JAXBContext.newInstance(ConfigXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            conf = (ConfigXML) jaxbUnmarshaller.unmarshal(file);
            return conf;


        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;

    }

}
