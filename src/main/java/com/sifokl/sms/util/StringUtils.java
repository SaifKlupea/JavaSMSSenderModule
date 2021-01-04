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


}
