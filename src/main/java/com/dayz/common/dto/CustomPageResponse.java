package com.dayz.common.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
public class CustomPageResponse<T> {

    private long totalCount;

    private int pageIndex;

    private int pageSize;

    private List<T> list;

    private boolean hasNext;

    public static CustomPageResponse of(Page tPage) {
        CustomPageResponse customPageResponse = new CustomPageResponse();
        customPageResponse.setTotalCount(tPage.getTotalElements());
        customPageResponse.setPageIndex(tPage.getNumber());
        customPageResponse.setPageSize(tPage.getSize());
        customPageResponse.setList(tPage.getContent());
        customPageResponse.setHasNext(tPage.hasNext());

        return customPageResponse;
    }

}
