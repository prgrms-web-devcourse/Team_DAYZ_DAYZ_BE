package com.dayz.common.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TimeUtilTest {

    @Autowired
    private TimeUtil timeUtil;

    @Test
    @DisplayName("시간 문자열을 초로 변환한다.")
    void timeStringToSecond() {
        Long sec = timeUtil.timeStringToSecond("12:30");
        assertThat(sec, is(45000L));
    }

    @Test
    @DisplayName("초를 시간 문자열로 반환한다.")
    void secondToTimeString() {
        String s = timeUtil.secondToTimeString(45000L);
        assertThat(s, equalTo("12:30"));
    }

}
