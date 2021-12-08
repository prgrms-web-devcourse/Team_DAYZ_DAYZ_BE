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
        CustomPageRequest customPageRequest = new CustomPageRequest();
        customPageRequest.setPageIndex(pageIndex);
        customPageRequest.setPageSize(pageSize);
        customPageRequest.setSort(sort);

        return customPageRequest;
    }

    public static CustomPageRequest of(int pageIndex, int pageSize) {
        CustomPageRequest customPageRequest = new CustomPageRequest();
        customPageRequest.setPageIndex(pageIndex);
        customPageRequest.setPageSize(pageSize);

        return customPageRequest;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private class Sort {

        private String column;
        private String order;

    }

}
