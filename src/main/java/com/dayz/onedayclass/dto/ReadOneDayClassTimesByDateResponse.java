package com.dayz.onedayclass.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadOneDayClassTimesByDateResponse {

    private List<OneDayClassTimeResult> classTimes;

    public static ReadOneDayClassTimesByDateResponse of(
            List<OneDayClassTimeResult> classTimes) {
        ReadOneDayClassTimesByDateResponse readOneDayClassTimesByDateResponse = new ReadOneDayClassTimesByDateResponse();
        readOneDayClassTimesByDateResponse.setClassTimes(classTimes);

        return readOneDayClassTimesByDateResponse;
    }

    @Getter
    @Setter(AccessLevel.PRIVATE)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class OneDayClassTimeResult {

        private Long classTimeId;

        private int currentPeopleNumber;

        private String startTime;

        private String endTime;

        private Boolean status;

        public static OneDayClassTimeResult of(
                Long classTimeId,
                int currentPeopleNumber,
                String startTime,
                String endTime,
                Boolean status) {
            OneDayClassTimeResult oneDayClassTimeResult = new OneDayClassTimeResult();
            oneDayClassTimeResult.setClassTimeId(classTimeId);
            oneDayClassTimeResult.setCurrentPeopleNumber(currentPeopleNumber);
            oneDayClassTimeResult.setStartTime(startTime);
            oneDayClassTimeResult.setEndTime(endTime);
            oneDayClassTimeResult.setStatus(status);

            return oneDayClassTimeResult;
        }

    }

}
