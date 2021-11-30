package com.dayz.common.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseEntity extends BaseTimeEntity{

//    @Column(name = "created_by", nullable = false)
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    private boolean useFlag;

    public void changeCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void changeUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
