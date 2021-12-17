package com.dayz.member.dto;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditMemberProfileRequest {

    @NotBlank(message = "name must be not blank")
    private String name;

    @NotBlank(message = "imageUrl must be not blank")
    private String imageUrl;

    public static EditMemberProfileRequest of(String name, String imageUrl) {
        EditMemberProfileRequest editMemberProfileRequest = new EditMemberProfileRequest();
        editMemberProfileRequest.setName(name);
        editMemberProfileRequest.setImageUrl(imageUrl);

        return editMemberProfileRequest;
    }

}
