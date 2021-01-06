package com.sifokl.sms.launcher;

import com.sifokl.sms.config.Config;
import com.sifokl.sms.config.ConfigTypeFactory;
import com.sifokl.sms.util.StringUtils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Date;
import java.util.Optional;

public class Example{

    public static void main(String[] args){

        Optional<Config> optConf = ConfigTypeFactory.getConfig();


        if(!optConf.isPresent()){
            System.err.println("Invalid Config : ");

            System.exit(-1);
        }

        Config conf = optConf.get();
        System.out.println(conf);

        System.err.println("Credentials (truncated BASE64): ");
        System.out.println("ACCOUNT_SID : "+ StringUtils.truncate(StringUtils.toBase64(conf.getACCOUNT_SID()),7));
        System.out.println("AUTH_TOKEN : "+StringUtils.truncate(StringUtils.toBase64(conf.getAUTH_TOKEN()),7));
        System.out.println("TRIAl_NUMBER : "+conf.getTRIAL_NUMBER());
        Twilio.init(conf.getACCOUNT_SID(), conf.getAUTH_TOKEN());

        Message message = Message.creator(
                new PhoneNumber(conf.getTO_NUMBER()),
                new PhoneNumber(conf.getTRIAL_NUMBER()),
                "Hello , your example test message is sent now "+new Date())
           .create();


        System.out.println(message);

    }
}