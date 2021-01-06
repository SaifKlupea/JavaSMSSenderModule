package com.sifokl.sms.config;

import java.util.Optional;

public class ConfigTypeFactory {


    public static Optional<Config> getConfig(){

        for (ConfigEnumType type : ConfigEnumType.values()){

            if(type.equals(ConfigEnumType.XML)){

                Config conf = new ConfigXML();
                conf.load();
                conf.setConfType(type);
                if(conf.validate())
                    return Optional.of(conf);

            }

            if(type.equals(ConfigEnumType.JSON)){

                Config conf = new ConfigJSON();
                conf.load();
                conf.setConfType(type);
                if(conf.validate())
                    return Optional.of(conf);

            }

            if(type.equals(ConfigEnumType.ENV)){

                Config conf = new ConfigEnv();
                conf.load();
                conf.setConfType(type);
                if(conf.validate())
                    return Optional.of(conf);

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
