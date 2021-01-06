package com.sifokl.sms.config;

import com.sifokl.sms.values.Values;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ConfigXML extends Config{


    @Override
    public void load() {
        load(Values.DEFAULT_CONFIG_SMS_FILE_NAME);
    }

    @Override
    public void load(String fileName) {

    }


    private ConfigXML xmlToConfig(String fileName){

        ConfigXML conf = new ConfigXML();

        try {

            File file = new File(fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(ConfigXML.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            conf = (ConfigXML) jaxbUnmarshaller.unmarshal(file);


        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return conf;

    }

}
