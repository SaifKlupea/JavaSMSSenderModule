package com.sifokl.sms.util;

import java.util.Base64;

public class StringUtils {

    public static String truncate(String input , int size){

        if(null == input || input.length() < size)
            return input+"...";

        return input.substring(0, size)+"...";
    }

    public static String toBase64(String input){
        if(null == input) return null;
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
        return encodedString;
    }

    /**
     * @param str input string
     * @param size size of final string (complete with blank)
     * @param strictMode if input string size > param size , trunc input string if true
     * @return string completed with blank until the final size reaches input size
     */
    public static String completeStringWithBlank(String str , int size , boolean strictMode){

        StringBuffer strBuff = new StringBuffer(size);
        strBuff.append(str);
        for(int i = str.length() ; i < size ; i++){
            strBuff.append(" ");
        }
        if(strictMode) return strBuff.substring(0,size);
        return strBuff.toString();
    }


}
