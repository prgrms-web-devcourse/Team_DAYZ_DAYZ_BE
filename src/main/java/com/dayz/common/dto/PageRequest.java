package com.dayz.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageRequest {

    private int pageIndex;

    private int pageSize;

    private Sort sort;

    public static PageRequest of(int pageIndex, int pageSize, Sort sort) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(pageIndex);
        pageRequest.setPageSize(pageSize);
        pageRequest.setSort(sort);

        return pageRequest;
    }

    public static PageRequest of(int pageIndex, int pageSize) {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageIndex(pageIndex);
        pageRequest.setPageSize(pageSize);

        return pageRequest;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    private class Sort {

        private String column;
        private String order;

    }

}
