package com.dayz.review.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.WorkTime;
import com.dayz.category.domain.Category;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomPageResponse;
import com.dayz.common.dto.CustomSort;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.Permission;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.review.converter.ReviewConverter;
import com.dayz.review.domain.Review;
import com.dayz.review.domain.ReviewImage;
import com.dayz.review.domain.ReviewRepository;
import com.dayz.review.dto.ReadAllAtelierReviewsResponse;
import com.dayz.review.dto.ReadAllMyReviewsResponse;
import com.dayz.review.dto.ReadAllMyReviewsResponse.MemberResult;
import com.dayz.review.dto.ReadAllMyReviewsResponse.OneDayClassResult;
import com.dayz.review.dto.ReadAllMyReviewsResponse.ReviewImageResult;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private ReviewConverter reviewConverter;

    private CustomPageRequest customPageRequest;

    private PageRequest pageRequest;

    private Review review;

    private Review reviewResult;

    private Review review2;

    private List<ReviewImage> reviewImage;

    @BeforeEach
    void setUp() {
        customPageRequest = CustomPageRequest.of(0, 10, CustomSort.of("createdAt", "ASC"));
        pageRequest = customPageRequest.convertToPageRequest(Review.class);

        reviewImage = List.of(
            ReviewImage.of(
                "리뷰사진1",
                1),
            ReviewImage.of(
                "리뷰사진2",
                2));
        review = Review.of( "너어어무 재밌어요", "도자기 만들기 꿀잼이네용 담에도 이용할께요", 5,
            Member.of(1L, "김유저", "kakao", "2019948491", null, Permission.of("ROLE_USER"),
                Address.of(1L, 1L, "서울시", "강남구"))
            , OneDayClass.of(1L, "도자기 만들기", "도자기를 재밌게 만들어봐요", 30000, 2L, 6, Category.of("도자기"),
                Atelier.of(1L, "노공방임", Address.of(1L, 3L, "서울시", "서초구"), "대륭 서초타워 2층",
                    "겁나 멋진 공방입니다.", WorkTime.of(32400000L, 68400000L), "123456789",
                    Member.of(2L, "최유저", "kakao", "20201022", null, Permission.of("ROLE_USER"),
                        Address.of(1L, 1L, "서울시", "강남구")))), reviewImage);

        reviewResult = Review.of( 1L,"아주 재밌어용", "시간 가는줄 몰랐네요", 5,
            Member.of(1L, "김유저", "kakao", "2019948491", null, Permission.of("ROLE_USER"),
                Address.of(1L, 1L, "서울시", "강남구"))
            , OneDayClass.of(1L, "도자기 만들기", "도자기를 재밌게 만들어봐요", 30000, 2L, 6, Category.of("도자기"),
                Atelier.of(1L, "노공방임", Address.of(1L, 3L, "서울시", "서초구"), "대륭 서초타워 2층",
                    "겁나 멋진 공방입니다.", WorkTime.of(32400000L, 68400000L), "123456789",
                    Member.of(2L, "최유저", "kakao", "20201022", null, Permission.of("ROLE_USER"),
                        Address.of(1L, 1L, "서울시", "강남구")))), reviewImage);

        review2 = Review.of(2L, "안 재밌어용", "노잼이에용", 3,
            Member.of(2L, "박유저", "kakao", "20201022", null, Permission.of("ROLE_USER"),
                Address.of(1L, 2L, "서울시", "송파구"))
            , OneDayClass.of(1L, "도자기 만들기", "도자기를 재밌게 만들어봐요", 30000, 2L, 6, Category.of("도자기"),
                Atelier.of(2L, "개멋진공방임", Address.of(1L, 4L, "서울시", "동작구"), "대륭 서초타워 2층", " 공방입니다.",
                    WorkTime.of(32400000L, 68400000L), "123456789",
                    Member.of(3L, "서유저", "kakao", "20201022", null, Permission.of("ROLE_USER"),
                        Address.of(1L, 1L, "서울시", "강남구")))), reviewImage);
    }

    @Test
    @DisplayName("내가 쓴 후기 조회를 할 수 있다")
    void getReviewsTest() {
        //given
        Page<Review> reviewPage = new PageImpl<>(List.of(review));

        ReadAllMyReviewsResponse of = ReadAllMyReviewsResponse.of(review.getId(), review.getTitle(),
            review.getContent(),
            review.getScore(), review.getCreatedAt(),
            MemberResult.of(review.getMember().getId(),
                review.getMember().getUsername(), review.getMember().getProfileImageUrl()),
            OneDayClassResult.of(review.getOneDayClass().getId(),
                review.getOneDayClass().getName()),
            review.getReviewImage().stream()
                .map(reviewImage -> ReviewImageResult.of(reviewImage.getImageFileName(),
                    reviewImage.getSequence())).collect(Collectors.toList()));

        given(reviewRepository.findAllByMemberId(1L, pageRequest)).willReturn(reviewPage);
        given(reviewConverter.convertReviewResponse(review)).willReturn(of);

        //when
        CustomPageResponse allReviews = reviewService.getAllReviews(customPageRequest, 1L);

        assertAll(
            () -> verify(reviewRepository, times(1)).findAllByMemberId(1L, pageRequest),
            () -> assertThat(allReviews).isNotNull(),
            () -> assertThat(allReviews.getList().get(0)).isEqualTo(of));
    }

    @Test
    @DisplayName("내가 쓴 후기 조회를 할 수 있다")
    void getReviewsByAtelierTest() {
        //given
        Page<Review> reviewPage = new PageImpl<>(List.of(review));

        ReadAllAtelierReviewsResponse of = ReadAllAtelierReviewsResponse.of(review.getId(), review.getTitle(),
            review.getContent(),
            review.getScore(), review.getCreatedAt(),
            com.dayz.review.dto.ReadAllAtelierReviewsResponse.MemberResult.of(review.getMember().getId(),
                review.getMember().getUsername(), review.getMember().getProfileImageUrl()),
            com.dayz.review.dto.ReadAllAtelierReviewsResponse.OneDayClassResult.of(review.getOneDayClass().getId(),
                review.getOneDayClass().getName()),
            review.getReviewImage().stream()
                .map(reviewImage -> ReadAllAtelierReviewsResponse.ReviewImageResult.of(reviewImage.getImageFileName(),
                    reviewImage.getSequence())).collect(Collectors.toList()));

        given(reviewRepository.findAllByAtelierId(1L, pageRequest)).willReturn(reviewPage);
        given(reviewConverter.convertReadAllAtelierReviewsResponse(review)).willReturn(of);

        //when
        CustomPageResponse allReviews = reviewService.getAllAtelierReviews(customPageRequest, 1L);

        assertAll(
            () -> verify(reviewRepository, times(1)).findAllByAtelierId(1L, pageRequest),
            () -> assertThat(allReviews).isNotNull(),
            () -> assertThat(allReviews.getList().get(0)).isEqualTo(of));
    }

}