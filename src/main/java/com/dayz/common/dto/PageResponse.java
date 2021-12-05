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
public class PageResponse<T> {

    private long totalCount;

    private int pageIndex;

    private int pageSize;

    private List<T> list;

    private boolean hasNext;

    public static PageResponse of(Page tPage) {
        PageResponse pageResponse = new PageResponse();
        pageResponse.setTotalCount(tPage.getTotalElements());
        pageResponse.setPageIndex(tPage.getNumber());
        pageResponse.setPageSize(tPage.getSize());
        pageResponse.setList(tPage.getContent());
        pageResponse.setHasNext(tPage.hasNext());

        return pageResponse;
    }

}
