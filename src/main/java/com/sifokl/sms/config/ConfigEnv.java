package com.sifokl.sms.config;

import com.sifokl.sms.values.Values;

public class ConfigEnv extends Config{


    @Override
    public void load() {
        this.ACCOUNT_SID = System.getenv(Values.KEY_ACCOUNT_SID);
        this.AUTH_TOKEN = System.getenv(Values.KEY_AUTH_TOKEN);
        this.TRIAL_NUMBER = System.getenv(Values.KEY_TRIAL_NUMBER);
        this.TO_NUMBER = System.getenv(Values.KEY_TO_NUMBER);
    }

    @Override
    public void load(String fileName) {

    }
}
