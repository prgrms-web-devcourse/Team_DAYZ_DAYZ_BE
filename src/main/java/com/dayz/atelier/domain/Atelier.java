package com.dayz.atelier.domain;

import com.dayz.member.domain.Address;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Getter
@Table(
        name = "atelier",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_atelier_name", columnNames = {"name"}),
                @UniqueConstraint(name = "UK_member_business_number", columnNames = {"business_number"})
        })
public class Atelier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Embedded
    private Address address;

    @Column(name = "intro", length = 1000)
    private String intro;

    @Embedded
    private WorkTime workTime;

    @Column(name = "business_number", nullable = false, length = 20)
    private String businessNumber;

    @Column(name = "profile_img_uuid")
    private UUID profileImageUuid;

}
