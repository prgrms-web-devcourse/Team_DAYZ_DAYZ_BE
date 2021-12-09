package com.dayz.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

@Getter
@Setter
@NoArgsConstructor
public class Sort {
    private String column;

    private String order;

    public org.springframework.data.domain.Sort convertSort() {
        return org.springframework.data.domain.Sort.by(Direction.valueOf(this.order),this.column);
    }

    public static Sort of (String column, String order) {
        Sort sort=new Sort();
        sort.setOrder(order);
        sort.setColumn(column);

        return sort;
    }
}
