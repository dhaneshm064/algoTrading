package com.stockmarket.technicalanalysis.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {

    public static Timestamp getTimeStampFromString(String timeStampString, String format) throws Exception {
        try {
            DateFormat formatter = new SimpleDateFormat(format);
            // you can change format of date
            Date dateFormat = formatter.parse(timeStampString);
            return new Timestamp(dateFormat.getTime());
        }
        catch (Exception e){
            log.debug("Unable to parse string "+timeStampString+" to timestamp with format "+format, e);
            throw new Exception("Unable to parse string "+timeStampString+" to timestamp with format "+format, e);
        }
    }
}
