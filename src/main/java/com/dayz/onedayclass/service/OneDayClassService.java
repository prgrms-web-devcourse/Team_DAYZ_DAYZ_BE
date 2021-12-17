package com.dayz.onedayclass.service;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.category.domain.Category;
import com.dayz.category.domain.CategoryRepository;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.onedayclass.converter.OneDayClassConverter;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.onedayclass.dto.ReadOneDayClassByAtelierResult;
import com.dayz.onedayclass.dto.ReadOneDayClassDetailResponse;
import com.dayz.onedayclass.dto.ReadOneDayClassesByCategoryResult;
import com.dayz.onedayclass.dto.ReadPopularOneDayClassesResponse;
import com.dayz.onedayclass.dto.SaveOneDayClassRequest;
import com.dayz.review.domain.ReviewRepository;
import com.querydsl.core.Tuple;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OneDayClassService {

    private final int POPULAR_ONEDAYCLASS_LIMIT = 3;

    private final OneDayClassRepository oneDayClassRepository;

    private final ReviewRepository reviewRepository;

    private final AtelierRepository atelierRepository;

    private final CategoryRepository categoryRepository;

    private final MemberRepository memberRepository;

    private final OneDayClassConverter oneDayClassConverter;

    public CustomPageResponse<CustomPageResponse<ReadOneDayClassesByCategoryResult>> getOneDayClassesByCategory(Member member, Long categoryId, Pageable pageRequest) {

        Category foundCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorInfo.CATEGORY_NOT_FOUND));

        Member foundMember = memberRepository.findById(member.getId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));

        Page<ReadOneDayClassesByCategoryResult> readOneDayClassesByCategoryResultPage = oneDayClassRepository.findOneDayClassByCategoryId(
                foundCategory.getId(),
                foundMember.getAddress().getCityId(),
                foundMember.getAddress().getRegionId(),
                pageRequest
        ).map(oneDayClassConverter::convertToReadOneDayClassesByCategoryResult);

        return CustomPageResponse.<CustomPageResponse<ReadOneDayClassesByCategoryResult>>of(readOneDayClassesByCategoryResultPage);
    }

    public ReadOneDayClassDetailResponse getOneDayClassDetail(Long classId) {
        OneDayClass foundOneDayClass = oneDayClassRepository.findOneDayClassById(classId)
                .orElseThrow(() -> new BusinessException(ErrorInfo.ONE_DAY_CLASS_NOT_FOUND));

        double avgScore = reviewRepository.getReviewAverageByOneDayClass(classId);

        return oneDayClassConverter.convertToReadOneDayClassDetailResponse(foundOneDayClass, avgScore);
    }

    public CustomPageResponse<CustomPageResponse<ReadOneDayClassByAtelierResult>> getOneDayClassesByAtelier(Long atelierId, Pageable pageRequest) {
        Atelier foundAtelier = atelierRepository.findById(atelierId)
                .orElseThrow(() -> new BusinessException(ErrorInfo.ATELIER_NOT_FOUND));

        Page<ReadOneDayClassByAtelierResult> readOneDayClassByAtelierResultPage = oneDayClassRepository.findOneDayClassByAtelierId(foundAtelier.getId(), pageRequest)
                .map(oneDayClassConverter::convertToReadOneDayClassByAtelierResult);

        return CustomPageResponse.<CustomPageResponse<ReadOneDayClassByAtelierResult>> of(readOneDayClassByAtelierResultPage);
    }

    public ReadPopularOneDayClassesResponse getPopularOneDayClasses(Long memberId) {
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));

        // 이번주 시작, 끝 날짜 구하기
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.with(DayOfWeek.MONDAY);
        LocalDate endDate = today.with(DayOfWeek.SUNDAY);
        Address address = foundMember.getAddress();

        List<Long> ids = oneDayClassRepository.findPopularOneDayClassIds(
                address.getCityId(),
                address.getRegionId(),
                startDate,
                endDate,
                POPULAR_ONEDAYCLASS_LIMIT);

        List<OneDayClass> oneDayClassesByIds = oneDayClassRepository.findOneDayClassesByIds(ids);

        return oneDayClassConverter.converToReadPopularOneDayClassesResponse(oneDayClassesByIds);
    }
  
    @Transactional
    public Long createOneDayClass(SaveOneDayClassRequest request) {
        Atelier foundAtelier = atelierRepository.findById(request.getAtelierId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.ATELIER_NOT_FOUND));

        Category foundCategory = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new BusinessException(ErrorInfo.CATEGORY_NOT_FOUND));

        OneDayClass newOneDayClass = oneDayClassConverter.convertToOneDayClass(request, foundCategory, foundAtelier);

        OneDayClass savedOneDayClass = oneDayClassRepository.save(newOneDayClass);

        return savedOneDayClass.getId();
    }

}
