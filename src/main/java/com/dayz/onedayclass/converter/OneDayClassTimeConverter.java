package com.dayz.onedayclass.converter;

import com.dayz.common.enums.TimeStatus;
import com.dayz.common.util.TimeUtil;
import com.dayz.onedayclass.dto.ReadOneDayClassTimesByDateResponse;
import com.dayz.onedayclass.dto.query.CurrentOneDayClassTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OneDayClassTimeConverter {

    private final TimeUtil timeUtil;

    public ReadOneDayClassTimesByDateResponse convertToReadOneDayClassTimesByDateResponse(List<CurrentOneDayClassTime> oneDayClassTimes) {
        return ReadOneDayClassTimesByDateResponse.of(
                oneDayClassTimes.stream()
                        .map(this::convertToReadOneDayClassTimesByDateOneDayClassTimeResult)
                        .collect(Collectors.toList())
        );
    }

    public ReadOneDayClassTimesByDateResponse.OneDayClassTimeResult convertToReadOneDayClassTimesByDateOneDayClassTimeResult(
            CurrentOneDayClassTime oneDayClassTime) {
        return ReadOneDayClassTimesByDateResponse.OneDayClassTimeResult.of(
                oneDayClassTime.getClassTimeId(),
                oneDayClassTime.getCurrentPeopleNumber(),
                timeUtil.secondToTimeString(oneDayClassTime.getStartTime()),
                timeUtil.secondToTimeString(oneDayClassTime.getEndTime()),
                ckeckTimeStatus(oneDayClassTime)
        );
    }

    private boolean ckeckTimeStatus(CurrentOneDayClassTime classTimeQuery) {
        LocalDateTime now = LocalDateTime.now();

        if(classTimeQuery.getStatus().equals(TimeStatus.PROCESS.getValue())
                && ( classTimeQuery.getCurrentPeopleNumber() < classTimeQuery.getMaxPeopleNumber() )
                && ( timeUtil.localTimeToSecond(now.toLocalTime()) < classTimeQuery.getStartTime())
        ){
            return true;
        }
        return false;
    }

}
