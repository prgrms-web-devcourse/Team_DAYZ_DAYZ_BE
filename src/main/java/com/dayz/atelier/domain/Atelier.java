package com.dayz.atelier.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "atelier")
public class Atelier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "address_detail", nullable = false)
    private String detail;

    @Column(name = "intro", length = 1000)
    private String intro;

    @Embedded
    private WorkTime workTime;

    @Column(name = "business_number", nullable = false, length = 20)
    private String businessNumber;

    @Column(name = "profile_img_uuid")
    private UUID profileImageUuid;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public static Atelier of(Long id, String name, Address address, String detail, String intro, WorkTime workTime, String businessNumber,
            UUID profileImageUuid, Member member) {
        Atelier atelier = new Atelier();
        atelier.setId(id);
        atelier.setName(name);
        atelier.changeAddress(address);
        atelier.setDetail(detail);
        atelier.setIntro(intro);
        atelier.setWorkTime(workTime);
        atelier.setBusinessNumber(businessNumber);
        atelier.setProfileImageUuid(profileImageUuid);
        atelier.changeMember(member);

        return atelier;
    }

    public static Atelier of(String name, Address address, String detail, String intro, WorkTime workTime, String businessNumber,
            UUID profileImageUuid, Member member) {
        Atelier atelier = new Atelier();
        atelier.setName(name);
        atelier.changeAddress(address);
        atelier.setDetail(detail);
        atelier.setIntro(intro);
        atelier.setWorkTime(workTime);
        atelier.setBusinessNumber(businessNumber);
        atelier.setProfileImageUuid(profileImageUuid);
        atelier.changeMember(member);

        return atelier;
    }

    public void changeAddress(Address address) {
        this.setAddress(address);
    }

    private void changeMember(Member member) {
        this.setMember(member);
    }

}
