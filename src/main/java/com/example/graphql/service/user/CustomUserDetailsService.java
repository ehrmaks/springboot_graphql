package com.example.graphql.service.user;

import com.example.graphql.domain.repository.MemberRepository;
import com.example.graphql.domain.vo.MemberVo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository userRepository) {
        this.memberRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userName) {
        return memberRepository.findOneWithAuthoritiesByUserName(userName)
                .map(memberVo -> createUser(userName, memberVo))
                .orElseThrow(() -> new UsernameNotFoundException(userName + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String name, MemberVo memberVo) {
        if (!memberVo.isActivated()) {
            throw new RuntimeException(name + " -> 활성화되어 있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorities = memberVo.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(memberVo.getUserName(),
                memberVo.getPasswd(),
                grantedAuthorities);
    }
}