package com.dayz.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditMemberProfileResponse {

    private String name;

    private String imageUrl;

    public static EditMemberProfileResponse of(String name, String imageUrl) {
        EditMemberProfileResponse editMemberProfileResponse = new EditMemberProfileResponse();
        editMemberProfileResponse.setName(name);
        editMemberProfileResponse.setImageUrl(imageUrl);

        return editMemberProfileResponse;
    }

}
