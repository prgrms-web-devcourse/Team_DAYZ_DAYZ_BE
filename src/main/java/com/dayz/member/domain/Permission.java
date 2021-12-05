package com.dayz.member.domain;

import com.dayz.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "permission")
public class Permission extends BaseEntity {

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  public static Permission of(Long id, String name) {
    Permission permission = new Permission();
    permission.setId(id);
    permission.setName(name);
    return permission;
  }

  public static Permission of(String name) {
    Permission permission = new Permission();
    permission.setName(name);
    return permission;
  }

}
