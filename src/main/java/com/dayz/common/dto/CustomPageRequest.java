package com.dayz.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomPageRequest {

    private int pageIndex;

    private int pageSize;

    private Sort sort;

    public static CustomPageRequest of(int pageIndex, int pageSize, Sort sort) {
        CustomPageRequest pageRequest = new CustomPageRequest();
        pageRequest.setPageIndex(pageIndex);
        pageRequest.setPageSize(pageSize);
        pageRequest.setSort(sort);

        return pageRequest;
    }

    public static CustomPageRequest of(int pageIndex, int pageSize) {
        CustomPageRequest pageRequest = new CustomPageRequest();
        pageRequest.setPageIndex(pageIndex);
        pageRequest.setPageSize(pageSize);

        return pageRequest;
    }

}
