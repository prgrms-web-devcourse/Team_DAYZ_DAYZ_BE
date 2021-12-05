package com.dayz.follow.domain;

import com.dayz.atelier.domain.Atelier;
import com.dayz.common.entity.BaseEntity;
import com.dayz.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "follow")
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "atelier_id")
    private Atelier atelier;

    public static Follow of(Long id, Member member, Atelier atelier) {
        Follow follow = new Follow();
        follow.setId(id);
        follow.changeMember(member);
        follow.changeAtelier(atelier);

        return follow;
    }

    public static Follow of(Member member, Atelier atelier) {
        Follow follow = new Follow();
        follow.changeMember(member);
        follow.changeAtelier(atelier);

        return follow;
    }

    public void changeMember(Member member) {
        this.setMember(member);
    }

    public void changeAtelier(Atelier atelier) {
        this.setAtelier(atelier);
    }

}
