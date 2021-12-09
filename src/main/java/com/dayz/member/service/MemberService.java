package com.dayz.member.service;

import com.dayz.common.enums.ErrorInfo;
import com.dayz.common.exception.BusinessException;
import com.dayz.common.jwt.JwtAuthentication;
import com.dayz.member.converter.MemberConverter;
import com.dayz.member.domain.Member;
import com.dayz.member.domain.MemberRepository;
import com.dayz.member.domain.Permission;
import com.dayz.member.domain.PermissionRepository;
import com.dayz.member.dto.ReadMemberInfoResponse;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PermissionRepository permissionRepository;
    private final MemberConverter memberConverter;

    @Transactional(readOnly = true)
    public ReadMemberInfoResponse getMemberInfo(Long memberId, String token) {
        Member foundMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));

        return memberConverter.convertToReadMemberInfoResponse(foundMember, token);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        Assert.notNull(username,"username must be provided.");

        return memberRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Member findById(Long memberId) {
        Assert.notNull(memberId,"memberId must be provided.");

        return memberRepository.findById(memberId).orElseThrow(() -> new BusinessException(ErrorInfo.MEMBER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByProviderAndProviderId(String provider, String providerId) {
        Assert.notNull(provider,"provider must be provided.");
        Assert.notNull(providerId,"providerId must be provided.");

        return memberRepository.findByProviderAndProviderId(provider, providerId);
    }

    @Transactional
    public Member join(OAuth2User oauth2User, String authorizedClientRegistrationId) {
//        checkArgument(oauth2User != null, "oauth2User must be provided.");
        Assert.notNull(authorizedClientRegistrationId,"provider must be provided.");

        String providerId = oauth2User.getName();
        return findByProviderAndProviderId(authorizedClientRegistrationId, providerId)
                .map(user -> {
                    log.warn("Already exists: {} for (provider: {}, providerId: {})", user, authorizedClientRegistrationId, providerId);
                    return user;
                })
                .orElseGet(() -> {
                    Map<String, Object> attributes = oauth2User.getAttributes();
                    @SuppressWarnings("unchecked")
                    Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
//                    checkArgument(properties != null, "OAuth2User properties is empty");
                    String nickname = (String) properties.get("nickname");
                    String profileImage = (String) properties.get("profile_image");
                    String name = "ROLE_USER";

                    Permission permission = permissionRepository.findByName(name)
                            .orElseThrow(() -> new IllegalStateException("Could not found group for USER_GROUP"));

                    return memberRepository.save(
                            Member.of(nickname, authorizedClientRegistrationId, providerId, profileImage, permission, null)
                    );
                });
    }

}
