package com.dayz.follow.dto;

import com.dayz.follow.domain.Follow;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReadAllFollowingResponse {

    @NotNull(message = "atelierId must not be null.")
    private Long atelierId;

    @NotBlank(message = "name must not be null.")
    private String name;

    @NotBlank(message = "imageFileName must not be null.")
    private String imageFileName;

    @NotBlank(message = "intro must not be null.")
    private String intro;

    public static ReadAllFollowingResponse of(Long atelierId, String name, String imageFileName, String intro) {
        ReadAllFollowingResponse readAllFollowingResponse = new ReadAllFollowingResponse();
        readAllFollowingResponse.setAtelierId(atelierId);
        readAllFollowingResponse.setName(name);
        readAllFollowingResponse.setImageFileName(imageFileName);
        readAllFollowingResponse.setIntro(intro);

        return readAllFollowingResponse;
    }

}
