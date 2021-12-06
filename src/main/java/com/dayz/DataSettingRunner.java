package com.dayz;

import com.dayz.atelier.domain.Atelier;
import com.dayz.atelier.domain.AtelierRepository;
import com.dayz.atelier.domain.WorkTime;
import com.dayz.category.domain.Category;
import com.dayz.category.domain.CategoryRepository;
import com.dayz.member.domain.Address;
import com.dayz.member.domain.AddressRepository;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.member.domain.PermissionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class DataSettingRunner implements ApplicationRunner {

    private final CategoryRepository categoryRepository;
    private final PermissionRepository permissionRepository;
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final AtelierRepository atelierRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Permission> permissions = List.of(
                Permission.of("ROLE_USER"),
                Permission.of("ROLE_ATELIER"),
                Permission.of("ROLE_ADMIN")
        );

        permissionRepository.saveAll(permissions);

        List<Category> categories = List.of(
                Category.of("도자기"),
                Category.of("네온싸인"),
                Category.of("요리"),
                Category.of("양모펠트")
        );

        List<Category> savedCategories = categoryRepository.saveAll(categories);

        List<Address> addresses = List.of(
                Address.of(1L, 1L, "서울시", "강남구"),
                Address.of(1L, 2L, "서울시", "강동구"),
                Address.of(1L, 3L, "서울시", "강북구"),
                Address.of(1L, 4L, "서울시", "관악구"),
                Address.of(1L, 5L, "서울시", "강서구"),
                Address.of(2L, 1L, "부산시", "금정구"),
                Address.of(2L, 2L, "부산시", "남구"),
                Address.of(2L, 3L, "부산시", "중구"),
                Address.of(2L, 4L, "부산시", "북구"),
                Address.of(2L, 5L, "부산시", "동구")
        );

        addressRepository.saveAll(addresses);

        List<Member> members = List.of(
                Member.of("김유저", "kakao", "2019948491", null, permissions.get(0), addresses.get(0)),
                Member.of("김공방", "kakao", "2019948492", null, permissions.get(1), addresses.get(1))
        );
        List<Member> savedMembers = memberRepository.saveAll(members);

        atelierRepository.save(Atelier.of("개멋진공방", addresses.get(0), "대륭 서초타워 2층", "겁나 멋진 공방입니다.", WorkTime.of(32400000L,68400000L),"123456789", null, savedMembers.get(0)));

    }

}
