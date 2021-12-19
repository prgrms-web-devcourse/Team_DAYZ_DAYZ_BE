package com.dayz.member.domain;

import com.dayz.atelier.domain.Atelier;
import com.dayz.common.entity.BaseEntity;
import com.dayz.follow.domain.Follow;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name")
    private String username;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(optional = false, mappedBy = "member")
    private Atelier atelier;

    @OneToMany(mappedBy = "member")
    private List<Follow> follows = new ArrayList<>();

    public static Member of(Long id,
            String username,
            String provider,
            String providerId,
            String profileImageUrl,
            Permission permission,
            Address address
    ) {
        Assert.notNull(id,"id must not be null!");
        Assert.notNull(username,"username must not be null!");
        Assert.notNull(provider,"provider must not be null!");
        Assert.notNull(providerId,"providerId must not be null!");
        Assert.notNull(permission,"permission must not be null!");

        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setProvider(provider);
        member.setProviderId(providerId);
        member.setProfileImageUrl(profileImageUrl);
        member.setPermission(permission);
        member.changeAddress(address);

        return member;
    }

    public static Member of(String username,
            String provider,
            String providerId,
            String profileImageUrl,
            Permission permission,
            Address address
    ) {
        Assert.notNull(username,"username must not be null!");
        Assert.notNull(provider,"provider must not be null!");
        Assert.notNull(providerId,"providerId must not be null!");
        Assert.notNull(permission,"permission must not be null!");

        Member member = new Member();
        member.setUsername(username);
        member.setProvider(provider);
        member.setProviderId(providerId);
        member.setProfileImageUrl(profileImageUrl);
        member.setPermission(permission);
        member.changeAddress(address);

        return member;
    }

    public void changeAddress(Address address) {
        this.setAddress(address);
    }

    public void changeAtelier(Atelier atelier) {
        atelier.changeMember(this);
    }

    public void changePermission(Permission permission) {
        this.setPermission(permission);
    }

    public void changeUserName(String userName) {
        this.setUsername(userName);
    }

    public void changeProfileImageUrl(String profileImageUrl) {
        this.setProfileImageUrl(profileImageUrl);
    }

    public void addFollow(Follow follow) {
        follow.changeMember(this);
    }

}
