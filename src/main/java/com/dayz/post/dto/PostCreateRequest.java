package com.dayz.post.dto;

import com.dayz.member.domain.Member;
import com.dayz.onedayclass.domain.OneDayClass;
import com.dayz.post.domain.PostImage;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {

    @NotNull
    private String content;

    @NotNull
    private Member member;

    @NotNull
    private OneDayClass oneDayClass;

    @NotNull
    private List<PostImage> postImages;

}
