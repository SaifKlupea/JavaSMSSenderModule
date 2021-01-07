package com.sifokl.sms.logging;


import com.sifokl.sms.util.StringUtils;
import com.sifokl.sms.values.Values;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {


    @Override
    public String format(LogRecord record) {


        StringBuffer stringBuffer = new StringBuffer();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd:::HH:mm:ss");



        stringBuffer.append(df.format(new Date(record.getMillis())));
        stringBuffer.append("\t");
        stringBuffer.append(record.getLevel());
        stringBuffer.append("\t");
        stringBuffer.append(StringUtils.completeStringWithBlank(record.getSourceClassName() , Values.CONFIG_MAX_LENGTH_CLASS_LOG, true));
        stringBuffer.append(" # ");
        stringBuffer.append(StringUtils.completeStringWithBlank(record.getSourceMethodName() , Values.CONFIG_MAX_LENGTH_METHOD_LOG , true));
        stringBuffer.append("\t");
        stringBuffer.append(record.getMessage());
        stringBuffer.append("\n");
        return stringBuffer.toString();
    }

    public String getHead(Handler h) {

        return "Start LOG "+h.getClass().getSimpleName()+": "+new Date()+"\n";
    }

    public String getTail(Handler h) {
        return "END LOG  : "+new Date()+"\n" ;
    }

}
