package com.dayz.member.domain;

import com.dayz.common.entity.BaseEntity;
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
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String username;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "profile_image_uuid")
    private UUID profileImageUUID;

    @OneToOne(optional = false)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public static Member of(Long id,
            String username,
            String provider,
            String providerId,
            UUID profileImageUUID,
            Permission permission,
            Address address
    ) {
        Member member = new Member();
        member.setId(id);
        member.setUsername(username);
        member.setProvider(provider);
        member.setProviderId(providerId);
        member.setProfileImageUUID(profileImageUUID);
        member.setPermission(permission);
        member.changeAddress(address);

        return member;
    }

    public static Member of(String username,
            String provider,
            String providerId,
            UUID profileImageUUID,
            Permission permission,
            Address address
    ) {
        Member member = new Member();
        member.setUsername(username);
        member.setProvider(provider);
        member.setProviderId(providerId);
        member.setProfileImageUUID(profileImageUUID);
        member.setPermission(permission);
        member.changeAddress(address);

        return member;
    }

    public void changeAddress(Address address) {
        this.setAddress(address);
    }

}
