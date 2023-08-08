package com.bfc.appmgmt.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * packageName    : com.bfc.appmgmt.util
 * fileName       : DateUtil
 * author         : joyousang
 * date           : 2023/08/08
 * description    :
 */
@UtilityClass
public class DateUtil {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";

    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT));
    }
}