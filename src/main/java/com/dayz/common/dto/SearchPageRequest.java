package com.dayz.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchPageRequest extends CustomPageRequest{

    private String keyWord;

}
