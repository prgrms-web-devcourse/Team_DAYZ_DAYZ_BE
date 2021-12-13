package com.dayz.common.aop;

import com.dayz.common.jwt.JwtAuthentication;
import com.dayz.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberService memberService;
    //컨트롤러에서 어노테이션 작용
    //@login
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    //동작하는 부분
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        JwtAuthentication principal = (JwtAuthentication) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return memberService.findById(principal.getId());
    }
}
