package com.dayz.onedayclass.service;

import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.onedayclass.converter.OneDayClassTimeConverter;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.onedayclass.domain.OneDayClassTimeRepository;
import com.dayz.onedayclass.dto.ReadOneDayClassTimesByDateResponse;
import com.dayz.onedayclass.dto.query.CurrentOneDayClassTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OneDayClassTimeService {

    private final OneDayClassTimeRepository oneDayClassTimeRepository;

    private final OneDayClassRepository oneDayClassRepository;

    private final OneDayClassTimeConverter oneDayClassTimeConverter;

    public ReadOneDayClassTimesByDateResponse getOneDayClassTimesByDate(Long classId, String date) {
        OneDayClass foundOneDayClass = oneDayClassRepository.findOneDayClassById(classId)
                .orElseThrow(() -> new BusinessException(ErrorInfo.ONE_DAY_CLASS_NOT_FOUND));

        List<CurrentOneDayClassTime> oneDayClassTimesByDate = oneDayClassTimeRepository.findOneDayClassTimesByDate(foundOneDayClass.getId(), date);
//        List<CurrentOneDayClassTimeQuery> oneDayClassTimesByDate = oneDayClassTimeRepository.findOneDayClassTimesByDate(foundOneDayClass.getId());

        return oneDayClassTimeConverter.convertToReadOneDayClassTimesByDateResponse(oneDayClassTimesByDate);
    }

}
