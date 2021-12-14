package com.dayz.review.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.category.domain.CategoryRepository;
import com.dayz.common.dto.CustomPageRequest;
import com.dayz.common.dto.CustomSort;
import com.dayz.config.QuerydslConfig;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.PermissionRepository;
import com.dayz.onedayclass.domain.OneDayRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@DataJpaTest
@Import(QuerydslConfig.class)
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void findMyReview(){
        List<Review> all = reviewRepository.findAll();
        CustomPageRequest customPageRequest=CustomPageRequest.of(0, 10, CustomSort.of("title", "ASC"));

        Page<Review> myReview = reviewRepository.findAllByMemberId(1L,
            customPageRequest.convertToPageRequest(Review.class));

        assertThat(myReview.getTotalElements()).isEqualTo(1);
    }

    @Test
    void findAtelierReview(){
        CustomPageRequest customPageRequest=CustomPageRequest.of(0, 10, CustomSort.of("createdAt", "ASC"));

        Page<Review> myReview = reviewRepository.findAllByAtelierId(1L,
            customPageRequest.convertToPageRequest(Review.class));

        assertThat(myReview.getTotalElements()).isEqualTo(1);
    }

}