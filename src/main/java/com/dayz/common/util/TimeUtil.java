package com.dayz.common.util;

import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Component;

@Component
public class TimeUtil {

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.KOREA);

    public Long timeStringToSecond(String timeString) {

        Date d = null;
        try {
            d = sdf.parse(timeString);
            int seconds = d.getHours() * 3600
                          + d.getMinutes() * 60;

            return Long.valueOf(seconds);
        } catch (ParseException e) {
            throw new BusinessException(ErrorInfo.UNKNOWN);
        }
    }

    public String secondToTimeString(Long seconds) {

        return String.format(
                "%02d:%02d",
                TimeUnit.SECONDS.toHours(seconds),
                TimeUnit.SECONDS.toMinutes(seconds) -
                TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(seconds))
        );
    }

}
