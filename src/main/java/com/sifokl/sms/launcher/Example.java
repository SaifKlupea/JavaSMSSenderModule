package com.sifokl.sms.launcher;

import com.sifokl.sms.config.Config;
import com.sifokl.sms.config.ConfigTypeFactory;
import com.sifokl.sms.logging.CustomLogger;
import com.sifokl.sms.util.StringUtils;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

public class Example{

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args){


        CustomLogger.setup();
        Optional<Config> optConf = ConfigTypeFactory.getConfig();


        if(!optConf.isPresent()){
            logger.severe("Invalid Config : can't read config");

            System.exit(-1);
        }

        Config conf = optConf.get();
        logger.info(conf.toString());

        Twilio.init(conf.getACCOUNT_SID(), conf.getAUTH_TOKEN());


        PhoneNumber to = new PhoneNumber(conf.getTO_NUMBER());
        PhoneNumber from =  new PhoneNumber(conf.getTRIAL_NUMBER());
        String body = "Hello , your example test message is sent now "+new Date()+" from CONFIG : "+conf.getConfigType();
        MessageCreator msgCreator = Message.creator(to , from , body);


        try {
            Message message = msgCreator.create();
        }catch(ApiException e){
            logger.severe("API EXCEPTION : verify auth credentials (could be compromised) : Exception Message : "+e.getMessage());
            e.printStackTrace();
        }


        logger.info("### END of Program ###");

    }
}