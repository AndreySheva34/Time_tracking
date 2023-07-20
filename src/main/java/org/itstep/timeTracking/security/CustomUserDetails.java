package org.itstep.timeTracking.security;

import lombok.RequiredArgsConstructor;
import org.itstep.timeTracking.security.entity.CustomRole;
import org.itstep.timeTracking.security.entity.CustomUser;
import org.itstep.timeTracking.security.repository.CustomUserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private final CustomUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User by username %s not found".formatted(username));
        }
        CustomUser user = optionalUser.get();
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .accountExpired(!user.isAccountNonExpired())
                .accountLocked(!user.isAccountNonLocked())
                .disabled(!user.isEnabled())
                .authorities(AuthorityUtils.createAuthorityList(
                        user.getAuthorities().stream().map(CustomRole::getAuthority).collect(Collectors.joining())
                ))
                .build();
    }
}
