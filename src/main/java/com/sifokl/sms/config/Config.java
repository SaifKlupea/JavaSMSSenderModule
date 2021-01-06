package com.sifokl.sms.config;

public abstract class Config {


    protected  String ACCOUNT_SID ;
    protected  String AUTH_TOKEN ;
    protected  String TRIAL_NUMBER;
    protected  String TO_NUMBER ;
    protected  ConfigEnumType configType;


    abstract public void load();

    abstract public void load(String fileName);


    public boolean validate(){
        return ((null != this.ACCOUNT_SID)
                && (null != this.AUTH_TOKEN)
                && (null != this.TO_NUMBER)
                && (null != this.TRIAL_NUMBER));
    }


    public String getACCOUNT_SID() {
        return ACCOUNT_SID;
    }

    public void setACCOUNT_SID(String ACCOUNT_SID) {
        this.ACCOUNT_SID = ACCOUNT_SID;
    }

    public String getAUTH_TOKEN() {
        return AUTH_TOKEN;
    }

    public void setAUTH_TOKEN(String AUTH_TOKEN) {
        this.AUTH_TOKEN = AUTH_TOKEN;
    }

    public String getTRIAL_NUMBER() {
        return TRIAL_NUMBER;
    }

    public void setTRIAL_NUMBER(String TRIAL_NUMBER) {
        this.TRIAL_NUMBER = TRIAL_NUMBER;
    }

    public String getTO_NUMBER() {
        return TO_NUMBER;
    }

    public void setTO_NUMBER(String TO_NUMBER) {
        this.TO_NUMBER = TO_NUMBER;
    }

    public void setConfType(ConfigEnumType type) {
        this.configType = type;
    }


    @Override
    public String toString() {
        return "Config{" +
                "ACCOUNT_SID='" + ACCOUNT_SID + '\'' +
                ", AUTH_TOKEN='" + AUTH_TOKEN + '\'' +
                ", TRIAL_NUMBER='" + TRIAL_NUMBER + '\'' +
                ", TO_NUMBER='" + TO_NUMBER + '\'' +
                ", configType=" + configType +
                '}';
    }
}
