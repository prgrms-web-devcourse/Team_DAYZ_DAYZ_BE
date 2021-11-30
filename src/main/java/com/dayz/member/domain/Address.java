package com.dayz.member.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    @Column(name = "address_city", length = 50)
    private String city;

    @Column(name = "address_resion", length = 50)
    private String region;

    @Column(name = "address_detail", length = 50)
    private String detail;

}
