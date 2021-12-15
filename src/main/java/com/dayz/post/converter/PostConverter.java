package com.dayz.post.converter;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.onedayclass.domain.OneDayClassRepository;
import com.dayz.post.domain.Post;
import com.dayz.post.domain.PostImage;
import com.dayz.post.dto.PostCreateRequest;
import com.dayz.post.dto.PostCreateRequest.PostImagesRequest;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostConverter {

    private final AtelierRepository atelierRepository;

    private final OneDayClassRepository oneDayClassRepository;

    private final MemberRepository memberRepository;

    public Post convertToPost(PostCreateRequest request) {

        Atelier atelier = atelierRepository.findById(request.getAtelierId()).orElseThrow(() -> new BusinessException(ErrorInfo.ATELIER_NOT_FOUND));
        Member member = memberRepository.findById(atelier.getMember().getId()).orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));
        OneDayClass oneDayClass = oneDayClassRepository.findById(request.getOneDayClassId()).orElseThrow(() -> new BusinessException(ErrorInfo.ONE_DAY_CLASS_NOT_FOUND));

        Post post = Post.of(
                request.getContent(),
                member,
                oneDayClass);
        post.addPostImages(request.getPostImages().stream().map(this::convertToImage)
                .collect(Collectors.toList()));
        return post;
    }

    public PostImage convertToImage(PostImagesRequest postImagesRequest) {
        return PostImage.of(postImagesRequest.getFileName(), postImagesRequest.getSequence());
    }

}
