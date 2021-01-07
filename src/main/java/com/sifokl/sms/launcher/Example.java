package com.sifokl.sms.launcher;

import com.sifokl.sms.config.Config;
import com.sifokl.sms.config.ConfigTypeFactory;
import com.sifokl.sms.config.ConfigXML;
import com.sifokl.sms.logging.CustomLogger;
import com.sifokl.sms.util.StringUtils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

public class Example{

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args){

        CustomLogger.setup();
        Optional<Config> optConf = ConfigTypeFactory.getConfig();


        if(!optConf.isPresent()){
            logger.severe("Invalid Config : ");

            System.exit(-1);
        }

        Config conf = optConf.get();
        logger.info(conf.toString());

        logger.severe("Credentials (truncated BASE64): ");
        logger.info("ACCOUNT_SID : "+ StringUtils.truncate(StringUtils.toBase64(conf.getACCOUNT_SID()),7));
        logger.info("AUTH_TOKEN : "+StringUtils.truncate(StringUtils.toBase64(conf.getAUTH_TOKEN()),7));
        logger.info("TRIAl_NUMBER : "+conf.getTRIAL_NUMBER());


        Twilio.init(conf.getACCOUNT_SID(), conf.getAUTH_TOKEN());

        Message message = Message.creator(
                new PhoneNumber(conf.getTO_NUMBER()),
                new PhoneNumber(conf.getTRIAL_NUMBER()),
                "Hello , your example test message is sent now "+new Date()+" from CONFIG : "+conf.getConfigType())
           .create();


        logger.info(message.toString());

    }
}