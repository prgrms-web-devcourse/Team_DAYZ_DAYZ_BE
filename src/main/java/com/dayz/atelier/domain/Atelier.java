package com.dayz.atelier.domain;

import com.dayz.common.entity.BaseEntity;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.Member;
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
import org.springframework.util.Assert;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "atelier")
public class Atelier extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atelier_id")
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

    @Column(name = "call_number", length = 20)
    private String callNumber;

    @Embedded
    private WorkTime workTime;

    @Column(name = "business_number", nullable = false, length = 20)
    private String businessNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static Atelier of(Long id, String name, Address address, String detail, String intro, WorkTime workTime, String businessNumber,
            Member member) {
        Atelier atelier = new Atelier();
        atelier.setId(id);
        atelier.setName(name);
        atelier.changeAddress(address);
        atelier.setDetail(detail);
        atelier.setIntro(intro);
        atelier.setCallNumber(null);
        atelier.setWorkTime(workTime);
        atelier.setBusinessNumber(businessNumber);
        atelier.changeMember(member);

        return atelier;
    }

    public static Atelier of(String name, Address address, String detail, String intro, WorkTime workTime, String businessNumber, Member member) {
        Atelier atelier = new Atelier();
        atelier.setName(name);
        atelier.changeAddress(address);
        atelier.setDetail(detail);
        atelier.setIntro(intro);
        atelier.setCallNumber(null);
        atelier.setWorkTime(workTime);
        atelier.setBusinessNumber(businessNumber);
        atelier.changeMember(member);

        return atelier;
    }

    // TODO : CallNumber가 새로 추가됨에 따라 생성자를 하나로 통일 할 필요가 있을 듯
    // TODO : 정적 생성자는 이렇게 Entity가 비뀜에따라 추가로 생성자를 만들어야함. 이런점에서는 Builder가 확실히 편한듯
    public static Atelier of(String name, Address address, String detail, String intro, String callNumber, WorkTime workTime, String businessNumber, Member member) {
        Atelier atelier = new Atelier();
        atelier.setName(name);
        atelier.changeAddress(address);
        atelier.setDetail(detail);
        atelier.setIntro(intro);
        atelier.setCallNumber(callNumber);
        atelier.setWorkTime(workTime);
        atelier.setBusinessNumber(businessNumber);
        atelier.changeMember(member);

        return atelier;
    }

    public void changeAddress(Address address) {
        this.setAddress(address);
    }

    public void changeMember(Member member) {
        this.setMember(member);
    }

}
