package com.sifokl.sms.config;

import java.util.Optional;
import java.util.logging.Logger;

public class ConfigTypeFactory {

    public static  final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static Optional<Config> getConfig(){
        logger.info("*** START ***");
        for (ConfigEnumType type : ConfigEnumType.values()){

            if(type.equals(ConfigEnumType.XML)){
                logger.info("Trying to load config with "+type);

                Config conf = new ConfigXML();
                conf.load();
                conf.setConfType(type);
                if(conf.validate()) {
                    logger.info("The config loaded and validated is "+type);
                    return Optional.of(conf);
                }

            }

            if(type.equals(ConfigEnumType.JSON)){

                logger.info("Trying to load config with "+type);
                Config conf = new ConfigJSON();
                conf.load();
                conf.setConfType(type);
                if(conf.validate()) {
                    logger.info("The config loaded and validated is "+type);
                    return Optional.of(conf);
                }

            }

            if(type.equals(ConfigEnumType.ENV)){

                logger.info("Trying to load config with "+type);
                Config conf = new ConfigEnv();
                conf.load();
                conf.setConfType(type);
                if(conf.validate()) {
                    logger.info("The config loaded and validated is "+type);
                    return Optional.of(conf);
                }

            }


        }

        return Optional.empty();

    }

/*

    public static Optional<Config> getConfig(String fileName){

        for (ConfigEnumType type : ConfigEnumType.values()){

            if(type.equals(ConfigEnumType.XML)){

                Config conf = new ConfigXML(fileName);
                if(conf.validate()) return Optional.of(conf);
            }

            if(type.equals(ConfigEnumType.JSON)){

                Config conf = new ConfigJSON(fileName);
                if(conf.validate()) return Optional.of(conf);
            }

            if(type.equals(ConfigEnumType.ENV)){

                Config conf = new ConfigEnv();
                if(conf.validate()) return Optional.of(conf);
            }


        }

        return null;

    }

*/

}
