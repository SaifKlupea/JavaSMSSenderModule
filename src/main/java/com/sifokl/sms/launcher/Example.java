package com.sifokl.sms.launcher;

import com.sifokl.sms.util.StringUtils;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.Date;

public class Example{
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_TOKEN");
    public static final String TRIAL_NUMBER = System.getenv("TWILIO_TRIAL_NUMBER");
    public static final String TO_NUMBER = System.getenv("TWILIO_TO_NUMBER");

    public static void main(String[] args){

        if(null == ACCOUNT_SID || null == AUTH_TOKEN || null == TRIAL_NUMBER || null == TO_NUMBER){
            System.err.println("Invalid Credentials : ");
            System.out.println("ACCOUNT_SID : "+ACCOUNT_SID);
            System.out.println("AUTH_TOKEN : "+AUTH_TOKEN);
            System.out.println("TRIAl_NUMBER : "+TRIAL_NUMBER );
            System.out.println("TRIAl_NUMBER : "+TO_NUMBER );
            System.exit(-1);
        }


        System.err.println("Credentials (truncated BASE64): ");
        System.out.println("ACCOUNT_SID : "+ StringUtils.truncate(StringUtils.toBase64(ACCOUNT_SID),7));
        System.out.println("AUTH_TOKEN : "+StringUtils.truncate(StringUtils.toBase64(AUTH_TOKEN),7));
        System.out.println("TRIAl_NUMBER : "+TRIAL_NUMBER);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(

                new PhoneNumber(TO_NUMBER),
                new PhoneNumber(TRIAL_NUMBER),
                "Hello , your example test message is sent now "+new Date()).create();


        System.out.println(message);

    }
}