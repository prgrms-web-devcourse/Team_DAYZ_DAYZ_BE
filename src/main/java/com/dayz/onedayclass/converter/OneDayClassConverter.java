package com.dayz.onedayclass.converter;

import com.dayz.atelier.domain.Atelier;
import com.dayz.common.util.ImageUrlUtil;
import com.dayz.common.util.TimeUtil;
import com.dayz.member.domain.Address;
import com.dayz.onedayclass.domain.Curriculum;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassImage;
import com.dayz.onedayclass.dto.ReadOneDayClassDetailResponse;
import com.dayz.onedayclass.dto.ReadOneDayClassesByCategoryResult;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OneDayClassConverter {

    private final ImageUrlUtil imageUrlUtil;

    private final TimeUtil timeUtil;

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

    public ReadOneDayClassDetailResponse convertToReadOneDayClassDetailResponse(OneDayClass oneDayClass, double avgScore) {
        return ReadOneDayClassDetailResponse.of(
                oneDayClass.getId(),
                oneDayClass.getName(),
                oneDayClass.getIntro(),
                oneDayClass.getPrice(),
                avgScore,
                oneDayClass.getMaxPeopleNumber(),
                oneDayClass.getOneDayClassImages().stream()
                        .map(this::convertToReadOneDayClassDetailOneDayClassImageResult)
                        .collect(Collectors.toList()),
                oneDayClass.getCurriculums().stream()
                        .map(this::convertToReadOneDayClassDetailCurriculum)
                        .collect(Collectors.toList()),
                convertToReadOneDayClassDetailAtelierResult(oneDayClass.getAtelier())
        );
    }

    public ReadOneDayClassDetailResponse.OneDayClassImageResult convertToReadOneDayClassDetailOneDayClassImageResult(
            OneDayClassImage oneDayClassImage) {
        return ReadOneDayClassDetailResponse.OneDayClassImageResult.of(
                imageUrlUtil.makeImageUrl(oneDayClassImage.getImageFileName()),
                oneDayClassImage.getSequence()
        );
    }

    public ReadOneDayClassDetailResponse.CurriculumResult convertToReadOneDayClassDetailCurriculum(Curriculum curriculum) {
        return ReadOneDayClassDetailResponse.CurriculumResult.of(
                curriculum.getId(),
                curriculum.getStep(),
                curriculum.getContent()
        );
    }

    public ReadOneDayClassDetailResponse.AtelierResult convertToReadOneDayClassDetailAtelierResult(Atelier atelier) {
        return ReadOneDayClassDetailResponse.AtelierResult.of(
                atelier.getId(),
                atelier.getName(),
                getFullAddress(atelier.getAddress(), atelier.getDetail()),
                null,
                timeUtil.secondToTimeString(atelier.getWorkTime().getStartTime()),
                timeUtil.secondToTimeString(atelier.getWorkTime().getEndTime())
        );
    }

    private String getFullAddress(Address address, String detail) {
        String cityName = address.getCityName();
        String regionName = address.getRegionName();

        return cityName + " " + regionName + " " + detail;
    }

}
