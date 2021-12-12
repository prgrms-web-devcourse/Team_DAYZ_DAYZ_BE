package com.dayz.onedayclass.converter;

import com.dayz.common.util.ImageUrlUtil;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.dto.ReadOneDayClassesByCategoryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OneDayClassConverter {

    private final ImageUrlUtil imageUrlUtil;

    public ReadOneDayClassesByCategoryResult convertToReadOneDayClassesByCategoryResult(OneDayClass oneDayClass) {
        return ReadOneDayClassesByCategoryResult.of(
                oneDayClass.getId(),
                oneDayClass.getName(),
                oneDayClass.getIntro(),
                oneDayClass.getOneDayClassImages().size() > 0 ?
                        imageUrlUtil.makeImageUrl(oneDayClass.getOneDayClassImages()
                                .get(0)
                                .getImageFileName()) :
                        null
        );
    }

}
