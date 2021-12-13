package com.dayz.common.jwt;

import com.dayz.member.domain.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    public JwtAuthentication getJwtAuthentication() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Member getMemberFromJwt() {
        return (Member)SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

}
