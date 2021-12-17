package com.dayz.onedayclass.dto.query;

public interface CurrentOneDayClassTime {

    Long getClassTimeId();

    Long getStartTime();

    Long getEndTime();

    int getCurrentPeopleNumber();

    int getMaxPeopleNumber();

    String getStatus();

}
